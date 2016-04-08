package at.begin.web.music;

import at.begin.web.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, String> {
}
