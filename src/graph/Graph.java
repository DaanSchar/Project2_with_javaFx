package graph;

import chromatics.branching.Branching;
import menu.Menu;
import visuals.menu.GraphicalMenu;

/**
 * contains some usefull variables of the graph like the graph itself and the color object related to that graph.
 * @author Daan
 */

public class Graph
{

    public int n;
    public int m;
    public ColEdge[] e;
    public Colors colors;
    public int chromNum;
    private static int calc = 250;

    /**
     * retrieves information for the graph from the menu.Menu class
     */
    public Graph()
    {
        n = GraphicalMenu.getMenuChoice().getVertices();
        m = GraphicalMenu.getMenuChoice().getEdges();
        e = GraphicalMenu.getMenuChoice().getE();
        colors = new Colors(n);
        System.out.println("n = " + n);

        //get the chromNum in the graph object - making it easier to retrieve at a later point
        Branching branching = new Branching(this, calc);
        chromNum = branching.getBranchingNum();
    }

    /**
     * prints out the graph
     */
    public void printGraph()
    {
        for(int i = 0; i < e.length; i++)
        {
            System.out.println(e[i].u + " " + e[i].v );
        }
    }

    public int getN()
    {
        return n;
    }

    public int getM()
    {
        return m;
    }

    public int getChromNum()
    {
        return chromNum;
    }

    public ColEdge[] getE()
    {
        return e;
    }

    public Colors getColor()
    {
        return colors;
    }

}
