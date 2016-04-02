package at.begin.infra.inject.findbyid.findbyid;

import at.begin.infra.exception.DataNotExistException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class FindByIdInjector implements HandlerMethodArgumentResolver {

    private ApplicationContext applicationContext;

    public FindByIdInjector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(FindById.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        FindById findById = methodParameter.getParameterAnnotation(FindById.class);
        boolean makeException = findById.makeNotFoundException();
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String id = request.getParameter(findById.parameterName());
        if (id == null) {
            return nullHandling(makeException);
        }

        Object findId = findById.caster().cast(request.getParameter(findById.parameterName()));
        if (findId == null) {
            return nullHandling(makeException);
        }
        JpaRepository repository = applicationContext.getBean(findById.from());
        Object find = repository.findOne((Serializable) findId);
        if (find == null) {
            return nullHandling(makeException);
        }
        return find;
    }

    private Object nullHandling(boolean makeException) {
        if (makeException)
            throw new DataNotExistException();
        return null;
    }

}
