package exam.java.examjava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exam.java.examjava.models.Bbq;
import exam.java.examjava.models.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {
      
    @Override
    List<Person> findAll();

    List<Person> findAllByBbq(Bbq bbq);
    
    Person findById(int id);

    Person save (Person person);

    void deleteById(int id);
}
