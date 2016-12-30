package ar.edu.undav.semillero.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * Juan Lagostena on 30/12/16
 * .
 */
public class HerokuDatasourceParserTest {


    @Test
    public void parseCompleteURL() throws Exception {

        String username = "pepe";
        String password = "bfw8wr0oss0uw3k1";
        String host = "mcldisu5rtyu29wf.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";
        int port = 3306;
        String schema = "lyti5ouskt6frrrr";
        String driver = "mariadb";

        String completeURL = "jdbc:" + driver + "://" + username + ":" + password + "@" + host + ":" + port + "/" + schema;
        HerokuDatasourceParser parser = new HerokuDatasourceParser();
        parser.parseCompleteURL(completeURL);

        String jdbcUrl = "jdbc:" + driver + "://" + host + ":" + port + "/" + schema;

        Assert.assertEquals(username, parser.getUsername());
        Assert.assertEquals(password, parser.getPassword());
        Assert.assertEquals(jdbcUrl, parser.getUrl());
    }

}