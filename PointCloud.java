// Michael Ohagwu
// 300074813

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class PointCloud {
    ArrayList<Point3D> points;
    ArrayList<Plane3D> planes;
    ArrayList<Point3D> dominantPlanePoints;

    // Constructor from a xyz file
    public PointCloud(String filename) {
        points = new ArrayList<Point3D>();
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ( (line = br.readLine()) != null) {
                String[] coordinates = line.trim().split("\\s+"); // split on whitespace
                if (coordinates.length < 3) {
                    System.out.println("Invalid inputPP: " + line);
                    continue;
                }
                try {
                    double x = Double.parseDouble(coordinates[0]);
                    double y = Double.parseDouble(coordinates[1]);
                    double z = Double.parseDouble(coordinates[2]);
                    Point3D point = new Point3D(x, y, z);
                    points.add(point);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid inputKK: " + line);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
 
 
       
    public PointCloud(ArrayList<Point3D> points) {
        this.points = points;
        planes = new ArrayList<Plane3D>();
     

    }

    // Empty constructor that constructs an empty point cloud
    public PointCloud() {
        points = new ArrayList<Point3D>();
        planes = new ArrayList<Plane3D>();
    }

    // addPoint method 
    public void addPoint(Point3D pt) {
        points.add(pt);
    }

    public int getNumPlanes() {
        return planes.size();
    }

    public Plane3D getPlane(int i) {
        return planes.get(i);
    }

    public void addPlane(Plane3D p) {
        planes.add(p);
    }
  

    // getPoint method that returns a random point from the cloud
    public Point3D getPoint() {
        if (points.size() == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * points.size());
        return points.get(randomIndex);
    }

    // save method that saves the point cloud into a xyz file
    public void save(String filename) {
        try {
            File file = new File(filename);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Point3D point : points) {
                bw.write(point.getX() + " " + point.getY() + " " + point.getZ());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    // iterator method that returns an iterator to the points in the cloud
    public Iterator<Point3D> iterator() {
        return points.iterator();
    }

    

    public static void main(String[] args) throws NumberFormatException, IOException {
        PointCloud cloud = new PointCloud("clouds/PointCloud1.xyz");
        Point3D point = cloud.getPoint();
        cloud.addPoint(point);
        cloud.save("clouds/new_points1.xyz");
    }
}
