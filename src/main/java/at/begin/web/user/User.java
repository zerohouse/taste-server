package at.begin.web.user;

import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.chat.Chat;
import at.begin.web.chat.message.Message;
import at.begin.web.content.UserLikesContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints = {@UniqueConstraint(name = UniqueKeys.EMAIL_ALREADY_EXIST, columnNames = "email")})
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @OneToMany(mappedBy = "user")
    List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "hostUser")
    List<Chat> userHostChats = new ArrayList<>();

    @OneToMany(mappedBy = "invitedUser")
    List<Chat> userInvitedChats = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserMatchedUser> userMatchingUsers = new ArrayList<>();

    @OneToMany(mappedBy = "matched")
    List<UserMatchedUser> userMatchedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserLikesContent> userLikesContents = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue
    long id;

    @NotNull(message = "이름을 입력해주세요.")
    @Column
    String name;

    @NotNull(message = "성별을 입력해주세요.")
    @Column
    String gender;

    @NotNull(message = "나이를 입력해주세요.")
    @Column
    Integer age;

    @NotNull(message = "지역을 입력해주세요.")
    @Column
    String district;

    @NotNull(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^.+@.+\\..+$", message = "이메일 형식이 맞지 않습니다.")
    @Column
    String email;

    @Column
    String photo;

    @Column
    String introduce;

    @NotNull(message = "암호를 입력해주세요.")
    @Column
    String password;

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        logger.debug("password passed : {}, password fromDB : {}", password, this.password);
        return passwordEncoder.matches(password, this.password);
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public void update(User updated) {
        name = updated.name;
        age = updated.age;
        district = updated.district;
        gender = updated.gender;
        introduce = updated.introduce;
    }

    public List<Chat> getAllChats() {
        List<Chat> chats = new ArrayList<>();
        chats.addAll(userHostChats);
        chats.addAll(userInvitedChats);
        return chats;
    }
}
