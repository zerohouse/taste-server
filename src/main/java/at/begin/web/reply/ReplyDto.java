package at.begin.web.reply;

import at.begin.web.user.UserCoreDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyDto {

    UserCoreDto user;
    Long id;
    Date createAt;
    String message;

    public ReplyDto(Reply reply) {
        id = reply.id;
        createAt = reply.createAt;
        message = reply.message;
        user = new UserCoreDto(reply.user);
    }
}
