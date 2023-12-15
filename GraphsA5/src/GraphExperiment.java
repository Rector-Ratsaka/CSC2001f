import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GraphExperiment {
    /**main method for GraphExperiment class.
     * It creates a `Graph` object, adds the generated data to the graph,
     * and performs Dijkstra's algorithm on the graph to find the shortest paths.
     * It then outputs vertices, edges, vertices count, edges count and
     * priority queue count for each generated dataset.
     */
    public static void main(String[] args) {
        // Create a new Graph object to hold the edges
        Graph g = new Graph();

        try {
            // Set the number of vertices and edges to read from the input file
            int dataV = 10;
            int dataE = 20;

            // Open the input file for reading
            FileReader fin = new FileReader("data/dataV" + dataV + "E" + dataE + ".txt");
            Scanner graphFile = new Scanner(fin);

            // Read the edges and insert them into the graph
            String line;
            while (graphFile.hasNextLine()) {
                line = graphFile.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                // Check that each line contains three tokens (source, dest, and cost)
                try {
                    if (st.countTokens() != 3) {
                        System.err.println("Skipping ill-formatted line " + line);
                        continue;
                    }

                    // Parse the source, dest, and cost from the line and add the edge to the graph
                    String source = st.nextToken();
                    String dest = st.nextToken();
                    int cost = Integer.parseInt(st.nextToken());
                    g.addEdge(source, dest, cost);
                } catch (NumberFormatException ex) {
                    // If the cost is not a valid integer, skip the line and continue
                    System.err.println("Skipping ill-formatted line " + line);
                }
            }

            // Run Dijkstra's algorithm starting from a given node
            g.dijkstra("Node000");

            // Print out some statistics about the graph
            System.out.println( dataV+ ", " + dataE + ", " + g.getvCount() + ", " + g.geteCount() + ", " + g.getPqCount());

        } catch (IOException e) {
            // If there was an error reading the input file, print the error message
            System.err.println(e);
        }
    }
}
