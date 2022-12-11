package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@RequestParam String name, @RequestParam int age) {
        Student addedStudent = studentService.createStudent(name, age);
        if (addedStudent != null) {
            return ResponseEntity.ok(addedStudent);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("get")
    public ResponseEntity<Student> getStudent(@RequestParam long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/by/age")
    public ResponseEntity<List<Student>> getByAge(@RequestParam int age) {
        List<Student> studentList = studentService.getStudentsByAge(age);
        if (!studentList.isEmpty()) {
            return ResponseEntity.ok(studentList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/all")
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

    @DeleteMapping("delete")
    public ResponseEntity<Student> deleteStudent(@RequestParam long id) {
        Student deletedStudent = studentService.removeStudent(id);
        if (deletedStudent != null) {
            return ResponseEntity.ok(deletedStudent);
        }
        return ResponseEntity.notFound().build();
    }
}
