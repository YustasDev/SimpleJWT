import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

  @Entity
  @Table(name = "LinkedPurchaselist")
  @org.hibernate.annotations.Immutable
  public class LinkedPurchaselist {

    @Embeddable
    public static class Id implements Serializable { // encapsulates composite key

      @Column(name = "course_id")
      private Integer courseId;
      @Column(name = "student_id")
      private Integer studentId;

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
        int result = studentId.hashCode();
        result = 13 * result + courseId.hashCode();
        return result;
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
    private Id id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;


    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    public LinkedPurchaselist() {
    }

    public LinkedPurchaselist(Id id, Integer studentId, Integer courseId) {
      this.id = id;
      this.studentId = studentId;
      this.courseId = courseId;
    }

    public Id getId() {
      return id;
    }

    public void setId(Id id) {
      this.id = id;
    }

    public Integer getStudentId() {
      return studentId;
    }

    public void setStudentId(Integer studentId) {
      this.studentId = studentId;
    }

    public Integer getCourseId() {
      return courseId;
    }

    public void setCourseId(Integer courseId) {
      this.courseId = courseId;
    }

    @Override
    public String toString() {
      return "LinkedPurchaselist{" +
          "id=" + id +
          ", studentId=" + studentId +
          ", courseId=" + courseId +
          '}';
    }
  }
