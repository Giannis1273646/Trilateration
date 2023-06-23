package org.trilateration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int COMBINATION_LENGTH = 3;
    private static final Double DESIRED_POINT_X = 4.97;
    private static final Double DESIRED_POINT_Y = 2.5;

    public static void main(String[] args) throws IOException {
        List<List<Nodes>> tripleNodesCombinations = TripleCombinations.getTripleCombinations(COMBINATION_LENGTH);
        List<Point2D.Double> unknownPoints = new ArrayList<>();

        // Create x and y data points
        List<Double> xUnknownPointData = new ArrayList<>();
        List<Double> yUnknownPointyData = new ArrayList<>();
        List<Double> xNodeData = new ArrayList<>();
        List<Double> yNodeData = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/files/nodes.json");
        int i=0;

        //Prints every possible combination of nodes
        System.out.println("Combination of " + COMBINATION_LENGTH + ":");
        for (List<Nodes> innerList : tripleNodesCombinations) {
            List<String> nodeNames = new ArrayList<>();
            for (Nodes node : innerList) {
                nodeNames.add(node.getId());
            }
            System.out.println(nodeNames);
        }
        System.out.println("\nThe results of the Trilateration algorithm: ");

        //Calls the trilateration function for each list from the node combination,
        // passing as a parameter the current combination
        //Works only with 3 number of combinations
        for (List<Nodes> tripleNodesCombination : tripleNodesCombinations) {
            Point2D.Double unknownPoint = Trilateration.trilateration(tripleNodesCombination);

            // Checks if the point is Infinite. If not add the point to the unknownPoints list
            if (!Double.isInfinite(unknownPoint.x) && !Double.isInfinite(unknownPoint.y)) {
                unknownPoints.add(unknownPoint);
            }
        }

        //Gets the list with filtered point in which we have remove the outlier points
        List<Point2D.Double> filteredPoints = OutlierDetection.outlierPointsDetection(unknownPoints);

        //Adds for each unknown point the x and y coordinates to the corresponding list
        for(Point2D.Double unknownPoint : filteredPoints){
            xUnknownPointData.add(unknownPoint.x);
            yUnknownPointyData.add(unknownPoint.y);
        }

        // Read all the nodes from the file
        List<Nodes> nodesList = objectMapper.readValue(file, new TypeReference<>() {});

        // Get the coordinates of the nodes in the file and add them to the list
        for(Nodes nodes : nodesList){
            i++;
            if(nodes.getId().equals("node".concat(String.valueOf(i)))){
                xNodeData.add(nodes.getX());
                yNodeData.add(nodes.getY());
            }
        }

        // Create Chart with default style
        XYChart chart = new XYChartBuilder().width(900).height(700).title("Coordinates of the unknown points").xAxisTitle("X Values").yAxisTitle("Y Values").build();

        // Customize chart style
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);

        // Add series to the chart
        XYSeries series = chart.addSeries("Unknown Points", xUnknownPointData, yUnknownPointyData);
        XYSeries series1 = chart.addSeries("Desired Point", List.of(DESIRED_POINT_X), List.of(DESIRED_POINT_Y));
        XYSeries series2 = chart.addSeries("Nodes", xNodeData, yNodeData);
        series.setMarkerColor(Color.BLUE);
        series1.setMarkerColor(Color.RED);
        series2.setMarkerColor(Color.BLACK);

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }
}