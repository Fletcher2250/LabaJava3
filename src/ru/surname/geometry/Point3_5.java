package ru.surname.geometry;

public class Point3_5 implements Cloneable {
    private double x;
    private double y;

    public Point3_5() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point3_5(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    // задача 9.4
    @Override
    public Point3_5 clone() {
        try {
            return (Point3_5) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        String strX = (x == (long) x) ? String.format("%d", (long) x) : String.valueOf(x);
        String strY = (y == (long) y) ? String.format("%d", (long) y) : String.valueOf(y);
        return "{" + strX + ";" + strY + "}";
    }
}
