package ru.cinemico;

import io.qameta.allure.Attachment;
import j2html.TagCreator;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

public final class AllureHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllureHelper.class);
    private static final String DEFAULT_ENCODING;

    private AllureHelper() {
    }

    public static byte[] saveXmlAttachment(String name, String message) {
        return saveAttachment(name, message, "text/xml");
    }

    public static byte[] saveAttachment(String name, String message, String type) {
        String charset = getContentEncoding(type, DEFAULT_ENCODING).toUpperCase();
        String typeOnly = type.split(";")[0];
        return saveAttachmentMaskedUni(name, message, charset, typeOnly);
    }

    public static String getContentEncoding(String contentType, String defaultCharset) {
        String charset = defaultCharset;
        String[] var3 = contentType.replace(" ", "").split(";");
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String param = var3[var5];
            if (param.startsWith("charset=")) {
                charset = param.split("=", 2)[1].replaceAll("\"", "");
                break;
            }
        }

        return charset;
    }

    private static byte[] saveAttachmentMaskedUni(String name, String message, String charset, String type) {
        byte var5 = -1;
        switch(type.hashCode()) {
            case -1082243251:
                if (type.equals("text/html")) {
                    var5 = 1;
                }
                break;
            case -1004727243:
                if (type.equals("text/xml")) {
                    var5 = 0;
                }
                break;
            case -43840953:
                if (type.equals("application/json")) {
                    var5 = 2;
                }
        }

        switch(var5) {
            case 0:
                return saveXmlAttachmentMasked(name, message, charset);
            case 1:
                return saveHtmlAttachmentMasked(name, message, charset);
            case 2:
                return saveJsonAttachmentMasked(name, message, charset);
            default:
                return saveTextAttachmentMasked(name, message, charset);
        }
    }

    @Attachment(
            value = "{name}",
            type = "text/plain"
    )
    private static byte[] saveTextAttachmentMasked(String name, String message, String charset) {
        return saveAttachmentMasked(name, message, charset);
    }

    @Attachment(
            value = "{name}",
            type = "text/xml"
    )
    private static byte[] saveXmlAttachmentMasked(String name, String message, String charset) {
        return saveAttachmentMasked(name, message, charset);
    }

    @Attachment(
            value = "{name}",
            type = "text/html"
    )
    private static byte[] saveHtmlAttachmentMasked(String name, String message, String charset) {
        return saveAttachmentMasked(name, message, charset);
    }

    @Attachment(
            value = "{name}",
            type = "application/json"
    )
    private static byte[] saveJsonAttachmentMasked(String name, String message, String charset) {
        return saveAttachmentMasked(name, message, charset);
    }
    private static byte[] saveAttachmentMasked(String name, String message, String charset) {
        try {
            return message.getBytes(charset);
        } catch (UnsupportedEncodingException var4) {
            LOGGER.warn("Неподдерживаемая кодировка {}", charset, var4);
            return message.getBytes();
        }
    }

    public static void saveHttpGetParameters(String serverUrl) {
        try {
            URI uri = new URI(serverUrl);
            String query = uri.getRawQuery();
            String[] paramPairs = query.split("&");
            Map<String, String> parMap = new HashMap();
            String[] var5 = paramPairs;
            int var6 = paramPairs.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String par = var5[var7];
                parMap.put(par.split("=")[0], URLDecoder.decode(par.split("=")[1], DEFAULT_ENCODING));
            }

            String html = map2HtmlTable(parMap);
            saveHtmlAttachment("параметры запроса", html);
        } catch (URISyntaxException | UnsupportedEncodingException var9) {
            LOGGER.error("error LOGGER http parameters", var9);
        }

    }

    public static byte[] saveHtmlAttachment(String name, String message) {
        return saveAttachment(name, message, "text/html");
    }

    public static String map2HtmlTable(Map<? extends Object, ? extends Object> parMap) {
        List<ContainerTag> rows = new ArrayList();
        rows.add(TagCreator.tr().with(new DomContent[]{TagCreator.th().with(TagCreator.span("параметр")), TagCreator.th().with(TagCreator.span("значение"))}));
        Iterator var2 = parMap.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<? extends Object, ? extends Object> entry = (Entry)var2.next();
            rows.add(TagCreator.tr().with(new DomContent[]{TagCreator.td().with(TagCreator.span(entry.getKey().toString())), TagCreator.td().with(TagCreator.span(entry.getValue().toString()))}));
        }

        return ((ContainerTag)TagCreator.table().attr("border", "1")).with(rows).render();
    }

    public static void saveMapAsHtml(String name, Map<? extends Object, ? extends Object> parMap) {
        String html = map2HtmlTable(parMap);
        html = "<!DOCTYPE html><html><head><meta charset=\"utf-8\"></head><body>" + html + "</body></html>";
        saveHtmlAttachment(name, html);
    }

    public static void saveMapAsHtml(String name, List<Pair<? extends Object, ? extends Object>> headers) {
        Map<String, String> parMap = new HashMap();
        Iterator var3 = headers.iterator();

        while(var3.hasNext()) {
            Pair<? extends Object, ? extends Object> pair = (Pair)var3.next();
            parMap.put(pair.getKey().toString(), pair.getValue().toString());
        }

        saveMapAsHtml(name, (Map)parMap);
    }

    public static void saveMapAsHtml(String name, Header[] headers) {
        Map<String, String> parMap = new HashMap();
        Header[] var3 = headers;
        int var4 = headers.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Header pair = var3[var5];
            parMap.put(pair.getName(), pair.getValue());
        }

        saveMapAsHtml(name, (Map)parMap);
    }

    static {
        DEFAULT_ENCODING = StandardCharsets.UTF_8.toString();
    }
}
