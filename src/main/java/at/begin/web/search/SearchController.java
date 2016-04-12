package at.begin.web.search;

import at.begin.infra.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static at.begin.infra.response.JsonResponseFactory.successResponse;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("/movie")
    public JsonResponse searchMovie(String query) {
        return successResponse(searchService.getMovies(query));
    }

    @RequestMapping("/movie/text")
    public JsonResponse searchMovieKeyword(String query) {
        return successResponse(searchService.getMoviesKeyword(query));
    }

    @RequestMapping("/book")
    public JsonResponse searchBook(String query) {
        return successResponse(searchService.getBooks(query));
    }

    @RequestMapping("/music")
    public JsonResponse searchMusic(String query) {
        return successResponse(searchService.getMusics(query));
    }

}
