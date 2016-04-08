package at.begin.web.music;


import at.begin.infra.response.JsonResponse;
import at.begin.web.movie.MovieDto;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    MusicService musicService;

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse addMusic(@Logged User user, @RequestBody MusicDto musicDto) {
        return musicService.addToCollection(user, musicDto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse removeMusic(@Logged User user, String id) {
        return musicService.removeFromCollection(user, id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public JsonResponse updateComment(@Logged User user, String id, String comment) {
        return musicService.updateComment(user, id, comment);
    }

}
