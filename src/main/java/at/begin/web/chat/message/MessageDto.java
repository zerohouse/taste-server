package at.begin.web.chat.message;

import at.begin.web.chat.ChatUserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {

    ChatUserDto user;

    Long id;

    String message;

    Boolean read;

    Date createAt;

    public MessageDto(Message message) {
        user = new ChatUserDto(message.user);
        id = message.id;
        this.message = message.message;
        read = message.reading;
        createAt = message.createAt;
    }
}
