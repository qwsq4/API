package ru.hogwarts.school.record;

public class FacultyImpl {
    private final String name;
    private final String color;

    public FacultyImpl(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
