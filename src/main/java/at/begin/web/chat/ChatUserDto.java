package at.begin.web.chat;

import at.begin.web.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatUserDto {
    long id;
    String name;
    String email;
    String photo;

    public ChatUserDto(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        photo = user.getPhoto();
    }
}
