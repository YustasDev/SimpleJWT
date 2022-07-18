package models;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lemma")
public class Lemma implements Serializable {

  @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Column(name = "lemma", nullable = false)
    @NotNull
    private String lemma;

    @Column(name = "frequency", nullable = false)
    @NotNull
    private Integer frequency;

    public Lemma() {}

    public Lemma(String lemma, Integer frequency) {
    this.lemma = lemma;
    this.frequency = frequency;
    }

    public Lemma(Integer id, String lemma, Integer frequency) {
    this.id = id;
    this.lemma = lemma;
    this.frequency = frequency;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getLemma() {
      return lemma;
    }

    public void setLemma(String lemma) {
      this.lemma = lemma;
    }

    public Integer getFrequency() {
      return frequency;
    }

    public void setFrequency(Integer frequency) {
      this.frequency = frequency;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Lemma)) {
      return false;
    }
    Lemma lemma1 = (Lemma) o;
    return getId().equals(lemma1.getId()) &&
        getLemma().equals(lemma1.getLemma()) &&
        getFrequency().equals(lemma1.getFrequency());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getLemma(), getFrequency());
  }

  @Override
  public String toString() {
    return "Lemma{" +
        "id=" + id +
        ", lemma='" + lemma + '\'' +
        ", frequency=" + frequency +
        '}';
  }


}
