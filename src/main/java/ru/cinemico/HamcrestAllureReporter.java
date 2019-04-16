package ru.cinemico;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.StringDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public final class HamcrestAllureReporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HamcrestAllureReporter.class);
    private static final Pattern PATTERN_XML = Pattern.compile("<\\?xml\\s+.+>", 34);
    private static final Pattern PATTERN_HTML = Pattern.compile("<.+>", 34);
    private static final Pattern PATTERN_JSON = Pattern.compile("\\[?\\s?\\{.+\\}\\s?\\]?", 34);
    public static final String HIDDEN_VALUE = "Значение скрыто согласно текущему уровню логирования.";

    private HamcrestAllureReporter() {
    }

    public static void addToAllureReport(Object actual, Matcher matcher, String reason) {
        Description description = new StringDescription();
        if (!StringUtils.isEmpty(reason)) {
            description.appendText(reason).appendText(": ");
        }

        description.appendText("checking that ").appendDescriptionOf(matcher);
        String json = HamcrestHelper.serializeObj(actual, LOGGER.isDebugEnabled());
        if (json.length() >= 20 && !json.equals("Значение скрыто согласно текущему уровню логирования.")) {
            addAssertDescription(description, json, reason, actual, matcher);
        } else {
            addAssertDescriptionShort(description, json, reason, actual, matcher);
        }

    }

    @Step("{description}")
    private static <T> void addAssertDescription(Description description, String actS, String reason, T actualValue, Matcher<? super T> matcher) {
        String contType = guessType(actS);
        AllureHelper.saveAttachment("actual value", actS, contType);
        if (isAddAssertStepToAllure()) {
            MatcherAssert.assertThat(reason, actualValue, matcher);
        }

    }

    @Step("{description} (actual=\"{actual}\")")
    private static <T> void addAssertDescriptionShort(Description description, String actual, String reason, T actualValue, Matcher<? super T> matcher) {
        if (isAddAssertStepToAllure()) {
            MatcherAssert.assertThat(reason, actualValue, matcher);
        }

    }

    public static boolean isAddAssertStepToAllure() {
        return LOGGER.isInfoEnabled();
    }

    private static String guessType(String actS) {
        String contType = "text/plain";
        if (PATTERN_XML.matcher(actS).matches()) {
            contType = "text/xml";
        } else if (PATTERN_HTML.matcher(actS).matches()) {
            contType = "text/xml";
        } else if (PATTERN_JSON.matcher(actS).matches()) {
            contType = "application/json";
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("пытаемся догадаться о типе проверямой строки '{}'\nпринимаем за '{}'", actS, contType);
        } else {
            LOGGER.trace("принимаем тип проверямой строки за '{}'", contType);
        }

        return contType;
    }
}
