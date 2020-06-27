package app;

import org.hibernate.Session;

public class CloseableSession implements AutoCloseable {

    private final Session session;

    public CloseableSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Poziva se close...");
        session.close();
    }
}
