package exam.java.examjava.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Bbq extends Place{
    
    
    private LocalDate date;

    @JsonManagedReference(value = "bbq-person")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> persons;

    @JsonManagedReference(value = "bbq-aliment")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aliment> aliments;

    public Bbq() {}

    public Bbq(int id, LocalDate date, String address, String city, String country) {
        super(id, address, city, country);
        this.date = date;
        this.persons = new ArrayList<>();
        this.aliments = new ArrayList<>();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Aliment> getAliments() {
        return this.aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }
}
