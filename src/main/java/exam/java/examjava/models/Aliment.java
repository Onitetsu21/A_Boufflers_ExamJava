package exam.java.examjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Aliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private TypeOfAliment type;

    @JsonBackReference(value = "person-aliment")
    @ManyToOne
    private Person person;

    @JsonBackReference(value = "bbq-aliment")
    @ManyToOne
    private Bbq bbq;


    public Aliment() {}


    public Aliment(int id, String name, TypeOfAliment type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public TypeOfAliment getType() {
        return this.type;
    }

    public void setType(TypeOfAliment type) {
        this.type = type;
    }
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Bbq getBbq() {
        return this.bbq;
    }

    public void setBbq(Bbq bbq) {
        this.bbq = bbq;
    }

}

    
