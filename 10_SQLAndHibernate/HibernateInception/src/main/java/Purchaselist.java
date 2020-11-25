import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "purchaselist")
@IdClass(Purchaselist.CompositeKey.class)
public class Purchaselist {

    @Embeddable
    public static class CompositeKey implements Serializable { // encapsulates composite key

      static final long serialVersionUID = 1L;

      @Column(name = "student_name")
      private String studentName;
      @Column(name = "course_name")
      private String courseName;

      public CompositeKey() {
      }

      public CompositeKey(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        CompositeKey id = (CompositeKey) o;
        return courseName.equals(id.courseName) &&
            studentName.equals(id.studentName);
      }

      @Override
      public int hashCode() {
        int result = studentName.hashCode();
        result = 13 * result + courseName.hashCode();
        return result;
      }

      public String getCourseName() {
        return courseName;
      }

      public void setCourseName(String courseName) {
        this.courseName = courseName;
      }

      public String getStudentName() {
        return studentName;
      }

      public void setStudentName(String studentName) {
        this.studentName = studentName;
      }
    }

  @Id
  @Column(name = "student_name", nullable = false, insertable = false, updatable = false )
  private String studentName;

  @Id
  @Column(name = "course_name", nullable = false, insertable = false, updatable = false)
  private String courseName;

  private int price;

  @Column(name = "subscription_date", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull
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
  @Override
  public String toString() {
    return "Purchaselist{" +
        "studentName='" + studentName + '\'' +
        ", courseName='" + courseName + '\'' +
        ", price=" + price +
        ", subscriptionDate=" + subscriptionDate +
        '}';
  }

}
