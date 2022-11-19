package main.helpers;

import main.models.DbName;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static DbName dbName = DbName.POSTGRES;
    private static final String credentialsFile = "src/resources/%s/credentials.properties";
    private static final String sqlFile = "src/resources/%s/sql.properties";

    private static Properties _readProperties(String fileName) {
        Properties props = new Properties();

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    public static Properties readCredentials() {
        return _readProperties(String.format(credentialsFile, dbName.getDbName()));
    }

    public static Properties readSql() {
        return _readProperties(String.format(sqlFile, dbName.getDbName()));
    }

    public static void setDbName(DbName dbName) {
        PropertiesReader.dbName = dbName;
    }
}
