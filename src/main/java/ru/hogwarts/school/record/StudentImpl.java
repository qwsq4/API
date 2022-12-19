package ru.hogwarts.school.record;

public class StudentImpl {
    private final String name;
    private final int age;

    private final long facultyId;

    public StudentImpl(String name, int age, int facultyId) {
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getFacultyId() {
        return facultyId;
    }
}
