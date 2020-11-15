package gamemodes;

import graph.Graph;

import java.util.Arrays;

/**
 * This class implements gamemode3
 *
 * @author Leo
 */

public class GameMode3
{

    private Graph graph;
    private int totalVertices;
    private boolean keepPlaying;
    private int[] newPath;
    private int vertex;
    private int count;


    public GameMode3(Graph graphObject)
    {
        graph = graphObject;
        keepPlaying = true;
        totalVertices = graph.n;
        count = count;
        RandomPath randomPath = new RandomPath(totalVertices);
        randomPath.makeRandomPath();
        newPath =  randomPath.getRandomPath();
        System.out.println(Arrays.toString(newPath));
    }

    public void play()
    {
        while(keepPlaying)
        {
            playerMove();
            graph.colors.printColorArray();
            end();
        }
    }

    /**
     * ends the game and prints the result
     * @return a boolean variable keepPlaying that stops method play() if false
     *
     */
    public boolean end()
    {
        if(count < newPath.length)
        {
            keepPlaying = true;
        }
        else
        {
            keepPlaying = false;
            System.out.println("This is the end of the game. \nYou used " + graph.colors.numberOfColors() + " colors.");
        }
        return keepPlaying;
    }

    public void playerMove()
    {
        PickVertexColor vertex1 = new PickVertexColor(graph);
        vertex1.pickColor(getNextVertex(newPath));
        count++;
    }

    /**
     * gets the next vertex from the random path
     */
    public int getNextVertex(int [] newPath)
    {
        int newVertex = newPath[count];
        vertex = newVertex;
        System.out.println("Please color vertex "  + vertex);
        return newPath[count];
    }
}
