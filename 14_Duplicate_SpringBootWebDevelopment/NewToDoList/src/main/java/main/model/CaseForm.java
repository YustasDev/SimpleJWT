package main.model;

public class CaseForm {

  private Long id;
  private Integer numberCase;
  private String descriptionCase;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNumberCase() {
    return numberCase;
  }

  public void setNumberCase(Integer numberCase) {
    this.numberCase = numberCase;
  }

  public String getDescriptionCase() {
    return descriptionCase;
  }

  public void setDescriptionCase(String descriptionCase) {
    this.descriptionCase = descriptionCase;
  }
}
