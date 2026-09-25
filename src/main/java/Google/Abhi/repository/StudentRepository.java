package Google.Abhi.repository;

import Google.Abhi.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface StudentRepository extends JpaRepository<Student, Long> {

    }
