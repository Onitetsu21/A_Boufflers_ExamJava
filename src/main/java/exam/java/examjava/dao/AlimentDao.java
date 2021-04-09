package exam.java.examjava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exam.java.examjava.models.Person;
import exam.java.examjava.models.Aliment;
import exam.java.examjava.models.Bbq;

@Repository
public interface AlimentDao extends JpaRepository<Aliment, Integer> {

    @Override
    List<Aliment> findAll();

    List<Aliment> findAllByPerson(Person person);

    List<Aliment> findAllByBbq(Bbq bbq);

    Aliment findById(int id);

    Aliment save(Aliment aliment);

    void deleteById(int id);
}
