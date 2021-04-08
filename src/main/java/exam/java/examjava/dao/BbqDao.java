package exam.java.examjava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exam.java.examjava.models.Bbq;

@Repository
public interface BbqDao extends JpaRepository<Bbq, Integer> {
    
    @Override
    List<Bbq> findAll();
    
    Bbq findById(int id);

    Bbq save (Bbq bbq);

    void deleteById(int id);
}
