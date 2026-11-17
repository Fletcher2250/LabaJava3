package ru.surname.birds;

import java.util.Random;

public class Cuckoo4 extends Bird4 {
    private Random random;

    public Cuckoo4() {
        super("Кукушка");
        this.random = new Random();
    }

    @Override
    public String sing() {
        int count = random.nextInt(10) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("ку-ку");
            if (i < count - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
