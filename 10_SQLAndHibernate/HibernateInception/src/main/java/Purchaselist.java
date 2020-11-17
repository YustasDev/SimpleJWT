import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "purchaselist")
public class Purchaselist {
  

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "course_name")
  private String courseName;

  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Purchaselist() {
  }

  public Purchaselist(String studentName, String courseName, int price,
      Date subscriptionDate) {
    this.studentName = studentName;
    this.courseName = courseName;
    this.price = price;
    this.subscriptionDate = subscriptionDate;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
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
