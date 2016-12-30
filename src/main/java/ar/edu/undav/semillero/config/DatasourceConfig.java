package ar.edu.undav.semillero.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Juan Lagostena on 30/12/16
 * jlagostena@bitsense.com.ar
 * .
 */
@Configuration
@Profile("heroku")
public class DatasourceConfig {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DatasourceConfig.class);

    @Value("${heroku.datasource.url}")
    private String herokuDatasourceURL;

    @Bean
    DataSource dataSource(){
        URI jdbUri = null;
        LOGGER.info("Heroku datasource -> " + this.herokuDatasourceURL);
        try {
            jdbUri = new URI(this.herokuDatasourceURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        return new DriverManagerDataSource(jdbUrl, username, password);
    }
}
