package at.begin.infra.exception.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueKeys {

    private static final Pattern pattern;
    public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";

    static {
        pattern = Pattern.compile("'(\\w+)'$");
    }

    private static final Map<String, String> messageMap;

    static {
        messageMap = new HashMap<>();
        messageMap.put(EMAIL_ALREADY_EXIST, "이미 가입한 이메일입니다.");
    }

    static String getErrorMessage(String message) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return messageMap.get(matcher.group(1));
        }
        return null;
    }

}
