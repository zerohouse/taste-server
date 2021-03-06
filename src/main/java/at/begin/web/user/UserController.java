package at.begin.web.user;

import at.begin.infra.response.JsonResponse;
import at.begin.infra.response.JsonResponseFactory;
import at.begin.infra.response.ResultStatus;
import at.begin.web.user.inject.Logged;
import at.begin.web.user.inject.LoggedUserInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/count")
    public JsonResponse count() {
        return userService.count();
    }

    @RequestMapping("/login")
    public JsonResponse isExistUser(User user, HttpSession session) {
        return userService.login(user, session);
    }

    @RequestMapping("/logout")
    public JsonResponse isExistUser(HttpSession session) {
        LoggedUserInjector.removeSession(session);
        return JsonResponseFactory.successResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse registerUser(User user, HttpSession session) {
        return userService.register(user, session);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResponse updateUser(@Logged User user, User updated, String auth) {
        return userService.update(user, updated, auth);
    }


    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public JsonResponse getSessionUser(@Logged(makeLoginNeededException = false) User user) {
        if (user == null)
            return new JsonResponse(ResultStatus.NO_RESULT);
        return JsonResponseFactory.successResponse(new UserDto(user));
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonResponse getUser(String email) {
        return userService.getUser(email);
    }


}
