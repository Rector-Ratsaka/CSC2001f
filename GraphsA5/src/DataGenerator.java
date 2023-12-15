import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    /**main method for DataGenerator class where graphs/ datasets are
     * randomly generatedwith a specified number of vertices and edges,
     * as well as methods to write the generated data to files.
     * @param args
     */
    public static void main(String[] args) {
        Random random = new Random();
        int numVertices = 30; // change this to set the number of vertices
        int numEdges = 315; // change this to set the number of edges
        int sourceCon = numVertices/3 ; //number of vertices connected to source vertex

        String[] vertices = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {//ensure the dataset looks good with a good spacing/formating
            if (i<10)
                vertices[i] = "Node0"+0+i;
            else
                vertices[i] = "Node0"+i;
        }

        ArrayList<String> edgeSet = new ArrayList<>(); // keep track of edges that have already been generated
        String[] edges = new String[numEdges];
        for (int i = 0; i < numEdges; i++) {//add random vertices to graph/dataset
            int u = random.nextInt(numVertices);
            int v = random.nextInt(numVertices);
            int cost = random.nextInt(10) + 1; // generate random edge cost
            if (i<sourceCon) { //connect source vertex to some vertices to ensure that it is connecte either directly or indirectly to other vertices
                edges[i] = vertices[0] + " " + vertices[i+1] + " " + cost;
            }
            else {
                while (u == v || v == 0 || u == 0 || edgeSet.contains(vertices[u] + " " + vertices[v])) { // make sure the vertex is not connected to itself, Node000 is the source vertex, and the edge doesn't already exist
                    u = random.nextInt(numVertices);
                    v = random.nextInt(numVertices);
                }
                edges[i] = vertices[u] + " " + vertices[v] + " " + cost; //store edges in an array then later stored in a text file
                edgeSet.add(vertices[u] + " " + vertices[v]); // add new edge to the edgeSet
            }
        }

        try {
            FileWriter writer = new FileWriter("data/dataV"+numVertices+"E"+numEdges+".txt"); //change text file name and save dataset to data directory
            for (String edge : edges) {
                writer.write(edge + "\n");
            }
            writer.close();
            System.out.println("Data saved to data/ as a text file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the graph: " + e.getMessage());
        }
    }
}