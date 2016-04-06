package at.begin.web.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

import static at.begin.infra.util.Util.getListProperty;
import static at.begin.infra.util.Util.getProperty;

@Getter
@NoArgsConstructor
@ToString
public class MovieDto {
    String id;
    String title;
    String link;
    String image;
    String subtitle;
    String pubDate;
    // String userRating;
    String comment;
    List<String> directors;
    List<String> actors;

    public MovieDto(Element item) {
        title = getProperty(item, "title").replaceAll("(<([^>]+)>)|(<[^<]+)$", "");
        subtitle = getProperty(item, "subtitle").replaceAll("(<([^>]+)>)|(<[^<]+)$", "");
        link = getProperty(item, "link");
        image = getProperty(item, "image");
        id = title + ":" + subtitle;
        pubDate = getProperty(item, "pubDate");
        // userRating = getProperty(item, "userRating");
        directors = getListProperty(item, "director");
        actors = getListProperty(item, "actor");
    }

    public MovieDto(UserLikesMovie userLikesMovie) {
        id = userLikesMovie.movie.id;
        title = userLikesMovie.movie.title;
        link = userLikesMovie.movie.link;
        image = userLikesMovie.movie.image;
        subtitle = userLikesMovie.movie.subtitle;
        pubDate = userLikesMovie.movie.pubDate;
        comment = userLikesMovie.comment;
        directors = Arrays.asList(userLikesMovie.movie.directors.split("\\|"));
        actors = Arrays.asList(userLikesMovie.movie.actors.split("\\|"));
    }

}
