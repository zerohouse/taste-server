package at.begin.web.user;

import at.begin.infra.response.JsonResponse;
import at.begin.web.user.inject.LoggedUserInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static at.begin.infra.response.JsonResponseFactory.successResponse;
import static at.begin.infra.response.JsonResponseFactory.warningResponse;


@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public JsonResponse isExistUser(String email) {
        return successResponse(null != userRepository.findByEmail(email));
    }

    JsonResponse login(User user, HttpSession session) {
        User fromDB = userRepository.findByEmail(user.getEmail());
        if (fromDB == null)
            return warningResponse("가입하지 않은 이메일입니다.");
        if (!fromDB.matchPassword(passwordEncoder, user.getPassword()))
            return warningResponse("패스워드가 다릅니다.");
        LoggedUserInjector.setUser(session, fromDB.getId());
        return successResponse(new UserDto(fromDB));
    }

    JsonResponse register(User user, HttpSession session) {
        user.encryptPassword(passwordEncoder);
        userRepository.save(user);
        LoggedUserInjector.setUser(session, user.getId());
        return successResponse(new UserDto(user));
    }

//    public JsonResponse passwordRedefine(User user, HttpSession session) {
//        User fromDB = userRepository.findByEmail(user.getEmail());
//        if(!fromDB.getPhone().equals(user.getPhone()))
//            return errorResponse("유효하지 않은 접근입니다.");
//        if (!phoneAuthService.checkKeyAndUpdate(user.getPhone(), user.getAuth()))
//            return errorResponse("유효하지 않은 접근입니다.");
//        fromDB.setPassword(user.getPassword());
//        fromDB.encryptPassword(passwordEncoder);
//        LoggedUserInjector.setUser(session, fromDB.getId());
//        return successResponse(new UserDto(fromDB));
//    }

//    public JsonResponse update(User user, String name) {
//        if(name == null)
//            return errorResponse("값이 없습니다.");
//        user.setName(name);
//        return successResponse(userRepository.save(user));
//    }
}
