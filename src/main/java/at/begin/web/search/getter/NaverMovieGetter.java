package at.begin.web.search.getter;

import at.begin.web.content.movie.MovieDto;
import com.google.api.client.http.HttpRequestFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NaverMovieGetter extends NaverGetter {

    public static final String NAVER_MOVIE_URL = "https://openapi.naver.com/v1/search/movie.xml?display=100&query=%s";

    public NaverMovieGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    @Override
    public List<MovieDto> getList(String query) {
        List<MovieDto> movieList = new ArrayList<>();
        try {
            NodeList elements = getDocument(String.format(NAVER_MOVIE_URL, query)).getElementsByTagName("item");
            for (int i = 0; i < elements.getLength(); i++) {
                movieList.add(new MovieDto((Element) elements.item(i)));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return movieList;
    }

}
