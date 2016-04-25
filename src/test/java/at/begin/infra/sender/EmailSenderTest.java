package at.begin.infra.sender;

import org.apache.catalina.Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class EmailSenderTest {

    @Test
    public void send() {
        EmailSender emailSender = new EmailSender("parksungho86@gmail.com");
        List<String> email = new ArrayList<>();
        email.add("oncepower@naver.com");
        emailSender.send(email, "asdf", "asdfasdf");
    }
}