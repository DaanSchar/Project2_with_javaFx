/**
 * this class handles in making arrays for each vertex containing all other vertices
 * which are connected to that vertex
 */

package graph;

public class Vertex
{

    public int[] connectedVerticesArray;
    public int[] convertedConnectedVerticesArray;
    private int n;
    private ColEdge[] e;
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


    public int[] getConnVerArray(int vertex)
    {
        connVerArray(vertex);
        return connectedVerticesArray;
    }

}
