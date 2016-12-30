package ar.edu.undav.semillero.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

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
        LOGGER.info("Heroku datasource -> " + this.herokuDatasourceURL);

        HerokuDatasourceParser herokuDatasourceParser = new HerokuDatasourceParser();
        herokuDatasourceParser.parseCompleteURL(this.herokuDatasourceURL);

        return new DriverManagerDataSource(herokuDatasourceParser.getUrl(),
                herokuDatasourceParser.getUsername(), herokuDatasourceParser.getPassword());
    }
}
