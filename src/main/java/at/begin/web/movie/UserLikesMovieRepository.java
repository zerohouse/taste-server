package at.begin.web.movie;

import at.begin.web.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesMovieRepository extends JpaRepository<UserLikesMovie, Long> {
    void deleteByUserAndMovie(User user, Movie movie);

    UserLikesMovie findByUserAndMovie(User user, Movie movie);
}
