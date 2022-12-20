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

    public StudentImpl createStudent(StudentImpl studentImpl) {
        if (facultyRepository.findByName(studentImpl.getFacultyName()) != null) {
            Student student = new Student(studentImpl.getName(),
                    studentImpl.getAge(),
                    facultyRepository.findByName(studentImpl.getFacultyName()));
            studentRepository.save(student);

            return convert(student);
        }
        return null;
    }

    public StudentImpl getStudent(long id) {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();

            return convert(student);
        }
        return null;
    }

    public String getStudentFaculty(long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id).get().getFaculty().getName();
        }
        return null;
    }

    public Collection<StudentImpl> getAllStudents() {
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAll()
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public Collection<StudentImpl> getStudentsByAge(int min, int max) {
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAllByAgeIsBetween(min, max)
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public Collection<StudentImpl> getStudentsByAge(int age) {
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAllByAgeAfter(age)
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public StudentImpl updateStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return convert(student);
        }
        return null;
    }

    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    private StudentImpl convert(Student e) {
        return new StudentImpl(e.getName(),
                e.getAge(),
                e.getFaculty().getName());
    }
}
