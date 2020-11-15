package chromatics.branching;

/**
 * This class calculates the chromatic number of 1 attempt of coloring the graph
 *
 * @author Daan
 */

public class ChromaticNum
{

    private int[] colorArray;
    private int max;

    public ChromaticNum(int[] finalColorArray)
    {
        colorArray = finalColorArray;
        CalcChromNum();
    }

    /**
     * calculates the chromatic number
     */
    private void CalcChromNum()
    {
        max = 0;

        for(int i = 0; i < colorArray.length; i++)
        {
            if(colorArray[i] > max)
            {
                max = colorArray[i];
            }
        }
    }

    /**
     * returns the chromatic number
     * @return int max
     */
    public int getChromNum()
    {
        return max;
    }

}
