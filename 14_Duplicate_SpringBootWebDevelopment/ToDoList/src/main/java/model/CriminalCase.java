package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CriminalCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int number;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CriminalCase)) return false;
        CriminalCase that = (CriminalCase) o;
        return getId() == that.getId() &&
                getNumber() == that.getNumber() &&
                getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getDescription());
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "id= " + id +
                ", number= " + number +
                ", description= '" + description + '\'' +
                '}';
    }
}
