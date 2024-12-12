package org.example.soap.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static EnvConfig instance;
    private Dotenv dotenv;

    private EnvConfig() {
        dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    public static EnvConfig getInstance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    //Token

    public String getSecretToken() {
        return getProperty("SECRET_TOKEN");
    }
    //Mysql
    public String getUrlMysql() {
        return getProperty("MYSQL_URL");
    }

    public String getUserMysql() {
        return getProperty("MYSQL_USER");
    }

    public String getPasswordMysql() {
        return getProperty("MYSQL_PASSWORD");
    }

    private String getProperty(String key) {
        String property = System.getenv(key);
        if (property == null) {
            property = dotenv.get(key);
        }
        return property;
    }
}
