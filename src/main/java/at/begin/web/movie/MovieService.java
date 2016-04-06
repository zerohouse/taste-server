package at.begin.web.movie;

import at.begin.infra.response.JsonResponse;
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
    UserLikesMovieRepository userLikesMovieRepository;


    public JsonResponse addToCollection(User user, MovieDto movieDto) {
        Movie movie = movieRepository.findOne(movieDto.id);
        if (movie == null) {
            movie = new Movie(movieDto);
            movieRepository.save(movie);
        }
        UserLikesMovie userLikesMovie = new UserLikesMovie(user, movie);
        userLikesMovieRepository.save(userLikesMovie);
        return successResponse(new MovieDto(userLikesMovie));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Movie movie = getEmptyMovie(id);
        userLikesMovieRepository.deleteByUserAndMovie(user, movie);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Movie movie = movieRepository.findOne(id);
        UserLikesMovie userLikesMovie = userLikesMovieRepository.findByUserAndMovie(user, movie);
        userLikesMovie.setComment(comment);
        userLikesMovieRepository.save(userLikesMovie);
        return successResponse();
    }


    private Movie getEmptyMovie(String id) {
        Movie movie = new Movie();
        movie.setId(id);
        return movie;
    }
}
