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
    private Id id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;

    public LinkedPurchaselist() {
    }

    public LinkedPurchaselist(Id id, Long studentId, Long courseId) {
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

    public Long getStudentId() {
      return studentId;
    }

    public void setStudentId(Long studentId) {
      this.studentId = studentId;
    }

    public Long getCourseId() {
      return courseId;
    }

    public void setCourseId(Long courseId) {
      this.courseId = courseId;
    }
  }
