package app;

//PARAMETRIZOVANI Upiti -> 

import app.CloseableSession;
import app.HibernateUtil;
import app.model.Book;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ApplicationQuery {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(CloseableSession closeableSession = new CloseableSession(sessionFactory.openSession())){
            //parametrizovani sa like %%
            String parametar = "ostrvo";
            Session session = closeableSession.getSession();
            Query query = session.createQuery("from Book book where lower(book.title) like :input");
            query.setParameter("input", "%"+parametar.toLowerCase()+"%");
            List<Book> books = query.list();
            books.forEach(System.out::println);
        }catch(Exception he){
            throw new RuntimeException(he.getMessage());
        }
    }
}
