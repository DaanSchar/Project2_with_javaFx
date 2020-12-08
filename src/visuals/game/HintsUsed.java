/**
 * class that stores which Hints have been used
 * @author Leo
 */

package visuals.game;

public class HintsUsed {

    public static boolean lineHintUsed;
    public static boolean hoverHintUsed;
    public static boolean smallestHintUsed;
    public static boolean largestHintUsed;


    public static void reset()
    {
        lineHintUsed = false;
        hoverHintUsed = false;
        smallestHintUsed = false;
        largestHintUsed = false;
    }


}