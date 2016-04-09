package at.begin.web.movie;

import at.begin.infra.util.Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    String comment;
    List<String> directors;
    List<String> actors;

    public MovieDto(Element item) {
        title = getProperty(item, "title");
        subtitle = getProperty(item, "subtitle");
        link = getProperty(item, "link");
        image = getProperty(item, "image");
        id = title + ":" + subtitle;
        pubDate = getProperty(item, "pubDate");
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

    public MovieDto(Map map) {
        title = (String) ((Map) ((List) map.get("title")).get(0)).get("content");
        link = (String) ((Map) ((List) map.get("title")).get(0)).get("link");
        subtitle = (String) ((Map) ((List) map.get("eng_title")).get(0)).get("content");
        image = (String) ((Map) ((List) map.get("thumbnail")).get(0)).get("content");
        pubDate = (String) ((Map) ((List) map.get("open_info")).get(0)).get("content");
        id = Util.extract("movieId=([0-9]+)", link);
        directors = ((List<Map>) map.get("director")).stream().map(director -> (String) director.get("content")).collect(Collectors.toList());
        actors = ((List<Map>) map.get("actor")).stream().map(director -> (String) director.get("content")).collect(Collectors.toList());
    }
}
