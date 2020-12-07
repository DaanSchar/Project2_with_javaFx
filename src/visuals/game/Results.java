/**
 * class that takes in relevant parameters from the gamemodes and graph classes
 * and calculates score
 * @author Leo
 */

package visuals.game;

import graph.Graph;
import menu.ScoreData;

public class Results
{
    private static int gamemode;
    private static int numberOfColors;
    private static int chromNum;
    private static double playTime;
    private static int score;
    private static Graph graph;
    private static int diff;
    public static ScoreData scores;


    public Results(int gamemode, int numberOfColors, int chromNum, long playTime, Graph graph)
    {
        scores = new ScoreData();
        this.gamemode = gamemode;
        this.numberOfColors = numberOfColors;
        this.chromNum = chromNum;
        this.playTime = (double) playTime;
        this.graph = graph;

        makeScore();
        addScore();

    }

    /**
     * calclulates score that was achieved depending on gamemode
     * relevant parameters playTime, diff
     * gamemode1: score = 1/playtime * graph.m * 1000
     * gamemode2: score = 1/diff^2 * 1000 (1000 bonus if chromNum is reached, 1500 penalty if illegal coloring included)
     * gamemode3: same as gamemode2
     */
    public static void makeScore()
    {
        double aScore;

        if(gamemode == 1)
        {
            aScore = ((1/playTime) * graph.m) * 3000;
            score = (int) aScore;
        }
        else if(gamemode == 2)
        {
            diff = (numberOfColors - chromNum);

            if(diff == 0)
            {
                aScore = ((1 * graph.m) * 1000 + 1000);
                score = (int) aScore;
            }
            else if(diff < 0)
            {
                int res = (int) Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000 - 2000);
                score = (int) aScore;
            }
            else if(diff>0)
            {
                int res = (int) Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000);
                score = (int) aScore;
            }

        }
        else if(gamemode == 3)
        {
            diff = (numberOfColors - chromNum);

            if(diff == 0)
            {
                aScore = ((1 * graph.m) * 1000 + 1000);
                score = (int) aScore;
            }
            else if(diff < 0)
            {
                int res = (int) Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000 - 1500);
                score = (int) aScore;
            }
            else if(diff>0)
            {
                int res = (int) Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000);
                score = (int) aScore;
            }
        }

    }

    /**
     * adds current score to ScoreData storage
     */
    public void addScore()
    {
        scores.add(gamemode,score);
    }

    public int getNumberOfColors()
    {
        return numberOfColors;
    }

    public int getChromNum()
    {
        return chromNum;
    }

    public int getPlayTime()
    {
        return (int) playTime;
    }

    public int getGamemode()
    {
        return gamemode;
    }

    public int getScore()
    {
        return score;
    }

}
