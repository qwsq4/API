package ru.hogwarts.school.record;

public class StudentImpl {
    private String name;
    private int age;

    private String facultyName;

    public StudentImpl(String name, int age, String facultyName) {
        this.name = name;
        this.age = age;
        this.facultyName = facultyName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFacultyName() {
        return facultyName;
    }
}
