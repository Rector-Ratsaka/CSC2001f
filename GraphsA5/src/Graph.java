/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void dijkstra( String s )    --> Single-source weighted
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    Map<String,Vertex> vertexMap = new HashMap<>( );
    private int pqCount = 0;
    private int vCount = 0;
    private int eCount = 0;
    /**getter for priority queue count operation to be used for the experiment*/
    public int getPqCount() {
        return pqCount;
    }
    /**getter for vertices count operation to be used for the experiment*/
    public int getvCount() {
        return vCount;
    }
    /**getter for edges count operation to be used for the experiment*/
    public int geteCount() {
        return eCount;
    }

    /**
     * Add a new edge to the graph.
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            System.out.print( "(Cost is: " + w.dist + ") " );
            printPath( w );
            System.out.println( );
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );
            System.out.print( " to " );
        }
        System.out.print( dest.name );
    }

    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    public void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }


    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra)
     * using priority queues based on the binary heap
     */

    public void dijkstra( String startName )
    {
        PriorityQueue<Path> pq = new PriorityQueue<>( );

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;


        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            pqCount+=(int)((Math.log(pq.size()))/Math.log(2)); //priority queue processing
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            if( v.scratch != 0 )  // already processed v
                continue;
            // vertex is processed
            vCount++;

            v.scratch = 1;
            nodesSeen++;

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;

                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                //edge is processed
                eCount++;
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                    pqCount+=(int)((Math.log(pq.size()))/Math.log(2)); //priority queue processing
                }
            }
        }
    }
}
