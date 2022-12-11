package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> storage;
    private long idCount = 0;

    public StudentService() {
        this.storage = new HashMap<>();
    }

    public Student createStudent(String name, int age) {
        Student student = new Student(name, age);
        student.setId(idCount);
        idCount++;

        if (storage.containsKey(student.getId())) {
            return null;
        }

        storage.put(student.getId(), student);
        return student;
    }

    public Student getStudent(long id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        }
        return null;
    }

    public Collection<Student> getAllStudents() {
        return storage.values();
    }

    public List<Student> getStudentsByAge(int age) {
            return storage.values()
                    .stream()
                    .filter(e -> e.getAge() >= age)
                    .collect(Collectors.toList());
    }

    public Student updateStudent(Student student) {
        if (storage.containsKey(student.getId())) {
            storage.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student removeStudent(long id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return storage.get(id);
        }
        return null;
    }
}
