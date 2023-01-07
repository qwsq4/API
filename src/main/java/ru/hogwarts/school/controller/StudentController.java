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
    public ResponseEntity<StudentImpl> addStudent(@RequestBody StudentImpl student) {
        StudentImpl addedStudent = studentService.createStudent(student);
        if (addedStudent != null) {
            return ResponseEntity.ok(addedStudent);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentImpl> getStudent(@PathVariable(value = "id") long id) {
        StudentImpl student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/amount")
    public ResponseEntity<Integer> getStudentsAmount() {
        int amount = studentService.getStudentsAmount();
        if (amount > 0) {
            return ResponseEntity.ok(amount);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/age/average")
    public ResponseEntity<Integer> getStudentsAgeAvg() {
        int ageSum = studentService.getStudentsAgeAvg();
        if (ageSum > 0) {
            return ResponseEntity.ok(ageSum);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/last")
    public ResponseEntity<Collection<StudentImpl>> getFiveLastStudents() {
        Collection<StudentImpl> studentCollection = studentService.getFiveLastStudents();
        if (!studentCollection.isEmpty()) {
            return ResponseEntity.ok(studentCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<String> getStudentFaculty(@PathVariable(value = "id") long id) {
        String faculty = studentService.getStudentFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("age/{age}")
    public ResponseEntity<Collection<StudentImpl>> getByAge(@PathVariable(value = "age") int age) {
        Collection<StudentImpl> studentList = studentService.getStudentsByAge(age);
        if (!studentList.isEmpty()) {
            return ResponseEntity.ok(studentList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<Collection<StudentImpl>> getAllStudents() {
        Collection<StudentImpl> studentCollection = studentService.getAllStudents();
        if (!studentCollection.isEmpty()) {
            return ResponseEntity.ok(studentCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nameStartWithA")
    public ResponseEntity<List<String>> getStudentNamesStartsWithA() {
        List<String> stringList = studentService.getStudentByNameStartsWithA();
        if (!stringList.isEmpty()) {
            return ResponseEntity.ok(stringList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("ageSum")
    public ResponseEntity<Integer> getAgeSum() {
        int ageSum = studentService.getAgeSum();
        if (ageSum > 0) {
            return ResponseEntity.ok(ageSum);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("update")
    public ResponseEntity<StudentImpl> updateStudent(@RequestBody Student student) {
        StudentImpl updatedStudent = studentService.updateStudent(student);
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
