package ru.surname.birds;

import java.util.Random;

public class Parrot4 extends Bird4 {
    private String text;
    private Random random;

    public Parrot4(String text) {
        super("Попугай");
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым");
        }
        this.text = text.trim();
        this.random = new Random();
    }

    @Override
    public String sing() {
        int n = random.nextInt(text.length()) + 1;
        return text.substring(0, n);
    }
}
