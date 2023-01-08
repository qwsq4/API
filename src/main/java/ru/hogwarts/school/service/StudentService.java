package ru.hogwarts.school.service;

import liquibase.repackaged.org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.StudentImpl;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public StudentImpl createStudent(StudentImpl studentImpl) {
        logger.debug("create student method called");
        if (facultyRepository.findByName(studentImpl.getFacultyName()) != null) {
            Student student = new Student(WordUtils.capitalize(studentImpl.getName()),
                    studentImpl.getAge(),
                    facultyRepository.findByName(studentImpl.getFacultyName()));
            studentRepository.save(student);

            return convert(student);
        }
        return null;
    }

    public int getStudentsAmount() {
        logger.debug("get students amount method create");
        return studentRepository.getStudentsAmount();
    }

    public int getStudentsAgeAvg() {
        logger.debug("get average students age method called");
        return studentRepository.getStudentsAgeAvg();
    }

    public Collection<StudentImpl> getFiveLastStudents() {
        logger.debug("get last 5 students method called");
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .getFiveLastStudents()
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public StudentImpl getStudent(long id) {
        logger.debug("get student method called");
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();

            return convert(student);
        }
        return null;
    }

    public List<String> getStudentByNameStartsWithA() {
        return studentRepository.findAll().stream()
                .map(s -> s.getName().toUpperCase())
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAgeAvg() {
        Stream<Student> stream = studentRepository.findAll().stream();

        double ageAvg = stream
                .parallel()
                .mapToInt(e -> e.getAge())
                .average().getAsDouble();

        return ageAvg;
    }

    public String getStudentFaculty(long id) {
        logger.debug("get student faculty method called");
        if (studentRepository.existsById(id)) {
            return studentRepository.findById(id).get().getFaculty().getName();
        }
        return null;
    }

    public Collection<StudentImpl> getAllStudents() {
        logger.debug("get all students method called");
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAll()
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public Collection<StudentImpl> getStudentsByAge(int min, int max) {
        logger.debug("get all students by age method called");
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAllByAgeIsBetween(min, max)
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public Collection<StudentImpl> getStudentsByAge(int age) {
        logger.debug("get all students by age method called");
        Collection<StudentImpl> studentCollection = new ArrayList<>();
        studentRepository
                .findAllByAgeAfter(age)
                .stream()
                .forEach(e -> studentCollection.add(convert(e)));
        return studentCollection;
    }

    public StudentImpl updateStudent(Student student) {
        logger.debug("update student method called");
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return convert(student);
        }
        return null;
    }

    public void removeStudent(long id) {
        logger.debug("remove student method called");
        studentRepository.deleteById(id);
    }

    private StudentImpl convert(Student e) {
        return new StudentImpl(e.getName(),
                e.getAge(),
                e.getFaculty().getName());
    }
}
