package RealDolmen.HappyR.Auth0;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// Import statements for required classes and libraries

// Configuration class for CORS (Cross-Origin Resource Sharing)
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Method to configure CORS mappings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Adding CORS mappings for all endpoints
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}









