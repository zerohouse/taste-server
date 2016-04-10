package at.begin.web.content.movie;


import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse addMovie(@Logged User user, @RequestBody MovieDto movieDto) {
        return movieService.addToCollection(user, movieDto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse removeMovie(@Logged User user, String id) {
        return movieService.removeFromCollection(user, id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public JsonResponse updateComment(@Logged User user, String id, String comment) {
        return movieService.updateComment(user, id, comment);
    }

}
