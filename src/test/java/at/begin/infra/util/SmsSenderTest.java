package at.begin.infra.util;

import org.junit.Test;

public class SmsSenderTest {

    @Test
    public void sendSms(){
        SmsSender smsSender = new SmsSender("zerohouse");
        smsSender.send("01067662010", "01067662010", "마가")
    }

}