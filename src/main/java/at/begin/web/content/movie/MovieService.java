package at.begin.web.content.movie;

import at.begin.infra.response.JsonResponse;
import at.begin.web.content.UserLikesContent;
import at.begin.web.content.UserLikesContentRepository;
import at.begin.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static at.begin.infra.response.JsonResponseFactory.successResponse;

@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserLikesContentRepository userLikesContentRepository;


    public JsonResponse addToCollection(User user, MovieDto movieDto) {
        Movie movie = movieRepository.findOne(movieDto.id);
        if (movie == null) {
            movie = new Movie(movieDto);
            movieRepository.save(movie);
        }
        UserLikesContent userLikesMovie = new UserLikesContent(user, movie);
        userLikesContentRepository.save(userLikesMovie);
        return successResponse(new MovieDto(userLikesMovie));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Movie movie = getEmptyMovie(id);
        userLikesContentRepository.deleteByUserAndMovie(user, movie);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Movie movie = movieRepository.findOne(id);
        UserLikesContent userLikesContent = userLikesContentRepository.findByUserAndMovie(user, movie);
        userLikesContent.setComment(comment);
        userLikesContentRepository.save(userLikesContent);
        return successResponse();
    }


    private Movie getEmptyMovie(String id) {
        Movie movie = new Movie();
        movie.setId(id);
        return movie;
    }
}
