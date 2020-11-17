import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "students")
public class Student {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private int age;

  @Column(name = "registration_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date registrationDate;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
  private List<Subscription> subscriptionList;

  @ManyToMany(mappedBy = "students")
  List <Course> courses;

  public Student() {
  }

  public Student(Integer id, String name, int age, Date registrationDate) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.registrationDate = registrationDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }

  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }


}
