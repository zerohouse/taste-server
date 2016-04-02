package at.begin.product.user.inject;

import at.begin.product.user.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class LoggedUserInjectConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private UserRepository userRepository;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoggedUserInjector(userRepository));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        userRepository = beanFactory.getBean(UserRepository.class);
    }

}
