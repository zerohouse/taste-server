package at.begin.infra.sender;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class SmsSender {

    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String PARAM_STRING = "userid=%s&callback=%s&phone=%s&type=sms&msg=%s";


    private String userid;

    private String sender;

    HttpRequestFactory httpRequestFactory;


    public SmsSender(String userid, String sender) {
        this.userid = userid;
        this.sender = sender;
        HttpTransport httpTransport = new NetHttpTransport();
        httpRequestFactory = httpTransport.createRequestFactory();
    }

    public boolean send(String to, String message) throws IOException {
        String formatted = String.format(PARAM_STRING, userid, sender, to, message);
        ByteArrayContent content = new ByteArrayContent(APPLICATION_X_WWW_FORM_URLENCODED, formatted.getBytes("euc-kr"));
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpRequest request = httpRequestFactory.buildPostRequest(new GenericUrl("http://biz.smson.co.kr/module/socket_send_multi.php"), content);
        httpHeaders.set("charset", "euc-kr");
        request.setHeaders(httpHeaders);
        String result = request.execute().parseAsString();
        result = result.substring(result.indexOf(":header_stop:") + 13, result.length());
        return result.charAt(0) == '1';
    }
}
