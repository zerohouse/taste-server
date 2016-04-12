package at.begin.web.search;

import at.begin.web.content.book.BookDto;
import at.begin.web.content.movie.MovieDto;
import at.begin.web.content.music.MusicDto;
import at.begin.web.search.getter.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class SearchService {

    HttpRequestFactory httpRequestFactory;
    HttpObjectGetter<MusicDto> musicGetter;
    HttpObjectGetter<BookDto> bookGetter;
    HttpObjectGetter<MovieDto> movieGetter;
    HttpObjectGetter<MovieDto> naverMovieGetter;

    @PostConstruct
    public void setup() {
        HttpTransport httpTransport = new NetHttpTransport();
        httpRequestFactory = httpTransport.createRequestFactory();
        musicGetter = new MelonMusicGetter(httpRequestFactory);
        naverMovieGetter = new NaverMovieGetter(httpRequestFactory);
        movieGetter = new DaumMovieGetter(httpRequestFactory);
//        bookGetter = new GoogleBookGetter(httpRequestFactory);
        bookGetter = new NaverBookGetter(httpRequestFactory);
    }

    public List<MovieDto> getMovies(String query) {
        return getObjectList(movieGetter, query);
    }

    public List<MovieDto> getMoviesKeyword(String query) {
        return getObjectList(naverMovieGetter, query);
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
