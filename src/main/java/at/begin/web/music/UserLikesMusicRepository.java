package at.begin.web.music;

import at.begin.web.movie.Movie;
import at.begin.web.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesMusicRepository extends JpaRepository<UserLikesMusic, Long> {
    void deleteByUserAndMusic(User user, Music music);

    UserLikesMusic findByUserAndMusic(User user, Music music);
}
