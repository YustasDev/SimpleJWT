import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Course {

  private int id;

  private String name;

  private int duration;

  private CourseType type;

  private String description;

  private int teacherId;

  private int studentsCount;

  private int price;

  private float pricePerHour;









}
