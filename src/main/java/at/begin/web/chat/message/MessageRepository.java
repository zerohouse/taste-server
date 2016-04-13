package at.begin.web.chat.message;

import at.begin.web.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
