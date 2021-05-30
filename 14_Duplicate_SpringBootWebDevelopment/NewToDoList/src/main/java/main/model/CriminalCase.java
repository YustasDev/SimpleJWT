package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CriminalCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Integer numberCase;
    private String descriptionCase;

    public CriminalCase(Integer numberCase, String descriptionCase) {
    }

    public CriminalCase(int id, Integer numberCase, String descriptionCase) {
        this.id = id;
        this.numberCase = numberCase;
        this.descriptionCase = descriptionCase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
