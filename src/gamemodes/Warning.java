package gamemodes;

import graph.ColEdge;
import graph.Colors;
import graph.Graph;
import graph.Vertex;

/**
 *  this class checks if the color that the player picks doesnt have a vertex adjacent to it with the same color.
 *
 * @author Daan
 */

public class Warning
{

    private int n;
    private int[] colorList;
    private int[] connVerArray;
    private ColEdge[] e;
    private Colors colors;
    private Graph graph;


    /**
     * Constructor
     * @param graphObject the graph and all its information
     */
    public Warning(Graph graphObject)
    {
        graph = graphObject;
        colors = graph.colors;
        e = graph.e;
        colorList = colors.getColorArray();
        n = colorList.length;
    }

    /**
     * checks if there are adjacent vertices that contain the same color as the color the player picked.
     *
     * @param vertex the vertex the player chooses to color in
     * @param colorChoice the color the player wishes the vertex to be
     * @return true if the color is the same as the color of an adjacent vertex
     */
    public boolean needWarning(int vertex, int colorChoice)
    {

        Vertex vert = new Vertex(graph);
        connVerArray = vert.getConnVerArray(vertex);


        for(int i = 0; i < connVerArray.length ; i++)
        {
            if(colors.getColorOfVertex(connVerArray[i]) == colorChoice)
            {
                return true;
            }
        }

        return false;
    }

}
