package RealDolmen.HappyR.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
// Import statements for required classes and libraries

// Configuration class responsible for initializing Firebase
@Configuration
public class FirebaseInitializer {

    // Logger for FirebaseInitializer class
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    // Method annotated with @PostConstruct to be executed after dependency injection is complete
    @PostConstruct
    public void firebaseInit() {
        try {
            // Loading the Firebase service account JSON file from the classpath
            ClassPathResource resource = new ClassPathResource("static/happyr-32a1e-firebase-adminsdk-jmcb5-220b19d870.json");

            // Building FirebaseOptions using the service account JSON file
            FirebaseOptions options = new FirebaseOptions
                    .Builder()
                    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                    .build();

            // Initializing FirebaseApp with the configured options
            FirebaseApp.initializeApp(options);

            // Logging successful initialization
            logger.info("Firebase initialized successfully.");
        } catch (IOException e) {
            // Logging error if initialization fails
            logger.error("Error initializing Firebase: {}", e.getMessage());
        }
    }
}
