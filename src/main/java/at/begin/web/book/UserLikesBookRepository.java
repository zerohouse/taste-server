package at.begin.web.book;

import at.begin.web.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesBookRepository extends JpaRepository<UserLikesBook, Long> {
    void deleteByUserAndBook(User user, Book book);

    UserLikesBook findByUserAndBook(User user, Book book);
}
