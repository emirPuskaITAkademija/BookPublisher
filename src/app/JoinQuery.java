package app;

import app.model.Book;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class JoinQuery {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (CloseableSession closeableSession = new CloseableSession(sessionFactory.openSession())) {
            String hqlQuery = "select book.title,author.name from Book as book inner join book.authors as author";
            Session session = closeableSession.getSession();
            Query queryJoin = session.createQuery(hqlQuery);
            List<Object[]> resultList = queryJoin.list();
            for(Object[] properties : resultList){
                for(Object property: properties){
                    System.out.print(property+" ");
                }
                System.out.println();
            }
        } catch (Exception he) {
            throw new RuntimeException(he.getMessage());
        }
        System.exit(0);
    }
}
