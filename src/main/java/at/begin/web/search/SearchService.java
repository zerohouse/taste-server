package at.begin.web.search;

import at.begin.web.book.BookDto;
import at.begin.web.movie.MovieDto;
import at.begin.web.music.MusicDto;
import at.begin.web.search.getter.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Service
public class SearchService {

    HttpRequestFactory httpRequestFactory;
    HttpObjectGetter<MusicDto> musicGetter;
    HttpObjectGetter<BookDto> bookGetter;
    HttpObjectGetter<MovieDto> movieGetter;

    @PostConstruct
    public void setup() {
        HttpTransport httpTransport = new NetHttpTransport();
        httpRequestFactory = httpTransport.createRequestFactory();
        musicGetter = new MelonMusicGetter(httpRequestFactory);
//        movieGetter = new NaverMovieGetter(httpRequestFactory);
        movieGetter = new DaumMovieGetter(httpRequestFactory);
        bookGetter = new GoogleBookGetter(httpRequestFactory);
//        bookGetter = new NaverBookGetter(httpRequestFactory);
    }

    public List<MovieDto> getMovies(String query) {
        return getObjectList(movieGetter, query);
    }

    public List<MusicDto> getMusics(String query) {
        return getObjectList(musicGetter, query);
    }

    public List<MusicDto> getBooks(String query) {
        return getObjectList(bookGetter, query);
    }

    private List getObjectList(HttpObjectGetter objectFromJson, String query) {
        try {
            return objectFromJson.getList(query);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
