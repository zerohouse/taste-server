package at.begin.infra.util;

import at.begin.infra.sender.SmsSender;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class SmsSenderTest {

    @Test
    public void sendSms() throws IOException {
        SmsSender smsSender = new SmsSender("zerohouse", "01067662010");
        System.out.print(smsSender.send("01067662010", "한글"));
    }

    @Test
    public void endSms() throws IOException {
        System.out.print(Util.rand("01", 10));
    }

}