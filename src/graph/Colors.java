package graph;

/**
 * this class stores the color of each vertex of the graph inside colorArray[]
 *
 * @author Daan
 */

public class Colors
{

    private int n;
    private int colorArray[];

    public Colors(int totalVertices)
    {
        n = totalVertices;
        colorArray = new int[n];


        //this initializes the colorArray AKA sets all the colorValues to 0
        for(int i = 0; i < colorArray.length; i++)
        {
            colorArray[i] = 0;
        }
    }


    /** returns the color of vertex
     *
     * @param vertex the vertex we wish to retrieve the color of
     * @return color of vertex
     */
    public int getColorOfVertex(int vertex)
    {
        return colorArray[vertex-1];
    }


    /** sets the color value of vertex
     *
     * @param vertex the vertex we wish to change the color of
     * @param color the color we wish to change vertex to
     */
    public void setColorOfVertex(int vertex, int color)
    {
        colorArray[vertex-1] = color;
    }


    /** returns the array containg each vertex and their color
     *
     * @return int array containg all vertices and their color
     */
    public int[] getColorArray()
    {
        return colorArray;
    }


    /**
     * prints out colorArray
     */
    public void printColorArray()
    {
        for(int i = 0; i < colorArray.length; i++)
        {
            System.out.println("Vertex: " + (i + 1) + ";    Color: " + colorArray[i]);
        }
        System.out.println();
    }

    /**
     * counts number of distinct colors in colorArray
     * @return number of distinct colors
     */

    public int numberOfColors()
    {
        int count = 0;
        for(int i = 0; i < colorArray.length; i++)
        {
            int j = 0;
            for(j = 0; j < i; j++)
                if(colorArray[i] == colorArray[j])
                {
                    break;
                }
            if (i == j)
            {
                count++;
            }

        }
        return count;

    }


}
