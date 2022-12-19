package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.StudentImpl;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public String createStudent(StudentImpl studentImpl) {
        if (facultyRepository.existsById(studentImpl.getFacultyId())) {
            Student student = new Student(studentImpl.getName(),
                    studentImpl.getAge(),
                    facultyRepository.findById(studentImpl.getFacultyId()).get());
            studentRepository.save(student);
            return student.toString();
        }
        return null;
    }

    public String getStudent(long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id).get().toString();
        }
        return null;
    }

    public String getStudentFaculty(long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id).get().getFaculty().toString();
        }
        return null;
    }

    public String getAllStudents() {
        return studentRepository.findAll().toString();
    }

    public String getStudentsByAge(int min, int max) {
        return studentRepository.findAllByAgeIsBetween(min, max).toString();
    }

    public String getStudentsByAge(int age) {
        return studentRepository.findAllByAgeAfter(age).toString();
    }

    public String updateStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return student.toString();
        }
        return null;
    }

    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }
}
