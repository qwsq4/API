package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.record.FacultyImpl;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public ResponseEntity<Faculty> addFaculty(@RequestBody FacultyImpl facultyImpl) {
        Faculty addedFaculty = facultyService.createFaculty(facultyImpl);
        if (addedFaculty != null) {
            return ResponseEntity.ok(addedFaculty);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable(value = "id") long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("color/{color}")
    public ResponseEntity<List<Faculty>> getByColor(@PathVariable(value = "color") String color) {
        List<Faculty> facultyCollection = facultyService.getFacultiesByColor(color);
        if (!facultyCollection.isEmpty()) {
            return ResponseEntity.ok(facultyCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        Collection<Faculty> facultyCollection = facultyService.getAllFaculties();
        if (!facultyCollection.isEmpty()) {
            return ResponseEntity.ok(facultyCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("update")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty != null) {
            return ResponseEntity.ok(updatedFaculty);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteFaculty(@PathVariable(value = "id") long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }
}
