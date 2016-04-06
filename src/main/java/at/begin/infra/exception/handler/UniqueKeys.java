package at.begin.infra.exception.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueKeys {

    private static final Pattern pattern;
    public static final String EMAIL_ALREADY_EXIST = "EMAIL_ALREADY_EXIST";
    public static final String LINK_ALREADY_EXIST = "LINK_ALREADY_EXIST";
    public static final String MOVIE_ALREADY_EXIST = "MOVIE_ALREADY_EXIST";
    public static final String BOOK_ALREADY_EXIST = "BOOK_ALREADY_EXIST";

    static {
        pattern = Pattern.compile("'(\\w+)'$");
    }

    private static final Map<String, String> messageMap;

    static {
        messageMap = new HashMap<>();
        messageMap.put(EMAIL_ALREADY_EXIST, "이미 가입한 이메일입니다.");
        messageMap.put(LINK_ALREADY_EXIST, "저장 중 오류가 발생했습니다.");
        messageMap.put(MOVIE_ALREADY_EXIST, "이미 콜렉션에 있는 영화입니다.");
        messageMap.put(BOOK_ALREADY_EXIST, "이미 콜렉션에 있는 책입니다.");
    }

    static String getErrorMessage(String message) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return messageMap.get(matcher.group(1));
        }
        return null;
    }

}
