package ar.edu.undav.semillero.config;

/**
 * Juan Lagostena on 30/12/16
 * .
 */
public class HerokuDatasourceParser {

    public static final String SEPARATOR = ":";
    public static final String SLASH = "/";

    private String url;
    private String username;
    private String password;

    public void parseCompleteURL(String url) {
        String[] urlFragments = url.split(SEPARATOR);

        this.username = urlFragments[2].substring(2);
        String[] passwordAndHostFragments = urlFragments[3].split("@");
        String host = passwordAndHostFragments[1];

        String[] portAndSchemaFragments = urlFragments[4].split(SLASH);

        this.password = passwordAndHostFragments[0];

        String port = portAndSchemaFragments[0];
        String schema = portAndSchemaFragments[1];

        StringBuffer buffer = new StringBuffer();
        this.url = buffer.append(urlFragments[0]).append(SEPARATOR).append(urlFragments[1]).append(SEPARATOR)
                .append(SLASH).append(SLASH).append(host).append(SEPARATOR).append(port).
                        append("/").append(schema).toString();

    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
