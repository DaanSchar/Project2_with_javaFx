package visuals.game;

import chromatics.branching.Branching;
import chromatics.branching.ChromaticNum;
import chromatics.branching.Coloring;
import graph.ColEdge;
import graph.Colors;
import graph.Graph;

import java.util.Random;

public class Hint
{
    private Graph graph;
    private Colors colorsCopy;
    private int[] chromArray;

    // this graph object contains the same values as graph, but an empty color object
    private Graph emptyGraph;

    private int[] smallestColorVertices;
    private int[] largestColorVertices;

    public Hint(Graph graph)
    {
        this.graph = graph;

        System.out.println("hint running!");

        // making a copy of graph and resetting the color object
        emptyGraph = new Graph();
        emptyGraph.e = graph.e;
        emptyGraph.n = graph.n;
        emptyGraph.m = graph.m;

        calculate();
        makeSmallestColorVertices();
        makeLargestColorVertices();
    }





    private void calculate()
    {

        int calc = 10;
        int[] vertexPath = new int[graph.n];
        chromArray = new int[calc];



        //loop to create template vertexpath, so {1,2,3,...,n}
        for(int i = 0; i < graph.n; i++)
        {
            vertexPath[i] = i+1;
        }

        graph.getColor().printColorArray();

        boolean found = false;

        //ReCalculates the chromatic number with a different random path x times and gets the smallest number from this
        //for(int i = 0; i < calc; i++)
        while(found == false)
        {
            randomize(vertexPath, vertexPath.length);

            Coloring coloring = new Coloring(emptyGraph, vertexPath);
            Colors colors = coloring.getColorOfColoringObject();

            ChromaticNum chromatic = new ChromaticNum(colors.getColorArray());
            //chromArray[i] = chromatic.getChromNum();

            // if the calculated chromatic number is equal to the chromatic number of the graph calculated previously
            if(chromatic.getChromNum() == graph.getChromNum())
            {

                // giving the color object of the the coloring class to the empty graph
                emptyGraph.colors = coloring.getColorOfColoringObject();

                System.out.println("chroms are the same!");


                // measuring how many vertices haven't been colored yet by the user(color = 0)
                int nonColored = 0;
                for(int i = 0; i < graph.n; i++)
                {
                    if(graph.getColor().getColorOfVertex(i+1) == 0)
                    {
                        nonColored++;
                    }
                }
                System.out.println("nonColored = " + nonColored);


                int totalColored = 0;

                for (int j = 0; j < graph.n; j++)
                {
                    // increments totalColored if it finds the same colors in both arrays
                    if(graph.getColor().getColorOfVertex(j+1) == emptyGraph.getColor().getColorOfVertex(j+1) && graph.getColor().getColorOfVertex(j+1) != 0)
                    {
                        totalColored++;

                        // when the total colored vertices + the total not colored vertices = total vertices
                        if(totalColored + nonColored == graph.n)
                        {
                            System.out.println("chromatic number of calulation: " + chromatic.getChromNum());
                            System.out.println("colors of graph");
                            graph.getColor().printColorArray();
                            System.out.println("colors of emptygraph");
                            emptyGraph.getColor().printColorArray();

                            // stops the while loop
                            found = true;
                        }
                    }
                }
            }
        }
    }


    /**
     * method to find the largest color in the color object of the algorithm(not the user)
     */
    private void makeLargestColorVertices()
    {
        int[] colors = emptyGraph.getColor().getColorArray();

        int max = colors[0];
        int maxVertex = 0;
        boolean firstIsMax = true;

        for(int i = 0; i < graph.getN(); i++)
        {
            if(colors[i] > max)
            {
                max = colors[i];
                maxVertex = i + 1;
                firstIsMax = false;
            }
        }

        // fixes problem that if the biggest vertex is color[0], it returns 0, when we want 1
        if(firstIsMax)
        {
            maxVertex = 1;
        }

        int[] largestColorVertices = new int[graph.getN()];
        int count = 0;

        // puts all vertices that contain that largest number in an array
        for(int i = 0; i < graph.getN(); i++)
        {
            if(colors[i] == max)
            {
                largestColorVertices[count] = (i + 1);
                count++;
            }
        }

        int[] finalArray = new int[count];

        // puts all values of largestColorVertices[] inside a properly sized array
        for(int i = 0; i < count; i++)
        {
            finalArray[i] = largestColorVertices[i];
        }

        this.largestColorVertices = finalArray;
    }


    /**
     * does the same as getLargestColorVertices but then for the smallest color
     */
    private void makeSmallestColorVertices()
    {
        int[] colors = emptyGraph.getColor().getColorArray();

        int min = colors[0];
        int minVertex = 0;
        boolean firstIsMin = true;

        for(int i = 0; i < graph.getN(); i++)
        {
            if(colors[i] < min)
            {
                min = colors[i];
                minVertex = i + i;
                firstIsMin = false;
            }
        }

        if(firstIsMin)
        {
            minVertex = 1;
        }

        int[] smallestColorVertices = new int[graph.getN()];
        int count = 0;

        for(int i = 0; i < graph.getN(); i++)
        {
            if(colors[i] == min)
            {
                smallestColorVertices[count] = (i + 1);
                count++;
            }
        }

        int[] finalArray = new int[count];

        for(int i = 0; i < count; i++)
        {
            finalArray[i] = smallestColorVertices[i];
        }

        this.smallestColorVertices = finalArray;
    }



    /**
     * randomizes the order of an array
     * @param arr array to be randomized
     * @param n total vertices
     */
    private static void randomize( int arr[], int n)
    {
        Random r = new Random();


        for (int i = n-1; i > 0; i--) {

            int j = r.nextInt(i+1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int[] getSmallestColorVertices()
    {
        return smallestColorVertices;
    }

    public int[] getLargestColorVertices()
    {
        return largestColorVertices;
    }


}
