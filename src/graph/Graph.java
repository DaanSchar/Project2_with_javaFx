package graph;

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

    public ColEdge[] getE()
    {
        return e;
    }

    public Colors getColor()
    {
        return colors;
    }

}
