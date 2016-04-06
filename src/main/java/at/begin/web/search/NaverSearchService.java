package at.begin.web.search;

import at.begin.web.book.BookDto;
import at.begin.web.movie.MovieDto;
import at.begin.web.music.MusicDto;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NaverSearchService {

    public static final String NAVER_MOVIE_URL = "https://openapi.naver.com/v1/search/movie.xml?display=100&query=%s";
    public static final String NAVER_BOOK_URL = "https://openapi.naver.com/v1/search/book.xml?display=100&query=%s";
    public static final String MELON_MUSIC_URL = "http://www.melon.com/search/keyword/index.json?query=%s";
    HttpTransport httpTransport;
    HttpRequestFactory httpRequestFactory;

    @PostConstruct
    public void setup() {
        httpTransport = new NetHttpTransport();
        httpRequestFactory = httpTransport.createRequestFactory();
    }

    public Map getJsonMap(String url) throws IOException {
        HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(url));
        request.setParser(new JsonObjectParser(new JacksonFactory()));
        return request.execute().parseAs(Map.class);
    }

    public List<MusicDto> getMusics(String query) {
        List<MusicDto> musicDtos = new ArrayList<>();
        try {
            Map map = getJsonMap(String.format(MELON_MUSIC_URL, query));
            List<Map> artistContents = (List) map.get("ARTISTCONTENTS");
            List<Map> songContents = (List) map.get("SONGCONTENTS");
            List<Map> albumContents = (List) map.get("ALBUMCONTENTS");
            musicDtos.addAll(artistContents.stream().map(artistContent -> {
                MusicDto musicDto = new MusicDto();
                musicDto.setAsArtistContent(artistContent);
                return musicDto;
            }).collect(Collectors.toList()));
            musicDtos.addAll(songContents.stream().map(songContent -> {
                MusicDto musicDto = new MusicDto();
                musicDto.setAsSongContent(songContent);
                return musicDto;
            }).collect(Collectors.toList()));
            musicDtos.addAll(albumContents.stream().map(albumContent -> {
                MusicDto musicDto = new MusicDto();
                musicDto.setAsAlbumContent(albumContent);
                return musicDto;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return musicDtos;
    }

    public Document getDocument(String url) throws IOException, ParserConfigurationException, SAXException {
        HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(url));
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "Up9YCrPxnduZfLunjJfD");
        headers.set("X-Naver-Client-Secret", "F0DHva9svj");
        request.setHeaders(headers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader((request.execute().parseAsString())));
        return builder.parse(inputSource);
    }

    public List<MovieDto> getMovies(String query) {
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


    public List<BookDto> getBooks(String query) {
        List<BookDto> bookList = new ArrayList<>();
        try {
            NodeList elements = getDocument(String.format(NAVER_BOOK_URL, query)).getElementsByTagName("item");
            for (int i = 0; i < elements.getLength(); i++) {
                bookList.add(new BookDto((Element) elements.item(i)));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
