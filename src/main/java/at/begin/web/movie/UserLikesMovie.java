package at.begin.web.movie;


import at.begin.web.exception.handler.UniqueKeys;
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
@Table(uniqueConstraints = @UniqueConstraint(name = UniqueKeys.MOVIE_ALREADY_EXIST, columnNames = {"user", "movie"}))
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLikesMovie {

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Movie movie;

    @Id
    @Column
    @GeneratedValue
    long id;

    @Lob
    String comment;

    public UserLikesMovie(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }
}
