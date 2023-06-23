package org.trilateration;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class OutlierDetection {
    private static final Double OUTLIER_THRESHOLD = 1.0;
    public static List<Point2D.Double> outlierPointsDetection(List<Point2D.Double> unknownPoints){

        List<Point2D.Double> nonOutlierPoints = new ArrayList<>();
        double centroidX = 0.0;
        double centroidY = 0.0;

        // Calculate centroid
        for (Point2D.Double unknownPoint : unknownPoints) {
            centroidX += unknownPoint.x;
            centroidY += unknownPoint.y;
        }

        centroidX /= unknownPoints.size();
        centroidY /= unknownPoints.size();

        // Calculate distances from centroid
        for (Point2D.Double unknownPoint : unknownPoints) {
            double distance = Math.sqrt(Math.pow(unknownPoint.x - centroidX, 2) + Math.pow(unknownPoint.y - centroidY, 2));
            if (distance <= OUTLIER_THRESHOLD) {
                nonOutlierPoints.add(unknownPoint);
            }
        }

        // Calculate outlier points
        List<Point2D.Double> outlierPoints = new ArrayList<>(unknownPoints);
        outlierPoints.removeAll(nonOutlierPoints);

        // Print outlier points
        System.out.println("\nOutlier points:");
        for (Point2D.Double index : outlierPoints) {
            System.out.println("(" + index.x + ", " + index.y + ")");
        }

        return nonOutlierPoints;
    }
}
