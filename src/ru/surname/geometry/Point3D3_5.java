package ru.surname.geometry;

public class Point3D3_5 extends Point3_5 {
    private double z;

    public Point3D3_5() {
        super();
        this.z = 0.0;
    }

    public Point3D3_5(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }

    // 9.4
    @Override
    public Point3D3_5 clone() {
        return (Point3D3_5) super.clone();
    }

    @Override
    public String toString() {
        String strX = (getX() == (long) getX()) ? String.format("%d", (long) getX()) : String.valueOf(getX());
        String strY = (getY() == (long) getY()) ? String.format("%d", (long) getY()) : String.valueOf(getY());
        String strZ = (z == (long) z) ? String.format("%d", (long) z) : String.valueOf(z);

        return "{" + strX + ";" + strY + ";" + strZ + "}";
    }
}