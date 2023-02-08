
// Michael Ohagwu
// 300074813

import java.lang.Math;

public class Plane3D extends Point3D {

    private double a;
    private double b;
    private double c;
    private double d;

    // Constructor from 3 points
    public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
        super(0, 0, 0);
        a = (p2.getY() - p1.getY()) * (p3.getZ() - p1.getZ()) - (p2.getZ() - p1.getZ()) * (p3.getY() - p1.getY());
        b = (p2.getZ() - p1.getZ()) * (p3.getX() - p1.getX()) - (p2.getX() - p1.getX()) * (p3.getZ() - p1.getZ());
        c = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
        d = -(a * p1.getX() + b * p1.getY() + c * p1.getZ());
    }

    // Constructor from parameters
    public Plane3D(double a, double b, double c, double d) {
        super(0, 0, 0);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    // getDistance method that returns the distance from a point to the plane. Equation from internet (wikipedia)
    public double getDistance(Point3D pt) {
        return (a * pt.getX() + b * pt.getY() + c * pt.getZ() + d) / Math.sqrt(a * a + b * b + c * c);
    }



    public static void main(String[] args) {
        Point3D point1 = new Point3D(1, 2, 3);
        Point3D point2 = new Point3D(4, 5, 6);
        Point3D point3 = new Point3D(7, 8, 9);
        
        Plane3D plane1 = new Plane3D(point1, point2, point3);
        Plane3D plane2 = new Plane3D(1, 2, 3, 4);
        
        double distance1 = plane1.getDistance(new Point3D(10,10,10));
        double distance2 = plane2.getDistance(new Point3D(10,10,10));
        System.out.println(distance1);
        System.out.println(distance2);
        
    }
}




