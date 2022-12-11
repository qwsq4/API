package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> storage;
    private long idCount = 0;

    public FacultyService() {
        this.storage = new HashMap<>();
    }

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty(name, color);
        faculty.setId(idCount);
        idCount++;

        if (storage.containsKey(faculty.getId())) {
            return null;
        }

        storage.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(long id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        }
        return null;
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return storage.values()
                .stream()
                .filter(e -> e.getColor() == color)
                .collect(Collectors.toList());
    }

    public Collection<Faculty> getAllFaculties() {
        return storage.values();
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (storage.containsKey(faculty.getId())) {
            storage.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty removeFaculty(long id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return storage.get(id);
        }
        return null;
    }
}
