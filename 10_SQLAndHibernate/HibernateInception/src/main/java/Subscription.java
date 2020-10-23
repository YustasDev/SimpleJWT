import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "subscriptions")
public class Subscription {

  @ManyToOne(cascade = CascadeType.ALL)
  private Student student;

  @Column(name = "course_id")
  private Integer courseId;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Subscription() {
  }

  public Subscription(Student student, Integer courseId, Date subscriptionDate) {
    this.student = student;
    this.courseId = courseId;
    this.subscriptionDate = subscriptionDate;
  }

  public Student getStudentId() {
    return student;
  }

  public void setStudentId(Student student) {
    this.student = student;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }
}
