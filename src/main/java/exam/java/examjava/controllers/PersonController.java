package exam.java.examjava.controllers;

import java.util.List;

import exam.java.examjava.dao.AlimentDao;
import exam.java.examjava.dao.BbqDao;
import exam.java.examjava.dao.PersonDao;
import exam.java.examjava.models.Aliment;
import exam.java.examjava.models.Bbq;
import exam.java.examjava.models.Person;
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
@RequestMapping("/persons")
public class PersonController {
    
    private final PersonDao personDao;
    private final AlimentDao alimentDao;
    private final BbqDao bbqDao;

    @Autowired
    public PersonController(PersonDao personDao, AlimentDao alimentDao, BbqDao bbqDao){
        this.personDao = personDao;
        this.alimentDao = alimentDao;
        this.bbqDao = bbqDao;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> persons = personDao.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id){
        Person person = personDao.findById(id);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        personDao.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        Person person = personDao.findById(id);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> putPerson(@PathVariable int id, @RequestBody Person person){
        Person modifiedPerson = personDao.findById(id);

        if(modifiedPerson == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        modifiedPerson = personDao.save(person);

        return new ResponseEntity<>(modifiedPerson, HttpStatus.OK);
    }

    @PutMapping("/{personId}/aliments/{alimentId}")
    public ResponseEntity<Person> addPersonInBbq(@PathVariable int personId, @PathVariable int alimentId){
        Person person = personDao.findById(personId);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Bbq bbq = person.getBbq();
        int bbqId = bbq.getId();
        if(bbq == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Aliment aliment = alimentDao.findById(alimentId);
        if(aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        person.getAliments().add(aliment);
        bbq.getAliments().add(aliment);

        aliment.setPerson(person);

        person.setId(personId);
        bbq.setId(bbqId);
        aliment.setId(alimentId);

        bbqDao.save(bbq);
        personDao.save(person);
        alimentDao.save(aliment);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    
}
