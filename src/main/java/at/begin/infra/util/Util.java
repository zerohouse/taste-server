package at.begin.infra.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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

    public static String rand(String source, int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += source.charAt(random.nextInt(source.length()));
        }
        return result;
    }


    public static String joinString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(string -> builder.append(string).append("|"));
        String result = builder.toString();
        if (result.length() == 0)
            return result;
        return result.substring(0, result.length() - 1);
    }

    public static String extract(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find())
            return matcher.group(1);
        return null;
    }
}
