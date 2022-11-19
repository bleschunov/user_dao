package main.connectionFactory;

import main.connectionFactory.impl.CachedConnectionFactory;
import main.connectionFactory.impl.RawConnectionFactory;
import main.models.DbName;

public class ConnectionFactoryFactory {
    public enum FactoryType { RAW, CACHED }

    private static FactoryType factoryType = FactoryType.RAW;

    public static ConnectionFactory getConnectionFactory() {
        return switch (factoryType) {
            case CACHED -> new CachedConnectionFactory();
            default -> new RawConnectionFactory();
        };
    }

    public static void setFactoryType(FactoryType factoryType) {
        ConnectionFactoryFactory.factoryType = factoryType;
    }
}
