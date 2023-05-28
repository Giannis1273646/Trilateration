package org.trilateration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripleCombinations {

    public static List<List<Integer>> findTripleCombinations(int n, int combinationLength) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();

        // Generate all triple combinations using backtracking
        backtrack(result, combination, n, combinationLength,0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> combination, int n, int combinationLength, int start) {

        // Base case: If combination has reached size 3, add it to the result
        if (combination.size() == combinationLength) {
            result.add(new ArrayList<>(combination));
            return;
        }

        // Generate combinations by selecting elements starting from the given start index
        for (int i = start; i < n; i++) {
            // Add the current element to the combination
            combination.add(i);

            // Recursively generate combinations with the next index
            backtrack(result, combination, n, combinationLength,i + 1);

            // Remove the current element from the combination for backtracking
            combination.remove(combination.size() - 1);
        }
    }

    public static List<List<Nodes>> getTripleCombinations(int combinationLength) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        int numberOfNodes = 0, i=0;

        // Read all the nodes from the file
        File file = new File("src/main/resources/files/nodes.json");
        List<Nodes> nodesList = objectMapper.readValue(file, new TypeReference<List<Nodes>>() {});

        // Count the number of nodes in the file
        for(Nodes nodes : nodesList){
            i++;
            if(nodes.getId().equals("node".concat(String.valueOf(i)))){
                numberOfNodes++;
            }
        }

        List<List<Integer>> combinations = findTripleCombinations(numberOfNodes, combinationLength);
        List<List<Nodes>> tripleNodesCombinations = new ArrayList<>();

        // Store the combinations in the tripleNodesCombinations list,
        // after assigning the correct node objects based on their names
        for (List<Integer> combination : combinations) {
            List<Nodes> nodesObjects = new ArrayList<>();
            for(Integer node : combination){
                String nodeName = "node".concat(String.valueOf(node+1));

                // Find the corresponding Node object based on the name
                Nodes nodeObject = findNodeByName(nodesList, nodeName);
                if (nodeObject != null) {
                    nodesObjects.add(nodeObject);
                } else {
                    // Handle case when the Node object is not found
                    // You can throw an exception or handle it as needed
                }
            }

            tripleNodesCombinations.add(nodesObjects);
        }

        return tripleNodesCombinations;
    }

    // Helper method to find the Node object based on its name
    private static Nodes findNodeByName(List<Nodes> nodesList, String nodeName) {
        for (Nodes node : nodesList) {
            if (node.getId().equals(nodeName)) {
                return node;
            }
        }
        return null; // Node object not found
    }
}
