package pl.makstokarz.springapp_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.makstokarz.springapp_2.entity.Student;
import pl.makstokarz.springapp_2.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {


    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/api/getStudents")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);

    }

    @GetMapping("/api/getStudents/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/updateStudent/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable long id, @RequestBody Student stud){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            student.get().setStudentName(stud.getStudentName());
            student.get().setStudentEmail(stud.getStudentEmail());
            student.get().setStudentAddress(stud.getStudentAddress());
            return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
