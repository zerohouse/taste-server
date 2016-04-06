package at.begin.search.movie.naver;

import at.begin.ServerLauncher;
import at.begin.web.music.MusicDto;
import at.begin.web.search.NaverSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ServerLauncher.class)
public class NaverSearchServiceTest {
    @Autowired
    NaverSearchService naverSearchService;

    @Test
    public void testMovie() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(naverSearchService.getMovies("그림"));
    }

    @Test
    public void testBook() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(naverSearchService.getBooks("그림"));
    }

    @Test
    public void testMusic() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(naverSearchService.getMusics("중식이"));
    }

}