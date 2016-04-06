package at.begin.search.movie.naver;

import at.begin.web.movie.MovieDto;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
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

@Service
public class NaverMovieSearchService {

    HttpTransport httpTransport;
    HttpRequestFactory httpRequestFactory;

    @PostConstruct
    public void setup() {
        httpTransport = new NetHttpTransport();
        httpRequestFactory = httpTransport.createRequestFactory();
    }

    public Document getDocument(String query) throws IOException, ParserConfigurationException, SAXException {
        HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(String.format("https://openapi.naver.com/v1/search/movie.xml?display=100&query=%s", query)));
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
            NodeList elements = getDocument(query).getElementsByTagName("item");
            for (int i = 0; i < elements.getLength(); i++) {
                movieList.add(new MovieDto((Element) elements.item(i)));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return movieList;
    }

}
