package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.StudentImpl;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Student> addStudent(@RequestBody StudentImpl student) {
        Student addedStudent = studentService.createStudent(student);
        if (addedStudent != null) {
            return ResponseEntity.ok(addedStudent);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("age/{age}")
    public ResponseEntity<List<Student>> getByAge(@PathVariable(value = "age") int age) {
        List<Student> studentList = studentService.getStudentsByAge(age);
        if (!studentList.isEmpty()) {
            return ResponseEntity.ok(studentList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        Collection<Student> studentCollection = studentService.getAllStudents();
        if (!studentCollection.isEmpty()) {
            return ResponseEntity.ok(studentCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable(value = "id") long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }
}
