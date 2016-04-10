package at.begin.web.content;

import at.begin.web.content.book.Book;
import at.begin.web.content.movie.Movie;
import at.begin.web.content.music.Music;
import at.begin.web.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesContentRepository extends JpaRepository<UserLikesContent, Long> {
    void deleteByUserAndBook(User user, Book book);

    UserLikesContent findByUserAndBook(User user, Book book);

    void deleteByUserAndMovie(User user, Movie movie);

    void deleteByUserAndMusic(User user, Music music);

    UserLikesContent findByUserAndMusic(User user, Music music);

    UserLikesContent findByUserAndMovie(User user, Movie movie);
}
