package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.FacultyImpl;
import ru.hogwarts.school.record.StudentImpl;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyImpl createFaculty(FacultyImpl facultyImpl) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyImpl.getName());
        faculty.setColor(facultyImpl.getColor());
        facultyRepository.save(faculty);
        return convert(faculty);
    }

    public FacultyImpl getFaculty(long id) {
        if (facultyRepository.existsById(id)) {
            return convert(facultyRepository.findById(id).get());
        }
        return null;
    }

    public Collection<StudentImpl> getFacultyStudents(long id) {
        if (facultyRepository.existsById(id)) {
            Collection<StudentImpl> studentCollection = new ArrayList<>();
            facultyRepository
                    .findById(id)
                    .get()
                    .getStudentCollection()
                    .stream()
                    .forEach(e -> studentCollection.add(convertStudent(e)));
            return studentCollection;
        }
        return null;
    }

    public Collection<FacultyImpl> getFacultyByColor(String color) {
        Collection<FacultyImpl> facultyCollection = new ArrayList<>();
        facultyRepository
                .findAllByColor(color)
                .stream()
                .forEach(e -> facultyCollection.add(convert(e)));
        return facultyCollection;
    }

    public Collection<FacultyImpl> getFacultyByName(String name) {
        Collection<FacultyImpl> facultyCollection = new ArrayList<>();
        facultyRepository
                .findAllByName(name)
                .stream()
                .forEach(e -> facultyCollection.add(convert(e)));
        return facultyCollection;
    }

    public Collection<FacultyImpl> getAllFaculties() {
        Collection<FacultyImpl> facultyCollection = new ArrayList<>();
        facultyRepository
                .findAll()
                .stream()
                .forEach(e -> facultyCollection.add(convert(e)));
        return facultyCollection;
    }

    public FacultyImpl updateFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            facultyRepository.save(faculty);
            return convert(faculty);
        }
        return null;
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    private FacultyImpl convert(Faculty e) {
        return new FacultyImpl(e.getName(),
                e.getColor());
    }

    private StudentImpl convertStudent(Student e) {
        return new StudentImpl(e.getName(),
                e.getAge(),
                e.getFaculty().getName());
    }
}
