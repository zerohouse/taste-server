package at.begin.search.movie.naver;

import at.begin.ServerLauncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ServerLauncher.class)
public class NaverMovieSearchServiceTest {
    @Autowired
    NaverMovieSearchService naverMovieSearchService;

    @Test
    public void test() throws IOException, ParserConfigurationException, SAXException {
        System.out.print(naverMovieSearchService.getMovies("그림"));
    }


}