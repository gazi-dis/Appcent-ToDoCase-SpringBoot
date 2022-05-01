package appcentcase.todo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 1.05.2022
 */

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${application.allowed.client.adress}")
    private String clientAdress;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins(clientAdress);
    }
}

