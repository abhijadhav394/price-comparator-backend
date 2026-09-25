package Google.Abhi.service;

import Google.Abhi.Entity.Student;
import Google.Abhi.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {

        return studentRepository.save(student);

    }
    public List<Student> getAllStudents() {

        return studentRepository.findAll();

    }
    public Page<Student> getStudents(Pageable pageable) {

        return studentRepository.findAll(pageable);

    }
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}