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
public class Person implements AlcoholDrinkers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int bloodAlcohol = 0;
    private String status = "Fine";

    @JsonBackReference(value = "bbq-person")
    @ManyToOne
    private Bbq bbq;

    @JsonManagedReference(value = "person-aliment")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aliment> aliments;

    public Person() {}


    public Person(int id, String name, int bloodAlcohol, String status) {
        this.id = id;
        this.name = name;
        this.bloodAlcohol = bloodAlcohol;
        this.status = status;
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

    @Override
    public void drinkAlcohol(int bloodAlcohol){
        this.bloodAlcohol = bloodAlcohol +1;
        if (this.bloodAlcohol == 3){
            this.beDrunk();
        }
    }

    @Override
    public void beDrunk(){
        this.status = "Drunk";
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    
