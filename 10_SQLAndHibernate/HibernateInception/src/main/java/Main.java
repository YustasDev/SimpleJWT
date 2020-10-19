import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.MetadataSource;

public class Main {

  public static void main(String[] args) {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();

    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    Session session = sessionFactory.openSession();

    Course course = session.get(Course.class, 1);

    Session session1 = sessionFactory.openSession();

    String hql = "SELECT studentsCount " + " From " + Course.class.getSimpleName() + " WHERE id = 1";

    Integer countStudents = (Integer) session1.createQuery(hql).getSingleResult();

    System.out.println("На курсе:  " + course.getName() + "  обучаются: " + countStudents + " студентов");

    session.close();
    session1.close();
    sessionFactory.close();
  }
}