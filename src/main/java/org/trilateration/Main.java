package org.trilateration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_COMBINATIONS = 3;

    public static void main(String[] args) throws IOException {
        List<List<Nodes>> tripleNodesCombinations = TripleCombinations.getTripleCombinations(NUMBER_OF_COMBINATIONS);

        //Prints every possible combination of nodes
        System.out.println("Combination of " + NUMBER_OF_COMBINATIONS + ":");
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
        for (List<Nodes> tripleNodesCombination : tripleNodesCombinations) {
            Trilateration.trilateration(tripleNodesCombination);
        }
    }
}