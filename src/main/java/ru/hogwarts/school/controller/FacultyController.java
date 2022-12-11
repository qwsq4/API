package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("add")
    public ResponseEntity<Faculty> addFaculty(@RequestParam String name, @RequestParam String color) {
        Faculty addedFaculty = facultyService.createFaculty(name, color);
        if (addedFaculty != null) {
            return ResponseEntity.ok(addedFaculty);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("get")
    public ResponseEntity<Faculty> getFaculty(@RequestParam long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/by/color")
    public ResponseEntity<Collection<Faculty>> getByColor(@RequestParam String color) {
        Collection<Faculty> facultyCollection = facultyService.getFacultiesByColor(color);
        if (!facultyCollection.isEmpty()) {
            return ResponseEntity.ok(facultyCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/all")
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

    @DeleteMapping("delete")
    public ResponseEntity<Faculty> deleteFaculty(@RequestParam long id) {
        Faculty deletedFaculty = facultyService.removeFaculty(id);
        if (deletedFaculty != null) {
            return ResponseEntity.ok(deletedFaculty);
        }
        return ResponseEntity.notFound().build();
    }
}
