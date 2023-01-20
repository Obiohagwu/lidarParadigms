import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.io.*;


public class PlaneRansac {
    private PointCloud pc;
    private double eps;
    private ArrayList<Point3D> dominantPlanePoints;

    public PlaneRansac(PointCloud pc) {
        this.pc = pc;
        this.eps = 0.01;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getEps() {
        return eps;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane) {
        return (int) (Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }

    public void run(int numberOfIterations, String filename) {
        dominantPlanePoints = new ArrayList<Point3D>();
        Random rand = new Random();

        for (int i = 0; i < numberOfIterations; i++) {
            ArrayList<Point3D> samplePoints = new ArrayList<Point3D>();
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(pc.points.size());
                samplePoints.add(pc.points.get(randomIndex));
            }
            Plane3D plane = new Plane3D(samplePoints.get(0), samplePoints.get(1), samplePoints.get(2));
            int inliers = 0;
            for (Point3D point : pc.points) {
                if (Math.abs(plane.getDistance(point)) < eps) {
                    inliers++;
                }
            }
            double ratio = (double) inliers / pc.points.size();
            if (ratio > 0.5) {
                dominantPlanePoints.clear();
                for (Point3D point : pc.points) {
                    if (Math.abs(plane.getDistance(point)) < eps) {
                        dominantPlanePoints.add(point);
                    }
                }
            }
        }
        pc.points.removeAll(dominantPlanePoints);
        PointCloud dominantPlane = new PointCloud();
        dominantPlane.points = dominantPlanePoints;
        dominantPlane.save(filename);
    }

    public static void main(String[] args) {
        PointCloud cloud = new PointCloud("clouds/PointCloud2.xyz");
        PlaneRansac ransac = new PlaneRansac(cloud);
        ransac.run(ransac.getNumberOfIterations(0.99, 0.5), "clouds/dominant_plane.xyz");
    }
}
