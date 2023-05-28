package org.trilateration;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trilateration {
    public static void trilateration(List<Nodes> nodesNames) throws IOException {
        //Initialisation of variables
        Point2D.Double pointA = new Point2D.Double();
        Point2D.Double pointB = new Point2D.Double();
        Point2D.Double pointC = new Point2D.Double();

        double distanceToA = 0;
        double distanceToB = 0;
        double distanceToC = 0;

        //Create a list of points and a list of distances so that we can define them in the loop one by one
        List<Point2D.Double> points = Arrays.asList(pointA, pointB, pointC);
        List<Double> distances = Arrays.asList(distanceToA, distanceToB, distanceToC);
        List<String> nodesList = new ArrayList<>();

        //Prints the node combination for which we will apply trilateration
        for (Nodes node : nodesNames) {
            nodesList.add(node.getId());
        }
        System.out.println(nodesList);

        //We take from each node in the list the coordinates and distance
        int i = 0;
        for(Nodes node : nodesNames){
            points.set(i, new Point2D.Double(node.getX(), node.getY()));
            distances.set(i, node.getDistance());
            i++;
        }

        // Calculate the coordinates of the unknown point
        Point2D.Double unknownPoint = trilateration(points.get(0), points.get(1), points.get(2),
                distances.get(0), distances.get(1), distances.get(2));

        // Print the coordinates of the unknown point
        System.out.println("Coordinates of the unknown point: (" + unknownPoint.x + ", " + unknownPoint.y + ")");
    }

    private static Point2D.Double trilateration(Point2D.Double pointA, Point2D.Double pointB, Point2D.Double pointC,
                                               double distanceToA, double distanceToB, double distanceToC) {

        double x0 = pointA.getX();
        double y0 = pointA.getY();
        double x1 = pointB.getX();
        double y1 = pointB.getY();
        double x2 = pointC.getX();
        double y2 = pointC.getY();

        double dA = distanceToA;
        double dB = distanceToB;
        double dC = distanceToC;

        // Calculate intermediate values for trilateration formula
        double W = x1 * x1 - x0 * x0 + y1 * y1 - y0 * y0 - dB * dB + dA * dA;
        double Z = x2 * x2 - x0 * x0 + y2 * y2 - y0 * y0 - dC * dC + dA * dA;

        // Calculate x-coordinate of the unknown point
        double unknownX = (W * (y2 - y0) - Z * (y1 - y0)) / (2 * ((x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0)));

        // Calculate y-coordinate of the unknown point
        double unknownY = (W - 2 * unknownX * (x1 - x0)) / (2 * (y1 - y0));

        //return Coordinates of the unknown point
        return new Point2D.Double(unknownX, unknownY);
    }
}

