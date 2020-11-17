package menu;

import chromatics.branching.Branching;
import gamemodes.*;
import graph.ColEdge;
import graph.GenerateRandomGraph;
import graph.Graph;
import graph.ReadGraph;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * This class takes care of all menu related tasks, for example importing user's graphs or returning generated graphs
 *
 * @author Dimitrina, Harry, Daan
 */

public class Menu
{

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Scanner scanner = new Scanner(System.in);
    private static GenerateRandomGraph randomGraph;
    private static Graph graph;
    private static int chromaticNumber;

    /**
     * excecutes the methods for introducing the player and setting up the game
     * @throws IOException
     */
    public static void startMenu() throws IOException {
        printintro();

        graphMenu();

        setGraph();

        getChromaticNumber();

        gamemodeMenu();
    }

    /**
     * returns graph to the menu class.
     */
    public static void setGraph()
    {

    }


    /**
     * returns the total vertices of the graph
     * @return int n
     */
    public static int getVertices()
    {
        return n;
    }

    /**
     * returns the total edges of the graph
     * @return int m
     */
    public static int getEdges()
    {
        return m;
    }


    /**
     * returns the graph
     * @return gameVisuals.ColEdge array e
     */
    public static ColEdge[] getGraph()
    {
        return e;
    }


    /**
     * prints text to introduce the player to the game and rules.
     */
    private static void printintro()
    {
        System.out.println("Welcome to Pick-a-graph.Graph!\n");
        System.out.println("Game instructions: ");
        System.out.println(" Three different games modes: ");
        System.out.println("   1. To the bitter end. The player simply has to colour the graph as quickly as " +
                "possible. \n      The computer does not allow the player to finish until the minimum " +
                "number of colours has been reached.");
        System.out.println("   2. Best upper bound in a fixed time frame. The player is given a fixed amount of " +
                "time and they have to find a colouring \n      with as few colours as possible in the given time. ");
        System.out.println("   3. Random  order.  Here  the  computer  generates  a  random  ordering  of  the  " +
                "vertices and the player has to pick the \n      colours of the vertices in exactly that order. " +
                "Once the colour of a vertex has been chosen, \n      it cannot be changed again. The goal for " +
                "the player is to use as few colours as possible.");
        System.out.println();
    }


    /**
     * asks the player if they want to import their own file or wants to have one randomly generated
     */
    private static void graphMenu() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Now it's time to choose whether you want to import your own file or to generate a random graph!");
        System.out.println("To import your own file type 1 or to generate a random graph type 2: ");
        System.out.println("Enter your choice:");

        int graphChoice = Integer.parseInt(input.nextLine());

        if(graphChoice == 1)
        {importFile();}
        else if(graphChoice == 2)
        {generateGraph();}

        graph = new Graph();

    }


    /**
     * returns the graph object containing variables like total edges
     * @return graph.Graph graph
     */
    public static Graph getGraphObject()
    {
        return graph;
    }


    /**
     * calls the graph.ReadGraph class to import the player's chosen file
     */
    private static void importFile()
    {

        System.out.println("What graph would you like to import?(type in: graph04_2020.txt)");

        //making a graph.ReadGraph object and inputting the next String
        ReadGraph file = new ReadGraph(scanner.next());


        //retrieving data from the graph.ReadGraph class to n, m and e
        n = file.getFileVertices();
        m = file.getFileEdges();
        e = file.getGraph();

        System.out.println("n = " + n + ". m = " + m);
    }


    /**
     * calculates the maximum possible edges of a graph with x vertices
     * @param x amount vertices
     * @return max possible edges as an integer
     */
    private static int calcMaxEdges(int x)
    {
        if (x == 1)
        {
            return 0;
        } else {
            return x - 1 + calcMaxEdges(x - 1);
        }
    }


    /**
     * calls the graph.GenerateRandomGraph class to generate a random graph
     */
    private static void generateGraph() throws IOException {
        System.out.println("how many vertices would you like your graph to have?");
        int chosenVertices = scanner.nextInt();
        System.out.println("how many edges would you like your graph to have?");
        int chosenEdges = scanner.nextInt();

        //checks if the user doesnt ask for an amount of edges that exceeds the amount of possible edges.
        while (chosenEdges > calcMaxEdges(chosenVertices)) {
            System.out.println("Please pick a different value of total edges. you have too many!");
            chosenEdges = scanner.nextInt();
        }

        //creating a graph.GenerateRandomGraph object
        randomGraph = new GenerateRandomGraph(chosenVertices, chosenEdges);

        n = chosenVertices;
        m = chosenEdges;
        e = randomGraph.getRandomGraph();
    }

    /**
     * method for calling the chromatics.branching class to calculate the chromatic number
     */
    public static void getChromaticNumber(){
        Instant start = Instant.now();

        Branching branching = new Branching(graph, 1);
        chromaticNumber = branching.getBranchingNum();
        System.out.println("chromatic number of this graph = " + chromaticNumber);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time fo calculation for non-multithreaded workload: " + timeElapsed);
    }


    /**
     * asks the player what gamemode they want to play and starts the corresponding gamemode
     */
    private static void gamemodeMenu()
    {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("What gamemode would you like to play?" + " To play \"To the bitter end\" type \"1\","+
                " to play \"Best upper bound in a fixed time frame\" type \"2\"," +
                " and to play \"Random order\" type \"3\"! ");
        System.out.println("Please enter your choice:");
        int playerChoice = Integer.parseInt(input.nextLine());
        if(playerChoice == 1)
        { startGamemode1();}
        else if(playerChoice == 2)
        { startGamemode2();}
        else if(playerChoice == 3)
        { startGamemode3(); }
        else if(playerChoice == 4)
        {
            return;
        }


    }


    /**
     * starts gamemode 1
     */
    private static void startGamemode1()
    {
        System.out.println("Starting gamemode 1!");

        GameMode1 gameMode1 = new GameMode1(graph);
        gameMode1.play();
    }


    /**
     * starts gamemode 2
     */
    private static void startGamemode2()
    {
        System.out.println("Starting gamemode 2!");

        new GameMode2(graph).play();
    }


    /**
     * starts gamemode 3
     */
    private static void startGamemode3()
    {
        System.out.println("Starting gamemode 3!");

        GameMode3 gameMode3 = new GameMode3(graph);
        gameMode3.play();
    }


}
