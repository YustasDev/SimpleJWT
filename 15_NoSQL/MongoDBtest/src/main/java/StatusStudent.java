

public class StatusStudent {

  private String nameStudent;
  private Integer ageStudent;
  private String coursesStudent;

  public StatusStudent() {
  }

  public StatusStudent(String nameStudent, Integer ageStudent, String coursesStudent) {
    this.nameStudent = nameStudent;
    this.ageStudent = ageStudent;
    this.coursesStudent = coursesStudent;
  }

  public String getNameStudent() {
    return nameStudent;
  }

  public void setNameStudent(String nameStudent) {
    this.nameStudent = nameStudent;
  }

  public Integer getAgeStudent() {
    return ageStudent;
  }

  public void setAgeStudent(Integer ageStudent) {
    this.ageStudent = ageStudent;
  }

  public String getCoursesStudent() {
    return coursesStudent;
  }

  public void setCoursesStudent(String coursesStudent) {
    this.coursesStudent = coursesStudent;
  }


  public String toString() {
    return "Student: " + nameStudent + "  Age = " + ageStudent + "  Courses =  " + coursesStudent;
  }
}
