/**
 * This class generates a random graph with some inputs from the user
 *
 * @author Patrick, Felix, Daan
 */

package graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;


public class GenerateRandomGraph {

    private int chosenVertices;
    private int chosenEdges;
    private int maxPossibleEdges;
    private ColEdge[] graphWithAllEdges;
    private ColEdge[] randomGraph;

    public GenerateRandomGraph(int vertices, int edges) throws IOException {
        chosenVertices = vertices;
        chosenEdges = edges;
        maxPossibleEdges = calcMaxEdges(vertices);
        randomGraph = new ColEdge[chosenEdges];

        generateAllEdges();
        readGraph();

        initColEdge(randomGraph);
        randomGraph = pickRandom();

    }

    /**
     * generates a txt file with all possible edges for the amount of vertices that the player chooses.
     *
     * @throws IOException
     */
    private void generateAllEdges() throws IOException {
        File file = new File("all_possible_edges_of_random_graph.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println("VERTICES = " + chosenVertices);
        pw.println("EDGES = " + maxPossibleEdges);

        for (int i = 1; i < chosenVertices + 1; i++) {
            for (int j = i + 1; j < chosenVertices + 1; j++) {
                pw.println(i + " " + j);
            }
        }

        pw.close();
    }


    /**
     *
     * graphWithAllEdges is a gameVisuals.ColEdge[] with all possible edges and is maxPossibleEdges long.
     * chosenVertices is an int that represents the amount of vertices the player wants.
     * chosenEdges is an int that represents the amount of Edges the player wants.
     * now make a method/make multiple methods so that we get a gameVisuals.ColEdge[] called randomGraph
     * that will contain chosenEdges amount of random ColEdges from graphWithAllEdges
     *
     */


    /**
     * turns the read file into a gameVisuals.ColEdge array.
     */
    private void readGraph() {
        ReadGraph randGraph = new ReadGraph("all_possible_edges_of_random_graph.txt");
        graphWithAllEdges = randGraph.getGraph();
    }


    /**
     * calculates the total amount of possible edges
     *
     * @param x chosenVertices
     * @return total Possible Edges
     */
    private int calcMaxEdges(int x) {
        if (x == 1) {
            return 0;
        } else {
            return x - 1 + calcMaxEdges(x - 1);
        }
    }


    /**
     * initializes a gameVisuals.ColEdge Array, or puts empty ColEdges int every index of e
     *
     * @param e gameVisuals.ColEdge array
     */
    public void initColEdge(ColEdge[] e) {
        for (int i = 0; i < e.length; i++) {
            ColEdge c = new ColEdge();
            e[i] = c;
        }
    }


    /**
     * randomly shuffles all possible edges and puts them inside a gameVisuals.ColEdge[]
     * @return gameVisuals.ColEdge[] randomGraph
     */
     public ColEdge[] pickRandom()
     {
       //  The pathArray is defined as the number of Edges picked by the Player
       //  This will later create the path of how many randomGraph Edges are returned
       int[] pathArray = new int[chosenEdges];

       //  The totalEdgesArray is a new Array that is all Edges long in order
       //  for the randomizer to pick out of all of them
       int[] totalEdgesArray = new int[graphWithAllEdges.length];

       Random randomizer = new Random();

       //   Loop goes through the totalEdgesArray and gives them values 1 to the max
       //   The fact that the first one gets value 1 instead of 0 is important
        for(int i = 0; i < totalEdgesArray.length; i++)
        {
            totalEdgesArray[i] = i + 1;
        }

       //
        for (int i = graphWithAllEdges.length-1; i > 0; i--)
        {
                int index = randomizer.nextInt(i+1);

                int temp = totalEdgesArray[i];
                totalEdgesArray[i] = totalEdgesArray[index];
                totalEdgesArray[index] = temp;
        }


       //   In the bound of chosenEdges, assign the pathArray values to the totalEdgesArray values
        for(int i = 0 ; i < chosenEdges; i++)
        {
            pathArray[i] = totalEdgesArray[i];
        }

       //   Lastly, give randomGraph Edge positions by assigning the random pathArray values in the
       //   random graphWithAllEdges gameVisuals.ColEdge to it
        for(int i = 0; i < chosenEdges; i++)
        {
            randomGraph[i] = graphWithAllEdges[pathArray[i]-1];
        }

        return randomGraph;
     }


    /**
     * prints out the Coledge[] array
     */
    private void printColEdgeArray(ColEdge[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i].u + " " + array[i].v);
        }
    }

    public ColEdge[] getRandomGraph()
    {
        return randomGraph;
    }
}
