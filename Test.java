import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        PointCloud cloud = new PointCloud("clouds/PointCloud2.xyz");
        Point3D point = cloud.getPoint();
        cloud.addPoint(new Point3D(1, 2, 3));
        cloud.save("clouds/new_points1.xyz");
    }
}
