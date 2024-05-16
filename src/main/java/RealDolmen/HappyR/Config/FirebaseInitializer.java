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

@Configuration
public class FirebaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    @PostConstruct
    public void firebaseInit() {
        try {
            ClassPathResource resource = new ClassPathResource("static/happyr-32a1e-firebase-adminsdk-jmcb5-220b19d870.json");

            FirebaseOptions options = new FirebaseOptions
                    .Builder()
                    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                    .build();
            FirebaseApp.initializeApp(options);
            logger.info("Firebase initialized successfully.");
        } catch (IOException e) {
            logger.error("Error initializing Firebase: {}", e.getMessage());
        }
    }
}
