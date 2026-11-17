package ru.surname.birds;

public abstract class Bird4 {
    private String name;

    public Bird4(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String sing();
}
