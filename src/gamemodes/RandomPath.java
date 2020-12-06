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
     * @param vertexPath array containing all vertices of the graph
     * @return shuffledVertexPath
     */

    public int [] shuffle (int [] vertexPath)
    {
        Random random = new Random();

        for (int i = vertexPath.length-1; i > 0; i--)
        {
            int index = random.nextInt(i+1);

            int temp = vertexPath[i];
            vertexPath[i] = vertexPath[index];
            vertexPath[index] = temp;
        }
        int [] shuffledVertexPath = vertexPath;
        return shuffledVertexPath;
    }


    public int[] getRandomPath()
    {
        return randomPath;
    }

}
