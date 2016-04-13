package at.begin.web.chat;

import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.chat.message.Message;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = {@UniqueConstraint(name = UniqueKeys.CHAT_ALREADY_EXIST, columnNames = {"host_user", "invited_user"})})
@Entity
public class Chat {

    @ManyToOne
    @JoinColumn
    User hostUser;

    @ManyToOne
    @JoinColumn
    User invitedUser;

    @OneToMany(mappedBy = "chat")
    List<Message> messages = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    ChatState state;

    public boolean isJoinedUser(User user) {
        return user.equals(hostUser) || user.equals(invitedUser);
    }

    public boolean isInvited(User user) {
        return user.equals(invitedUser);
    }

    public boolean chatable() {
        return state == ChatState.OPEN;
    }

    public void exit(User user) {
        if (hostUser.equals(user)) {
            hostUser = null;
            return;
        }
        if (invitedUser.equals(user))
            invitedUser = null;

    }
}

