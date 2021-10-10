package org.nekosoft.pdffer.props;

import java.util.Properties;

/**
 * A Property POJO part that represents SMTP server information.
 */
public class SmtpServerInfo {
    // field declarations
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final Properties javaMailProperties;

    /**
     * Instantiates a new SMTP server info.
     *
     * @param host               the host
     * @param port               the port
     * @param username           the username
     * @param password           the password
     * @param javaMailProperties any extra options as a Java Properties instance
     */
    public SmtpServerInfo(String host, int port, String username, String password, Properties javaMailProperties) {
        // set field values
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.javaMailProperties = javaMailProperties;
    }

    // getters only

    /**
     * Gets the host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the port.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets extra options as a Java Properties instance.
     *
     * @return extra options as a Java Properties instance
     */
    public Properties getJavaMailProperties() {
        return javaMailProperties;
    }
}
