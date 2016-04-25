package at.begin.web.auth;

import at.begin.web.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneAuthRepository extends JpaRepository<PhoneAuth, Long> {
    PhoneAuth findFirstByPhoneOrderByIdDesc(String phone);
}
