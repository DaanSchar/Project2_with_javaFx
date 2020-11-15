package graph;

import graph.ColEdge;
import graph.Graph;

public class Vertex
{

    private ColEdge[] e;
    private int[] connectedVerticesArray;
    private int[] convertedConnectedVerticesArray;
    private int n;
    private Graph graph;


    public Vertex(Graph graphObject)
    {
        graph = graphObject;
        e = graph.e;
        n = graph.n;

    }


    /** creates an array containing all connected vertices of a vertex
     *
     * @param vertex the vertex we wish to have an array of
     */
    public void connVerArray(int vertex)
    {

        connectedVerticesArray = new int[n];
        int c = 0;

        for(int i = 0; i < e.length; i++)
        {
            if(e[i].u == vertex)
            {
                connectedVerticesArray[c] = e[i].v;
                c++;
            }
            if(e[i].v == vertex)
            {
                connectedVerticesArray[c] = e[i].u;
                c++;
            }
        }

        //this part converts the array of n long to an array of c long
        convertedConnectedVerticesArray = new int[c];

        for(int i = 0; i < c; i++)
        {
            convertedConnectedVerticesArray[i] = connectedVerticesArray[i];
        }

        connectedVerticesArray = convertedConnectedVerticesArray;
    }


    /** returns the array containing all adjacent vertices of vertex
     *
     * @param vertex the vertex we wish to have an array of
     * @return an integer array
     */
    //returns the array containing all vertices connected to vertex
    public int[] getConnVerArray(int vertex)
    {
        connVerArray(vertex);
        return connectedVerticesArray;
    }


    /**
     * prints the array of vertices connected to vertex
     */
    public void printConVerArray()
    {
        System.out.println("the graph.Vertex is connected to: ");

        for(int i = 0; i < connectedVerticesArray.length; i++)
        {
            System.out.print(connectedVerticesArray[i] + " ");
        }
        System.out.println();
    }


}
