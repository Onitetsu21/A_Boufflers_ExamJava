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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String artiste;
    private String track;

    @JsonBackReference(value = "bbq-person")
    @ManyToOne
    private Bbq bbq;

    public Music() {
    }

    public Music(int id, String artiste, String track) {
        this.id = id;
        this.artiste = artiste;
        this.track = track;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtiste() {
        return this.artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getTrack() {
        return this.track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

}
