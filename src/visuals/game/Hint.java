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

    public Hint(Graph graph)
    {
        this.graph = graph;


        // making a copy of graph and resetting the color object
        emptyGraph = new Graph();
        emptyGraph.e = graph.e;
        emptyGraph.n = graph.n;
        emptyGraph.m = graph.m;

        System.out.println("hint!");
        calculate();
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

        System.out.println("thiss!!!");
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
                    if(graph.getColor().getColorOfVertex(j+1) == emptyGraph.getColor().getColorOfVertex(j+1) && graph.getColor().getColorOfVertex(j+1) != 0)
                    {
                        totalColored++;

                        if(totalColored + nonColored == graph.n)
                        {
                            System.out.println(graph.getColor().getColorOfVertex(j + 1) + " = " + emptyGraph.getColor().getColorOfVertex(j + 1));
                            System.out.println("chromatic number of calulation: " + chromatic.getChromNum());
                            System.out.println("colors of graph");
                            graph.getColor().printColorArray();
                            System.out.println("colors of emptygraph");
                            emptyGraph.getColor().printColorArray();
                            found = true;
                        }
                    }
                }
            }
        }
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


}
