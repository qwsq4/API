package ru.hogwarts.school.record;

public class StudentImpl {
    private final String name;
    private final int age;

    public StudentImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
