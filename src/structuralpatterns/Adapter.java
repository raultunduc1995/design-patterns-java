package structuralpatterns;

/**
 * Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.
 */
public class Adapter {
    public static void main(String[] args) {
        Source dbSourceAdapter = new DBSourceAdapter(new DBSource());
        Source remoteSource = new RemoteSource();
        System.out.println("Remote source client: " + remoteSource.getClient().name());
        System.out.println("DB source client: " + dbSourceAdapter.getClient().name());
    }

    interface Source {
        Client getClient();
    }

    record Client(String name) {
    }

    record DBClient(String name, int age) {
    }

    static class RemoteSource implements Source {

        @Override
        public Client getClient() {
            return new Client("name");
        }
    }

    static class DBSource {
        public DBClient getDBClient() {
            return new DBClient("db-client-name", 23);
        }
    }

    static class DBSourceAdapter implements Source {
        private final DBSource dbSource;

        public DBSourceAdapter(DBSource dbSource) {
            this.dbSource = dbSource;
        }

        @Override
        public Client getClient() {
            DBClient dbClient = dbSource.getDBClient();
            Client client = new Client(dbClient.name());
            return client;
        }
    }
}
