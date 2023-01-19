



public class Plane3D extends Point3D {
    private double x;
    private double y;
    private double z;

    public Plane3D(double x, double y, double z) {
        super(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double distance(Plane3D other) {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static void main(String[] args) {
        Plane3D p1 = new Plane3D(1, 2, 3);
        Plane3D p2 = new Plane3D(4, 5, 6);
        System.out.println(p1.distance(p2));
    }
}




