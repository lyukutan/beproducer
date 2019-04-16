package ru.cinemico;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.*;
import org.hamcrest.core.Is;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public final class HamcrestHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HamcrestHelper.class);
    private static final ObjectWriter JSON_WRITER = (new ObjectMapper()).writer().withDefaultPrettyPrinter();

    private HamcrestHelper() {
    }

    public static <T> Matcher<T> hasGraph(String graphPath, Matcher<T> matcher) {
        List<String> properties = Arrays.asList(graphPath.split("\\."));
        ListIterator<String> iterator = properties.listIterator(properties.size());

        Matcher ret;
        for(ret = matcher; iterator.hasPrevious(); ret = Matchers.hasProperty((String)iterator.previous(), ret)) {
            ;
        }

        return ret;
    }

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
        log(actual, matcher, "");
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        log(actual, matcher, reason);
    }

    public static void assertThat(String reason, boolean assertion) {
        log(assertion, Is.is(true), reason);
    }

    private static void log(Object actual, Matcher matcher, String reason) {
        if (LOGGER.isInfoEnabled()) {
            Description description = new StringDescription();
            if (actual != null && !ClassUtils.isPrimitiveOrWrapper(actual.getClass()) && !(actual instanceof String)) {
                description.appendText(serializeObj(actual, LOGGER.isDebugEnabled()));
            } else {
                description.appendValue(actual);
            }

            description.appendText("\nis ").appendDescriptionOf(matcher);
            if (!StringUtils.isEmpty(reason)) {
                description.appendText(" (").appendText(reason).appendText(")");
            }

            LOGGER.info("Checking that:\n{}", description);
        }

        if (HamcrestAllureReporter.isAddAssertStepToAllure()) {
            HamcrestAllureReporter.addToAllureReport(actual, matcher, reason);
        } else {
            MatcherAssert.assertThat(reason, actual, matcher);
        }

    }

    static String serializeObj(Object actual, boolean addRealValue) {
        String json;
        if (actual == null) {
            json = "null";
        } else if (addRealValue) {
            if (!ClassUtils.isPrimitiveOrWrapper(actual.getClass()) && !(actual instanceof String)) {
                try {
                    json = JSON_WRITER.writeValueAsString(actual);
                } catch (JsonProcessingException var4) {
                    LOGGER.warn("ошибка серилизации объекта при выводе в отчет allure", var4);
                    json = actual.toString();
                }
            } else {
                json = actual.toString();
            }
        } else {
            json = "Значение скрыто согласно текущему уровню логирования.";
        }

        return json;
    }
}