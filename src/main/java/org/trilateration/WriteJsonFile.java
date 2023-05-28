package org.trilateration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteJsonFile {

    private static final String FILENAME = "src/main/resources/files/nodes.json";

    public static void writeNodes(String id, double x, double y, double distance){
        Nodes nodes = new Nodes(id, x, y, distance);
        List<Nodes> nodesList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File(FILENAME);

        // Write JSON data to file
        try {
            nodesList = readNodesFromFile(FILENAME);
            nodesList.add(nodes);
            objectMapper.writeValue(file, nodesList);
            System.out.println("JSON data written to file: " + FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Nodes> readNodesFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        if (file.exists()) {
            return objectMapper.readValue(file, new TypeReference<List<Nodes>>() {});
        } else {
            return new ArrayList<>();
        }
    }
}
