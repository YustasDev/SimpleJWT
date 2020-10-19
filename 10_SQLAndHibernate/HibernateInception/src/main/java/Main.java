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

    CriteriaBuilder builder = session1.getCriteriaBuilder();

    CriteriaQuery<Course> query = builder.createQuery(Course.class);

    Root<Course> root = query.from(Course.class);

    query.select(root).where((builder.equal(root.<Integer>get("studentsCount")
            ......



    System.out.println("На курсе:  " + course.getName() + "  обучаются: " + countStudents);




    sessionFactory.close();

  }
}