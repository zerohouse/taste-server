package at.begin.web.search;

import at.begin.infra.response.JsonResponse;
import at.begin.search.movie.naver.NaverMovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static at.begin.infra.response.JsonResponseFactory.successResponse;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    NaverMovieSearchService naverMovieSearchService;

    @RequestMapping("/movie")
    public JsonResponse searchMovie(String query) {
        return successResponse(naverMovieSearchService.getMovies(query));
    }

}
