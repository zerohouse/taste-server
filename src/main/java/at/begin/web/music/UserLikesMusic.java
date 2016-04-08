package at.begin.web.music;


import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.movie.Movie;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(name = UniqueKeys.MOVIE_ALREADY_EXIST, columnNames = {"user", "music"}))
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLikesMusic {

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Music music;

    @Id
    @Column
    @GeneratedValue
    long id;

    @Lob
    String comment;

    public UserLikesMusic(User user, Music music) {
        this.user = user;
        this.music = music;
    }
}
