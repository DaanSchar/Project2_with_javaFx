package chromatics.branching;

import graph.ColEdge;
import graph.Colors;
import graph.Graph;
import graph.Vertex;

/**
 * this is a helper class for calculating the chromatic number
 * this class takes care of coloring the graph and checking if that color is valid(the color adjacent
 * to it isnt the same color)
 *
 * @author Daan
 */


public class Coloring
{

    private int n;
    private ColEdge[] e;
    private int[] path;
    private int[] conVerArray;
    private Colors colorsOfColoringObject;

    static boolean DEBUG = false;



    public Coloring(Graph graph, int[] vertexPath)
    {
        n = graph.n;
        e = graph.e;
        path = vertexPath;

        colorsOfColoringObject = new Colors(n);
        Vertex vertex = new Vertex(graph);


        for(int j = 0; j < path.length; j++)
        {

            int vertexx = path[j];
            conVerArray = vertex.getConnVerArray(vertexx);
            colorsOfColoringObject.setColorOfVertex(vertexx, 1);

            for (int i = 0; i < conVerArray.length; i++)
            {
                if (colorsOfColoringObject.getColorOfVertex(conVerArray[i]) >= colorsOfColoringObject.getColorOfVertex(vertexx))
                {
                    colorsOfColoringObject.setColorOfVertex(vertexx, colorsOfColoringObject.getColorOfVertex(conVerArray[i]) + 1);
                }
            }
        }
    }

    /**
     * returns the graph.Color Object colorOfColoringObject
     * @return graph.Color colorfOfColoringObject
     */
    public Colors getColorOfColoringObject()
    {
        return colorsOfColoringObject;
    }

}
