package at.begin.web.chat.message;

import at.begin.ServerLauncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ServerLauncher.class)
public class MessageTest {


    @Autowired
    MessageRepository messageRepository;

    @Test
    public void szet() {
        Message m = new Message();
        messageRepository.save(m);
    }

}