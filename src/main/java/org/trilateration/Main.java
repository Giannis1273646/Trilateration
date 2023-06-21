package org.trilateration;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int COMBINATION_LENGTH = 3;
    private static final Double DESIRED_POINT_X = 4.97;
    private static final Double DESIRED_POINT_Y = 2.5;

    public static void main(String[] args) throws IOException {
        List<List<Nodes>> tripleNodesCombinations = TripleCombinations.getTripleCombinations(COMBINATION_LENGTH);

        // Create x and y data points
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        //Prints every possible combination of nodes
        System.out.println("Combination of " + COMBINATION_LENGTH + ":");
        for (List<Nodes> innerList : tripleNodesCombinations) {
            List<String> nodeNames = new ArrayList<>();
            for (Nodes node : innerList) {
                nodeNames.add(node.getId());
            }
            System.out.println(nodeNames);
        }

        //Calls the trilateration function for each list from the node combination,
        // passing as a parameter the current combination
        //Works only with 3 number of combinations
        //Also adds for each unknown point the x and y coordinates to the corresponding list
        for (List<Nodes> tripleNodesCombination : tripleNodesCombinations) {
            Point2D.Double unknownPoint = Trilateration.trilateration(tripleNodesCombination);

            //If the unknown point is null, not added
            if(unknownPoint.x != Double.POSITIVE_INFINITY  && unknownPoint.x != Double.NEGATIVE_INFINITY
                    && unknownPoint.y != Double.POSITIVE_INFINITY && unknownPoint.y != Double.NEGATIVE_INFINITY ){
                xData.add(unknownPoint.x);
                yData.add(unknownPoint.y);
            }
        }

        // Create Chart with default style
        XYChart chart = new XYChartBuilder().width(600).height(400).title("Coordinates of the unknown points").xAxisTitle("X Values").yAxisTitle("Y Values").build();

        // Customize chart style
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);

        // Add series to the chart
        XYSeries series = chart.addSeries("Unknown Point", xData, yData);
        XYSeries series1 = chart.addSeries("Desired Point", List.of(DESIRED_POINT_X), List.of(DESIRED_POINT_Y));
        series.setMarkerColor(Color.BLUE);
        series1.setMarkerColor(java.awt.Color.RED);

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }
}