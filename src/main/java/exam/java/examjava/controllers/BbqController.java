package exam.java.examjava.controllers;

import java.util.List;

import exam.java.examjava.dao.AlimentDao;
import exam.java.examjava.dao.BbqDao;
import exam.java.examjava.dao.PersonDao;
import exam.java.examjava.models.Aliment;
import exam.java.examjava.models.Bbq;
import exam.java.examjava.models.Person;
import exam.java.examjava.models.TypeOfAliment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bbqs")
public class BbqController {
    
    private final BbqDao bbqDao;
    private final PersonDao personDao;
    private final AlimentDao alimentDao;


    @Autowired
    public BbqController(BbqDao bbqDao, PersonDao personDao, AlimentDao alimentDao){
        this.bbqDao = bbqDao;
        this.personDao = personDao;
        this.alimentDao = alimentDao;
    }

    @GetMapping
    public ResponseEntity<List<Bbq>> getAllBbqs(){
        List<Bbq> bbqs = bbqDao.findAll();
        return new ResponseEntity<>(bbqs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bbq> getById(@PathVariable int id){
        Bbq bbq = bbqDao.findById(id);
        if(bbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bbq, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bbq> createBbq(@RequestBody Bbq bbq){
        bbqDao.save(bbq);
        return new ResponseEntity<>(bbq, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBbq(@PathVariable int id){
        Bbq bbq = bbqDao.findById(id);
        if(bbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bbqDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bbq> putBbq(@PathVariable int id, @RequestBody Bbq bbq){
        Bbq modifiedBbq = bbqDao.findById(id);

        if(modifiedBbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        modifiedBbq = bbqDao.save(bbq);

        return new ResponseEntity<>(modifiedBbq, HttpStatus.OK);
    }

    @PutMapping("/{bbqId}/persons/{personId}")
    public ResponseEntity<Bbq> addPersonInBbq(@PathVariable int bbqId, @PathVariable int personId){
        Bbq bbq = bbqDao.findById(bbqId);
        if(bbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Person person = personDao.findById(personId);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bbq.getPersons().add(person);
        person.setBbq(bbq);
        bbq.setId(bbqId);
        person.setId(personId);
        personDao.save(person);
        bbqDao.save(bbq);
        return new ResponseEntity<>(bbq, HttpStatus.OK);
    }

    @PutMapping("/{bbqId}/persons/{personId}/aliments/{alimentId}")
    public ResponseEntity<Bbq> consumnAliment(@PathVariable int bbqId, @PathVariable int personId, @PathVariable int alimentId){
        Bbq bbq = bbqDao.findById(bbqId);
        if(bbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Person person = personDao.findById(personId);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Aliment aliment = alimentDao.findById(alimentId);
        if(aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(aliment.getType() == TypeOfAliment.ALCOHOL){
            person.drinkAlcohol(person.getBloodAlcohol());
            person.setId(personId);
            personDao.save(person);
        }
        bbq.getAliments().remove(aliment);
        bbq.setId(bbqId);
        bbqDao.save(bbq);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
