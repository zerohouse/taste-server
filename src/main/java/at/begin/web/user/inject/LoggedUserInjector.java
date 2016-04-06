package at.begin.web.user.inject;

import at.begin.infra.exception.LoginNeededException;
import at.begin.web.user.User;
import at.begin.web.user.UserRepository;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoggedUserInjector implements HandlerMethodArgumentResolver {

    private static final String USER = "user";

    private UserRepository userRepository;

    public LoggedUserInjector(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(Logged.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        boolean makeException = methodParameter.getParameterAnnotation(Logged.class).makeLoginNeededException();
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return nullHandling(makeException);
        }
        Long id = (Long) session.getAttribute(USER);
        if (id == null) {
            return nullHandling(makeException);
        }
        User user = userRepository.findOne(id);
        if (user == null) {
            return nullHandling(makeException);
        }
        return user;
    }

    private Object nullHandling(boolean makeException) {
        if (makeException)
            throw new LoginNeededException();
        return null;
    }

    public static void setUser(HttpSession session, long id) {
        session.setAttribute(USER, id);
    }

    public static void removeSession(HttpSession session) {
        session.invalidate();
    }
}
