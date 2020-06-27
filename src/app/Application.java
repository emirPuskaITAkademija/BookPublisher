package app;

import app.model.Book;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Application {

    public static void main(String[] args) {
        //JPA -> EntityManager  -> EntityManagerFactory
        //Hibernate -> Session -> SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (CloseableSession closeableSession = new CloseableSession(sessionFactory.openSession())) {
            //SQL : select *from book
            //HQL: from Book
            Session session = closeableSession.getSession();
            //OTVARAMO TRANSAKCIJU
            session.getTransaction().begin();
            //UPDATE
            Query updateQuery = session.createQuery("update Book book set book.pages=book.pages+1 where book.id>1");
            int brojIzmijenjenihRecorda = updateQuery.executeUpdate();
            System.out.println("Izmijenjeno = " + brojIzmijenjenihRecorda);
            //DELETE
            Query deleteQuery = session.createQuery("delete Book book where book.id=8");
            int brojIzbrisanihRecorda = deleteQuery.executeUpdate();
            System.out.println("Izbrisano = " + brojIzbrisanihRecorda);
            //COMMIT TANSAKCIJE
            session.getTransaction().commit();
            Query query = session.createQuery("select book.title,book.pages from Book book");
            List<Object[]> bookElements = query.list();
            for(Object[] properties: bookElements){
                for(Object property: properties){
                    System.out.print(property + "   ");
                }
                System.out.println();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        System.exit(0);
    }
}
