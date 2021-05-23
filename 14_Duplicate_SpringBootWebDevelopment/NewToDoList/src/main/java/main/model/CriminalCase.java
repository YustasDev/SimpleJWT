package main.model;

public class CriminalCase {

    private Long id;
    private Integer numberCase;
    private String descriptionCase;

    public CriminalCase() {
    }

    public CriminalCase(Long id, Integer numberCase, String descriptionCase) {
        this.id = id;
        this.numberCase = numberCase;
        this.descriptionCase = descriptionCase;
    }

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

    @Override
    public String toString() {
        return "CriminalCase{" +
                "id=" + id +
                ", numberCase=" + numberCase +
                ", descriptionCase='" + descriptionCase + '\'' +
                '}';
    }
}
