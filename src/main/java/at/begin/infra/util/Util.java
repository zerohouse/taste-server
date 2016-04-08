package at.begin.infra.util;

import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static String getProperty(Element item, String name) {
        if (item.getElementsByTagName(name).getLength() == 0)
            return null;
        return item.getElementsByTagName(name).item(0).getTextContent().replaceAll("(<([^>]+)>)|(<[^<]+)$", "");
    }

    public static List<String> getListProperty(Element item, String name) {
        String source = Util.getProperty(item, name);
        if (source == null) return null;
        return Arrays.asList(source.split("\\|"));
    }


    public static String joinString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(string -> builder.append(string).append("|"));
        String result = builder.toString();
        if (result.length() == 0)
            return result;
        return result.substring(0, result.length() - 1);
    }
}
