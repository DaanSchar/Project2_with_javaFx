package gamemodes;

import graph.Colors;
import graph.Graph;

import java.util.Scanner;

/**
 * This class takes care of the player's choices of vertex and what color they want the vertex to be
 *
 * @author Leo
 */

public class PickVertexColor {
    private int vertex;
    private int color;
    private int playNumber;
    private Colors colorsObject;
    private static Scanner scanner;
    private static int lastAssignedNumber;
    private Warning warning;
    private int n;
    private int newVertex;

    public PickVertexColor(Graph graph) {
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
    public void pickVertex() {

        boolean validVertex = false;

        System.out.println("Pick vertex");
        newVertex = scanner.nextInt();

        if (newVertex <= n) {
            validVertex = true;
        }

        while (validVertex == false) {

            System.out.println("This vertex does not exist in your graph. please pick a different vertex");
            newVertex = scanner.nextInt();

            if (newVertex <= n) {
                validVertex = true;
            }

        }
        vertex = newVertex;
    }

    /**
     * let's you set the picked vertex for the player, this is useful for game mode 3
     */
    public void setPickedVertex(int aVertex) {
        vertex = aVertex;
        color = 0;
    }


    /**
     * lets the player pick a color. if the color the player picked isn't valid ai. there is an adjacent vertex with the
     * same color, it'll will ask the player to pick a different color.
     */
    public void pickColor() {
        boolean needWarning = true;

        while (needWarning) {
            System.out.println("Pick color");
            int newColor = scanner.nextInt();
            color = newColor;

            needWarning = warning.needWarning(vertex, color);

            System.out.print("need warning = " + needWarning);
            if (needWarning) {
                System.out.print(", please pick a different color");
            }
            System.out.println();
        }

        colorsObject.setColorOfVertex(vertex, color);

    }


    public void pickColorUp(int color) {
        this.color = color + 1;
        colorsObject.setColorOfVertex(vertex, this.color);
    }

    public void pickColorDown(int color)
    {
        this.color = color - 1;
        colorsObject.setColorOfVertex(vertex, this.color);
    }


    /**
     * lets the player pick a color. if the color the player picked isn't valid ai. there is an adjacent vertex with the
     * same color, it'll will ask the player to pick a different color.
     */
    public void pickColor(int vertex)
    {
        vertex = vertex;
        boolean needWarning = true;

        //System.out.println("This is vertex " + vertex);

        while(needWarning)
        {
            System.out.println("Pick color");
            int newColor = scanner.nextInt() ;
            color = newColor;

            needWarning = warning.needWarning(vertex, color);

            System.out.print("need warning = " + needWarning);
            if(needWarning)
            {
                System.out.print(", please pick a different color");
            }
            System.out.println();
        }

        colorsObject.setColorOfVertex(vertex, color);

    }

    /**
     * returns the vertex the player picked
     * @return int vertex
     */
    public int getVertex()
    {
        return vertex;
    }


    /**
     * returns the color the player picked
     * @return int color
     */
    public int getColor()
    {
        return color;
    }


    /**
     * returns the play number, this way we can measure how often the player has assigned colors
     * @return int playNumber
     */
    public int getPlayNumber()
    {
        return playNumber;
    }
}
