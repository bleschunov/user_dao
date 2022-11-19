package main.models;

public enum DbName {
    MYSQL("mysql"), POSTGRES("postgres");

    private final String dbName;

    DbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
