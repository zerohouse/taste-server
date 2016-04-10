package at.begin.web.content.movie;

import at.begin.web.content.UserLikesContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static at.begin.infra.util.Util.joinString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Movie {

    @OneToMany(mappedBy = "movie")
    private List<UserLikesContent> userLikesContents = new ArrayList<>();

    @Id
    @Column
    String id;

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
        id = movieDto.id;
        link = movieDto.link;
        title = movieDto.title;
        image = movieDto.image;
        subtitle = movieDto.subtitle;
        pubDate = movieDto.pubDate;
        directors = joinString(movieDto.directors);
        actors = joinString(movieDto.actors);
    }

}
