package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {

    @Bean
    public Server server() { return new Server();}

    @Bean
    public AuthService authService() {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setUrl("jdbc:sqlite:users.db");
            ds.setDriverClassName("org.sqlite.JDBC");
            return new AuthService();
    }
}
