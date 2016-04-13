package at.begin.web.chat.message;

import at.begin.web.chat.Chat;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table
public class Message {

    @ManyToOne
    @JoinColumn
    Chat chat;

    @ManyToOne
    @JoinColumn
    User user;

    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    String message;

    @Column
    Boolean reading;

    @Column
    Date createAt;
}
