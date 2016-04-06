package at.begin.web.movie;

import at.begin.web.exception.handler.UniqueKeys;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "link")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints = {@UniqueConstraint(name = UniqueKeys.LINK_ALREADY_EXIST, columnNames = "link")})
public class Movie {

    @OneToMany(mappedBy = "movie")
    private List<UserLikesMovie> userLikesMovies = new ArrayList<>();

    @Id
    @Column
    String link;

    @Column
    String title;

    @Column
    String image;

    @Column
    String subtitle;

    @Column
    String pubDate;

    @Column
    String directors;

    @Column
    String actors;

    public Movie(MovieDto movieDto) {
        link = movieDto.link;
        title = movieDto.title;
        image = movieDto.image;
        subtitle = movieDto.subtitle;
        pubDate = movieDto.pubDate;
        directors = joinString(movieDto.directors);
        actors = joinString(movieDto.actors);
    }

    private String joinString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(string -> builder.append(string).append("|"));
        return builder.toString();
    }
}
