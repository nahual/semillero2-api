package ar.edu.undav.semillero;

import ar.edu.undav.semillero.config.HerokuAwareDataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(HerokuAwareDataSourceProperties.class)
public class SemilleroAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemilleroAPIApplication.class, args);
    }
}
