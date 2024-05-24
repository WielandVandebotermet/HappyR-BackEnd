package RealDolmen.HappyR.Auth0;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
// Import statements for required classes and libraries

// Configuration class for security settings
@Configuration
@EnableWebSecurity // Annotation to enable Spring Security features
public class SecurityConfig {

    // Value for the issuer URI obtained from application properties
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    // Bean definition for JWT decoder
    @Bean
    JwtDecoder jwtDecoder() {
        // Creating a Nimbus JWT decoder from the OIDC issuer location
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        // Configuring token validator with issuer and audience
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer);
        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    // Bean definition for security filter chain handling authentication for the '/auth/create' endpoint
    @Bean
    public SecurityFilterChain authCreateFilterChain(HttpSecurity http) throws Exception {
        // Configuring HTTP security
        http
                .csrf(csrf -> csrf.disable()) // Disabling CSRF protection
                .httpBasic(basic -> basic.disable()) // Disabling HTTP Basic authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configuring session management to be stateless
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/auth/create").permitAll() // Allowing access to '/auth/create' endpoint without authentication
                        .anyRequest().authenticated() // Requiring authentication for all other requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults()) // Configuring JWT authentication
                );
        return http.build();
    }
}
