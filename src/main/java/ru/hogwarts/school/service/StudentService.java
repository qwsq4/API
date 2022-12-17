package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.StudentImpl;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentImpl studentImpl) {
        Student student = new Student();
        student.setName(studentImpl.getName());
        student.setAge(studentImpl.getAge());
        studentRepository.save(student);
        return student;
    }

    public Student getStudent(long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id).get();
        }
        return null;
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByAge(int age) {
            return studentRepository
                    .findAll()
                    .stream()
                    .filter(e -> e.getAge() >= age)
                    .collect(Collectors.toList());
    }

    public Student updateStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }
}
