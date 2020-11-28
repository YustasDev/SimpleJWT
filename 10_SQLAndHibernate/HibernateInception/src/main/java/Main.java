import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    Transaction transaction = session.beginTransaction();

    // =============================================================================================

    try {
      List<Purchaselist> purchaselistAll = session.createQuery("from Purchaselist").getResultList();
      List<Student> studentlist = session.createQuery("from Student").getResultList();
      List<Course> courseList = session.createQuery("from Course").getResultList();

      for (Purchaselist purchaselist : purchaselistAll) {
        LinkedPurchaselist linkedPurchaselist = new LinkedPurchaselist();
        LinkedPurchaselist.Id regId = new LinkedPurchaselist.Id();
        for (Student student : studentlist) {
          if (purchaselist.getStudentName().equals(student.getName())) {
            Integer studentIdForLinkedPurchaselist = student.getId();
            regId.setStudentId(studentIdForLinkedPurchaselist);
            linkedPurchaselist.setStudentId(studentIdForLinkedPurchaselist);
          }
        }
        for (Course course : courseList) {
          if (purchaselist.getCourseName().equals(course.getName())) {
            Integer courseIdForLinkedPurchaselist = course.getId();
            regId.setCourseId(courseIdForLinkedPurchaselist);
            linkedPurchaselist.setCourseId(courseIdForLinkedPurchaselist);
          }
        }
        linkedPurchaselist.setId(regId);
        session.save(linkedPurchaselist);
      }

      transaction.commit();
      session.close();
      sessionFactory.close();

    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("It's WRONG!");
    }
  }
}
