package main.connectionFactory;

import main.helpers.PropertiesReader;

import java.util.Properties;

public abstract class AbstractConnectionFactory implements ConnectionFactory {
    protected final String url;
    protected final String login;
    protected final String password;

    protected AbstractConnectionFactory() {
        Properties props = PropertiesReader.readCredentials();
        url = props.getProperty("url");
        login = props.getProperty("login");
        password = props.getProperty("password");
    }
}
