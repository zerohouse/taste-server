package at.begin.web.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class MovieDto {
    Long id;
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
        title = getProperty(item, "title");
        link = getProperty(item, "link");
        image = getProperty(item, "image");
        subtitle = getProperty(item, "subtitle");
        pubDate = getProperty(item, "pubDate");
        // userRating = getProperty(item, "userRating");
        directors = getListProperty(item, "director");
        actors = getListProperty(item, "actor");
    }

    public MovieDto(UserLikesMovie userLikesMovie) {
        id = userLikesMovie.id;
        title = userLikesMovie.movie.title;
        link = userLikesMovie.movie.link;
        image = userLikesMovie.movie.image;
        subtitle = userLikesMovie.movie.subtitle;
        pubDate = userLikesMovie.movie.pubDate;
        comment = userLikesMovie.comment;
        directors = Arrays.asList(userLikesMovie.movie.directors.split("\\|"));
        actors = Arrays.asList(userLikesMovie.movie.actors.split("\\|"));
    }

    private static List<String> getListProperty(Element item, String name) {
        String source = getProperty(item, name);
        if (source == null) return null;
        return Arrays.asList(source.split("\\|"));
    }

    private static String getProperty(Element item, String name) {
        if (item.getElementsByTagName(name).getLength() == 0)
            return null;
        return item.getElementsByTagName(name).item(0).getTextContent();
    }
}
