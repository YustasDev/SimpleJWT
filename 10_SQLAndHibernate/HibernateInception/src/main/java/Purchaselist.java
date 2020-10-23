import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "purchaselist")
public class Purchaselist {

  @Column(name = "student_name")
  private String StudentName;

  @Column(name = "course_name")
  private String CourseName;

  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Purchaselist() {
  }

  public Purchaselist(String studentName, String courseName, int price,
      Date subscriptionDate) {
    StudentName = studentName;
    CourseName = courseName;
    this.price = price;
    this.subscriptionDate = subscriptionDate;
  }

  public String getStudentName() {
    return StudentName;
  }

  public void setStudentName(String studentName) {
    StudentName = studentName;
  }

  public String getCourseName() {
    return CourseName;
  }

  public void setCourseName(String courseName) {
    CourseName = courseName;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }
}
