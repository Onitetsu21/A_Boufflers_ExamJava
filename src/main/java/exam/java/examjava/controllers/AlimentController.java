package exam.java.examjava.controllers;

import java.util.List;

import exam.java.examjava.dao.AlimentDao;
import exam.java.examjava.models.Aliment;
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
@RequestMapping("/aliments")
public class AlimentController {
    
    private final AlimentDao alimentDao;

    @Autowired
    public AlimentController(AlimentDao alimentDao){
        this.alimentDao = alimentDao;
    }

    @GetMapping
    public ResponseEntity<List<Aliment>> getAllAliments(){
        List<Aliment> aliments = alimentDao.findAll();
        return new ResponseEntity<>(aliments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aliment> getById(@PathVariable int id){
        Aliment aliment = alimentDao.findById(id);
        if(aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aliment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Aliment> createAliment(@RequestBody Aliment aliment){
        alimentDao.save(aliment);
        return new ResponseEntity<>(aliment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAliment(@PathVariable int id){
        Aliment aliment = alimentDao.findById(id);
        if(aliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        alimentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aliment> putAliment(@PathVariable int id, @RequestBody Aliment aliment){
        Aliment modifiedAliment = alimentDao.findById(id);

        if(modifiedAliment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        modifiedAliment = alimentDao.save(aliment);

        return new ResponseEntity<>(modifiedAliment, HttpStatus.OK);
    }
}
