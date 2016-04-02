package at.begin.infra.config;

import at.begin.infra.inject.findbyid.findbyid.FindByIdInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter  {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new FindByIdInjector(applicationContext));
    }

}
