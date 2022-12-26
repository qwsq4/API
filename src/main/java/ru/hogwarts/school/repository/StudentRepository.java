package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAgeAfter(int age);
    Collection<Student> findAllByAgeIsBetween(int min, int max);

    @Query(value = "SELECT COUNT(id) from student", nativeQuery = true)
    int getStudentsAmount();

    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    int getStudentsAgeAvg();

    @Query(value = "SELECT * from student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getFiveLastStudents();
}
