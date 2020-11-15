package chromatics.branching;

import graph.ColEdge;
import graph.Colors;
import graph.Graph;

import java.util.Arrays;
import java.util.Random;

public class Branching
{

    private static ColEdge[] e;
    private static int n;
    private int[] chromArray;
    private int BranchingNum;

    static boolean DEBUG = false;

    /**
     * calculates the chromatic number calculations time and puts all chromatic numbers inside an array chromArray
     * @param graph the graph we want to apply the chromatics.branching algorithm on
     * @param calculations
     *
     * @author Daan
     */
    public Branching(Graph graph, int calculations)
    {

        e = graph.e;
        n = graph.n;

        int calc = calculations;
        int[] vertexPath = new int[n];
        chromArray = new int[calc];


        //loop to create template vertexpath, so {1,2,3,...,n}
        for(int i = 0; i < n; i++)
        {
            vertexPath[i] = i+1;
        }


        //ReCalculates the chromatic number with a different random path x times and gets the smallest number from this
        for(int i = 0; i < calc; i++)
        {
            randomize(vertexPath, vertexPath.length);

            Coloring coloring = new Coloring(graph, vertexPath);
            Colors colors = coloring.getColorOfColoringObject();

            ChromaticNum chromatic = new ChromaticNum(colors.getColorArray());
            if(DEBUG) System.out.println("the Chromatic number = " + chromatic.getChromNum());
            chromArray[i] = chromatic.getChromNum();
        }

        if(DEBUG) System.out.println(Arrays.toString(chromArray));
        if(DEBUG) System.out.println("The Chromatic number: " + getBranchingNum());
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


    /**
     * calculates the smallest chromatic number from all calculated chromatic numbers
     */
    private void BranchingChromNum()
    {

        int min = n*n;

        for(int i = 0; i < chromArray.length; i++)
        {
            if(chromArray[i] < min)
            {
                min = chromArray[i];
            }
        }
        BranchingNum = min;
    }


    /**
     * returns the smallest chromatic number calculated
     * @return int BranchingNum
     */
    public int getBranchingNum()
    {
        BranchingChromNum();
        return BranchingNum;
    }

}
