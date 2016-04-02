package at.begin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({PersistenceConfig.class, CacheConfig.class})
@ComponentScan(basePackages = "at.begin")
public class AppConfig {
}
