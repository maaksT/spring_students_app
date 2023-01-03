package pl.makstokarz.springapp_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.makstokarz.springapp_2.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

}
