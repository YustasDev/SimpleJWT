import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Subscriptions")
@org.hibernate.annotations.Immutable
public class Subscription {

  @Embeddable
  public static class Id implements Serializable { // encapsulates composite key

    @Column(name = "course_id")
    protected Integer courseId;
    @Column(name = "student_id")
    protected Integer studentId;

    public Id() {
    }

    public Id(Integer courseId, Integer studentId) {
      this.courseId = courseId;
      this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Id id = (Id) o;
      return courseId.equals(id.courseId) &&
          studentId.equals(id.studentId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(courseId, studentId);
    }

    public Integer getCourseId() {
      return courseId;
    }

    public void setCourseId(Integer courseId) {
      this.courseId = courseId;
    }

    public Integer getStudentId() {
      return studentId;
    }

    public void setStudentId(Integer studentId) {
      this.studentId = studentId;
    }
  }

  @EmbeddedId
  protected Id id = new Id();

  @ManyToOne
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;

  @Column(name = "subscription_date", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
  private Date subscriptionDate;

  public Subscription() {
  }

  public Subscription(Student student, Course course, Date subscriptionDate) {
    this.student = student;
    this.course = course;
    this.subscriptionDate = subscriptionDate;
  }

  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }
}
