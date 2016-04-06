package at.begin.web.user;

import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.book.UserLikesBook;
import at.begin.web.movie.UserLikesMovie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
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
    List<UserLikesBook> userLikesBooks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UserLikesMovie> userLikesMovies = new ArrayList<>();

    @Id
    @Column
    @GeneratedValue
    long id;

    @Column
    String name;

    @Column
    String email;

    @Column
    String password;

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        logger.debug("password passed : {}, password fromDB : {}", password, this.password);
        return passwordEncoder.matches(password, this.password);
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
