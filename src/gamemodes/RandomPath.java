package gamemodes;

import graph.Graph;

import java.util.Random;

/**
 * this class determines a randomly ordered path of vertices which is used for gamemode3
 *
 * @author Leo
 */

public class RandomPath
{
    private Graph graph;
    private int[] vertexPath;
    private int[] randomPath;
    private int max;
    private int count;

    public RandomPath (int totalVertices)
    {
        max = totalVertices;
        vertexPath = vertexPath;
        randomPath = randomPath;
        count = count;
    }

    /**
     * store vertices of a graph in vertexPath
     */

    public int[] makePath (int max)
    {
        int[] newVertexPath = new int[max];
        for(int i=0; i < max; i++)
        {
            newVertexPath[i] = i + 1;
        }
        vertexPath =  newVertexPath;
        return vertexPath;
    }

    /**
     * implements Fisher-Yates shuffle
     */

    public int [] shuffle (int [] array)
    {
        Random random = new Random();

        for (int i = array.length-1; i > 0; i--)
        {
            int index = random.nextInt(i+1);

            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    /**
     * randomizes contents of vertexPath
     * @return randomPath
     */
    public int [] makeRandomPath()
    {
        makePath(max);
        int[] newRandomPath = shuffle(vertexPath);
        randomPath = newRandomPath;
        return randomPath;
    }

    public int[] getVertexPath()
    {
        return vertexPath;
    }

    public int[] getRandomPath()
    {
        return randomPath;
    }

}
