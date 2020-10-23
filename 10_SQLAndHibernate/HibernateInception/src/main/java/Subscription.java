import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "subscriptions")
public class Subscription {

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private Integer studentId;

  @Column(name = "course_id")
  private Integer courseId;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Subscription() {
  }

  public Subscription(Integer studentId, Integer courseId, Date subscriptionDate) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.subscriptionDate = subscriptionDate;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer student) {
    this.studentId = studentId;
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
