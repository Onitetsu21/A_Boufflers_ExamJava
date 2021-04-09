package exam.java.examjava.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int bloodAlcohol = 0;

    @JsonBackReference(value = "bbq-person")
    @ManyToOne
    private Bbq bbq;

    @JsonManagedReference(value = "person-aliment")
    @OneToMany
    private List<Aliment> aliments;

    public Person() {}


    public Person(int id, String name, int bloodAlcohol) {
        this.id = id;
        this.name = name;
        this.bloodAlcohol = bloodAlcohol;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBloodAlcohol() {
        return this.bloodAlcohol;
    }

    public void setBloodAlcohol(int bloodAlcohol) {
        this.bloodAlcohol = bloodAlcohol;
    }

    public void drinkAlcohol(int bloodAlcohol){
        this.bloodAlcohol = bloodAlcohol +1;
    }

    public Bbq getBbq() {
        return this.bbq;
    }

    public void setBbq(Bbq bbq) {
        this.bbq = bbq;
    }

    public List<Aliment> getAliments() {
        return this.aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

}

    
