package RealDolmen.HappyR.Config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
// Import statements for required classes and libraries

// Configuration class for DataSource configuration
@Configuration
public class DataSourceConfig {

    // Bean definition for configuring DataSource
    @Bean
    public DataSource getDataSource() {
        // Creating and configuring DataSource using DataSourceBuilder
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver") // Setting driver class name for MySQL
                .url("jdbc:mysql://localhost:3306/happyr?createDatabaseIfNotExist=true") // Setting database URL
                .username("Admin") // Setting database username
                .password("1234") // Setting database password
                .build(); // Building and returning the DataSource object
    }
}
