package RealDolmen.HappyR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HappyRApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyRApplication.class, args);
	}

}
