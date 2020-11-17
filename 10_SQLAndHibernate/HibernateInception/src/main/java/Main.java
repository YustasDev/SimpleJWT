import java.util.HashSet;
import java.util.Iterator;
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

    Course requiredСourse = session.get(Course.class, 1);

    int countStudents = requiredСourse.getSubscriptionList().size();
    List<Subscription> priorStudentsList = requiredСourse.getSubscriptionList();

    System.out.println("На курсе: " + requiredСourse.getName());

    System.out.println("Обучаются  " + countStudents + " студентов:");

    for (Subscription student : priorStudentsList) {
      System.out.println(student.getStudent().getName());
    }

    System.out.println("Преподаватель группы:  " + requiredСourse.getTeacher().getName());

    System.out.println("Перечень курсов, которые ведет этот же преподаватель:  ");

    Set<Course> collectionCourses = requiredСourse.getTeacher().getCourseSet();

    Iterator<Course> iterator = collectionCourses.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next().getName());
    }

    Course requiredСourse2 = session.get(Course.class, 2);
    Integer countStudents2 = requiredСourse2.getStudentsCount();
    List<Student> studentList2 = requiredСourse2.getStudents();

    System.out.println("На курсе: " + requiredСourse2.getName());
    System.out.println("Обучаются  " + countStudents + " студентов:");
    studentList2.forEach(st -> System.out.println(st.getName()));

    Student studentIdOne = session.get(Student.class, 1);
    List<Course> coursesOfOneStudent = studentIdOne.getCourses();

    System.out.println("Студент: " + studentIdOne.getName() + "  обучается на следующих курсах:");
    coursesOfOneStudent.forEach(cs -> System.out.println(cs.getName()));


    session.close();
    sessionFactory.close();
  }
}
