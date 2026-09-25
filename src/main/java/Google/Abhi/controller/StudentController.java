package Google.Abhi.controller;

import Google.Abhi.Entity.Student;
import org.springframework.web.bind.annotation.*;
import Google.Abhi.service.StudentService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import Google.Abhi.dto.StudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody StudentRequest studentRequest) {

        Student student = new Student();

        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());

        Student savedStudent = studentService.saveStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }
    @GetMapping("/page")
    public Page<Student> getStudents(Pageable pageable) {
        return studentService.getStudents(pageable);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {

        return studentService.getStudent(id);
    }
    @GetMapping("/protected")
    public String protectedApi() {

        return "You are logged in!";
    }
}