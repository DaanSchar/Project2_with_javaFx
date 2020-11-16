/**
 * class used to make a grid where all vertices can be drawn on
 *
 * @author Daan
 */

package visuals.game;

import graph.Graph;

public class Grid
{

    private int h;
    private int v;
    private String[] gridPositions;
    private int totalGridPositions;
    private int gridScale;
    private int sideMenuSize;
    private int topMenuSize;
    private int n;

    public Grid(int horizontalPixels, int verticalPixels, Graph graph)
    {

        sideMenuSize = 200;
        topMenuSize = 30;
        h = horizontalPixels - 50 - sideMenuSize;
        v = verticalPixels - 100 - topMenuSize;

        n = graph.getN();

        gridScale = 40;
        scaleGrid();

        totalGridPositions = (h/gridScale)*(v/gridScale);
        gridPositions = new String[totalGridPositions];
        System.out.println("total = " + totalGridPositions);

        makeGrid();
        shuffle();
    }

    private void scaleGrid()
    {
        if(n < 10)
        {
            gridScale = 200;
        } else {

            if(n < 20)
            {
                gridScale = 80;
            } else {

                if(n < 50)
                {
                    gridScale = 30;
                } else {

                    if(n < 100)
                    {
                        gridScale = 25;
                    } else {

                        if(n < 150)
                        {
                            gridScale = 20;
                        } else {

                            if( n < 300)
                            {
                                gridScale = 15;
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * turns the index of gridPositions from a string to 2 integers,x and y and returns these
     * values as a coordinate object
     */
    public Coordinate getRandomCord(int index)
    {
        Coordinate cord = new Coordinate();

        String string = gridPositions[index];
        String[] parts = string.split(" ");
        cord.x = Integer.parseInt(parts[0]);
        cord.y = Integer.parseInt(parts[1]);

        return cord;
    }


    /**
     * makes a "grid pattern". it generates all possible positions for a vertex to be placed and adds it to
     * gridPositions.
     */
    private void makeGrid()
    {
        int k = 0;

        while (k < (totalGridPositions))
        {
            for (int i = 1; i <= h; i++)
            {
                for (int j = 1; j <= v; j++)
                {
                    if((i%gridScale == 0) && (j%gridScale == 0))
                    {
                        gridPositions[k] = String.valueOf(i + sideMenuSize) + " " + String.valueOf(j + topMenuSize);
                        k++;
                    }
                }
            }
        }
    }


    /**
     * @return total gridPositions
     * if this value is smaller than the total vertices of a graph, the graph will not be able to generate
     */
    public int getTotalGridPositions()
    {
        return totalGridPositions;
    }


    /**
     * shuffles the content of gridPositions to get a random order of all possible possitions on the canvas
     */
    protected void shuffle()
    {
        for(int i = 0; i < gridPositions.length; i++)
        {
            int s = i + (int)(Math.random() * (gridPositions.length - i));

            String temp = gridPositions[s];
            gridPositions[s] = gridPositions[i];
            gridPositions[i] = temp;
        }
    }
}



