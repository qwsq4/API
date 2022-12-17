package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.record.FacultyImpl;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(FacultyImpl facultyImpl) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyImpl.getName());
        faculty.setColor(facultyImpl.getColor());
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFaculty(long id) {
        if (facultyRepository.existsById(id)) {
            return facultyRepository.findById(id).get();
        }
        return null;
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository
                .findAll()
                .stream()
                .filter(e -> e.getColor() == color)
                .collect(Collectors.toList());
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            facultyRepository.save(faculty);
            return faculty;
        }
        return null;
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }
}
