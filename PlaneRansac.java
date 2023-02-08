// Michael Ohagwu
// 300074813

import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.io.*;


public class PlaneRansac extends PointCloud {
    private PointCloud pc;
    private double eps;
    private ArrayList<Point3D> dominantPlanePoints;
    private int bestSupport;
    private Plane3D dominantPlane;
    private int iterations;

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
        //from equation given in pdf for k
        return (int) (Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }



    // run method
    public void run(int numberOfIterations, String filename) {
        //dominantPlanePoints = new ArrayList<Point3D>();
        Random rand = new Random();
        ArrayList<Point3D> inliers = new ArrayList<Point3D>();

        if (pc.points.size() == 0) {
            System.out.println("Error: Point cloud is empty.");
            return;
        }
        
        // here we are executing the part of the algorithm used to find the dominant plane



        for (int i = 0; i < numberOfIterations; i++) {
            ArrayList<Point3D> samplePoints = new ArrayList<Point3D>();
            // following instructions from pdf, we randomly select 3 points from the point cloud
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(pc.points.size());
                samplePoints.add(pc.points.get(randomIndex));
            }
        

            // Then we create a Plane3D object using the 3 points we just randomly sampled
            Plane3D plane = new Plane3D(samplePoints.get(0), samplePoints.get(1), samplePoints.get(2));
            
            int initSupport = 0; // set to 0 <- support
            int idealSupport = 0; // set to 0 <- support

            for (Point3D point : pc.points) {
                if (Math.abs(plane.getDistance(point)) < eps) {
                    initSupport++;
                }
            }
            if (initSupport > idealSupport) {
                dominantPlane = plane;
                idealSupport = initSupport;
            }

            //ArrayList<Point3D> inliers = new ArrayList<Point3D>();
            //for (Point3D point : pc.points) {
                //if (Math.abs(dominantPlane.getDistance(point)) < eps) {
                    //inliers.add(point);
                //}
            //}
            
            inliers = getInliers(dominantPlane, pc.points);
            
        }

        subtractFromPointCloud(inliers, pc.points);
        
        for(Point3D point : inliers) {
            System.out.println("Inliers: "+point);
        }

        for(Point3D point : pc.points) {
            System.out.println("PC Points: "+point);
        }

        // then we save the dominant plane points to a file
        pc.points = inliers;
        pc.save(filename);


    }
    private ArrayList<Point3D>getInliers(Plane3D plane, ArrayList<Point3D> points) {
        ArrayList<Point3D> inliers = new ArrayList<Point3D>();
        for (Point3D point : points) {
            if (Math.abs(plane.getDistance(point)) < eps) {
                inliers.add(point);
            }
        }
        return inliers;

       
    }

   
    private void subtractFromPointCloud(ArrayList<Point3D> inliers, ArrayList<Point3D> points) {
        for (Point3D point : inliers) {
            points.remove(point);
        }
    }



    
    public static void main(String[] args) {
        PointCloud pc = new PointCloud("clouds/PointCloud3_p0.xyz");
        // Point3D point = pc.getPoint();
        PlaneRansac ransac = new PlaneRansac(pc);
        ransac.setEps(0.01);
        //ransac.getInliers(null, null);
        ransac.run(ransac.getNumberOfIterations(0.99, 0.3), "clouds/testing.xyz");
    }
}
