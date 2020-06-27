package app;

import app.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class CriteriaQuery {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (CloseableSession closeableSession = new CloseableSession(sessionFactory.openSession())) {
            Session session = closeableSession.getSession();
            Criteria kriterij = session.createCriteria(Book.class);
            Criterion criterion = Restrictions.eq("title", "Tvrdava");
            kriterij.add(criterion);
            Book book = (Book) kriterij.uniqueResult();
            System.out.println(book);
        } catch (Exception he) {
            throw new RuntimeException(he.getMessage());
        }
        System.exit(0);
    }
}
