import com.mysql.cj.protocol.ColumnDefinition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



@Entity
@Table(name = "Courses")
public class Course {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int duration;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "enum")
  private CourseType type;

  private String description;

  @Column (name = "teacher_id")
  private int teacherId;

  @Column (name = "students_count")
  private int studentsCount;

  private int price;

  @Column (name = "price_per_hour")
  private float pricePerHour;


}
