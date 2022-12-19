package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.record.FacultyImpl;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public String createFaculty(FacultyImpl facultyImpl) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyImpl.getName());
        faculty.setColor(facultyImpl.getColor());
        facultyRepository.save(faculty);
        return faculty.toString();
    }

    public String getFaculty(long id) {
        if (facultyRepository.existsById(id)) {
            return facultyRepository.findById(id).get().toString();
        }
        return null;
    }

    public String getFacultyStudents(long id) {
        if (facultyRepository.existsById(id)) {
            return facultyRepository.findById(id).get().getStudentCollection().toString();
        }
        return null;
    }

    public String getFacultyByColor(String color) {
        return facultyRepository.findAllByColor(color).toString();
    }

    public String getFacultyByName(String name) {
        return facultyRepository.findAllByName(name).toString();
    }

    public String getAllFaculties() {
        return facultyRepository.findAll().toString();
    }

    public String updateFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            facultyRepository.save(faculty);
            return faculty.toString();
        }
        return null;
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }
}
