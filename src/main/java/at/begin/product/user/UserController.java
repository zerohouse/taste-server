//package at.begin.product.user;
//
//import at.begin.infra.response.JsonResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping("/api/v1/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @RequestMapping("/exist")
//    public JsonResponse isExistUser(String email) {
//        return userService.isExistUser(email);
//    }
//
//    @RequestMapping("/login")
//    public JsonResponse isExistUser(User user, HttpSession session) {
//        return userService.login(user, session);
//    }
//
//    @RequestMapping("/logout")
//    public JsonResponse isExistUser(HttpSession session) {
//        LoggedUserInjector.removeSession(session);
//        return JsonResponseFactory.successResponse();
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public JsonResponse registerUser(User user, HttpSession session) {
//        return userService.register(user, session);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public JsonResponse registerUser(@Logged User user, String name) {
//        return userService.update(user, name);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/passwordRedefine")
//    public JsonResponse passwordRedefine(User user, HttpSession session) {
//        return userService.passwordRedefine(user, session);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public JsonResponse getUser(@Logged(makeLoginNeededException = false) User user) {
//        if (user == null)
//            return new JsonResponse(ResultStatus.NO_RESULT);
//        return JsonResponseFactory.successResponse(new UserDto(user));
//    }
//
//}
