package at.begin.infra.sender;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;

import java.util.Collections;
import java.util.List;

public class EmailSender {


    private String from;
    private AmazonSimpleEmailServiceClient client;

    public EmailSender(String from) {
        this.from = from;
        client = new AmazonSimpleEmailServiceClient();
        Region REGION = Region.getRegion(Regions.US_WEST_2);
        client.setRegion(REGION);
    }

    public void send(List<String> destinations, String subject, String bodyString) {
        Destination destination = new Destination().withToAddresses(destinations);
        send(destination, subject, bodyString);
    }

    public void send(Destination destination, String subject, String bodyString) {
        Content subjectContent = new Content().withData(subject);
        Content bodyContent = new Content().withData(bodyString);
        Body body = new Body().withText(bodyContent);

        Message message = new Message().withSubject(subjectContent).withBody(body);
        SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);
        client.sendEmail(request);
    }
}
