package at.begin.web.search;

import at.begin.ServerLauncher;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchServiceTest {
    SearchService searchService;

    @Before
    public void setup() {
        searchService = new SearchService();
        searchService.setup();
    }

    @Test
    public void testMovie() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(searchService.getMovies("매트릭스"));

    }

    @Test
    public void testBook() {
        System.out.print(searchService.getBooks("그림"));
        System.out.print(searchService.getMusics("그림"));
    }

    @Test
    public void testMusic() throws IOException, ParserConfigurationException, SAXException {
        Pattern pattern = Pattern.compile("movieId=([0-9]+)");
        Matcher matcher = pattern.matcher("http://movie.daum.net/moviedetailVideoView.do?movieId=79008&videoId=41496");
        if (matcher.find())
            System.out.print(matcher.group(1));
    }

}