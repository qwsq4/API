package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.FacultyImpl;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public ResponseEntity<String> addFaculty(@RequestBody FacultyImpl facultyImpl) {
        String addedFaculty = facultyService.createFaculty(facultyImpl);
        if (addedFaculty != null) {
            return ResponseEntity.ok(addedFaculty);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getFaculty(@PathVariable(value = "id") long id) {
        String faculty = facultyService.getFaculty(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/students")
    public ResponseEntity<String> getFacultyStudents(@PathVariable(value = "id") long id) {
        String studentCollection = facultyService.getFacultyStudents(id);
        if (studentCollection != null) {
            return ResponseEntity.ok(studentCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("color")
    public ResponseEntity<String> getByColorOrName(@RequestParam(required = false) String color,
                                                                @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            String facultyCollection = facultyService.getFacultyByColor(color);
            return ResponseEntity.ok(facultyCollection);
        }
        if (name != null && !name.isBlank()) {
            String facultyCollection = facultyService.getFacultyByName(name);
            return ResponseEntity.ok(facultyCollection);
        }
        String facultyCollection = facultyService.getAllFaculties();
        return ResponseEntity.ok(facultyCollection);
    }

    @GetMapping("all")
    public ResponseEntity<String> getAllFaculties() {
        String facultyCollection = facultyService.getAllFaculties();
        if (!facultyCollection.isEmpty()) {
            return ResponseEntity.ok(facultyCollection);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("update")
    public ResponseEntity<String> updateFaculty(@RequestBody Faculty faculty) {
        String updatedFaculty = facultyService.updateFaculty(faculty);
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
