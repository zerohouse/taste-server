package at.begin.web.chat;

import at.begin.web.chat.message.MessageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ChatDto {

    ChatUserDto hostUser;

    ChatUserDto invitedUser;

    List<MessageDto> messages;

    Long id;

    ChatState state;


    public ChatDto(Chat chat) {
        if (chat.hostUser != null)
            hostUser = new ChatUserDto(chat.hostUser);
        if (chat.invitedUser != null)
            invitedUser = new ChatUserDto(chat.invitedUser);
        messages = chat.getMessages().stream().map(MessageDto::new).collect(Collectors.toList());
        id = chat.id;
        state = chat.state;
    }
}
