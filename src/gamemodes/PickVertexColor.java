package gamemodes;

import graph.Colors;
import graph.Graph;

import java.util.Scanner;

/**
 * This class takes care of the player's choices of a vertex and associated color
 *
 * @author Leo
 */

public class PickVertexColor
{
    private int vertex;
    private int color;
    private int playNumber;
    private Colors colorsObject;
    private static Scanner scanner;
    private static int lastAssignedNumber;
    private Warning warning;
    private int n;
    private int newVertex;

    public PickVertexColor(Graph graph)
    {
        colorsObject = graph.colors;
        scanner = new Scanner(System.in);
        warning = new Warning(graph);
        n = graph.n;
        color = 0;

        lastAssignedNumber++;
        playNumber = lastAssignedNumber;
    }

    /**
     * lets the player pick a vertex to pick a color.
     */
    public void pickVertex()
    {

        boolean validVertex = false;

        System.out.println("Pick vertex");
        newVertex = scanner.nextInt();

        if(newVertex <= n)
        {
            validVertex = true;
        }

        while(validVertex == false)
        {

            System.out.println("This vertex does not exist in your graph. please pick a different vertex");
            newVertex = scanner.nextInt();

            if(newVertex <= n)
            {
                validVertex = true;
            }

        }
        vertex = newVertex;
    }

    /**
     * lets you set the picked vertex for the player
     * @param aVertex that is chosen by the player
     */
    public void setPickedVertex(int aVertex)
    {
        vertex = aVertex;
        color = 0;
    }


    public void pickColorUp(int color)
    {
        this.color = color + 1;
        colorsObject.setColorOfVertex(vertex, this.color);
    }

    public void pickColorDown(int color)
    {
        if(color - 1 >= 0) {
            this.color = color - 1;
            colorsObject.setColorOfVertex(vertex, this.color);
        }
        else{
            System.out.println("Please pick a different color.");
        }
    }


    public int getVertex()
    {
        return vertex;
    }


    public int getColor()
    {
        return color;
    }


    public int getPlayNumber()
    {
        return playNumber;
    }
}
