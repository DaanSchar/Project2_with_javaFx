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
        checkForHints();
        addScore();

    }

    /**
     * calclulates score that was achieved depending on gamemode
     * relevant parameters playTime, diff
     * gamemode1: score = 1/playtime * graph.m * 10000
     * gamemode2: score = 1/diff^2 * 1000 (1000 bonus if chromNum is reached, /2 if illegal colorings)
     * gamemode3: same as gamemode2
     */
    public static void makeScore()
    {
        double aScore;

        if(gamemode == 1)
        {
            double res = Math.pow(playTime,2);
            aScore = ((1/res) * graph.m) * 100000;
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
                double res = Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000)/2;
                score = (int) aScore;
            }
            else if(diff>0)
            {
                double res = Math.pow(diff, 2);
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
                double res = Math.pow(diff, 2);
                aScore = (((1/res) * graph.m) * 1000)/2;
                score = (int) aScore;
            }
            else if(diff>0)
            {
                double res = Math.pow(diff, 2);
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

    /**
     * checks which hints have been used and subtracts penalty points from the score
     */
    public static void checkForHints()
    {
        double aScore;

        if(HintsUsed.hoverHintUsed == true && HintsUsed.lineHintUsed == true)
        {
              System.out.println("hover hint used & line hint used");
              aScore = (score - (score * 0.2));
              score = (int) aScore;
        }
        else if(HintsUsed.hoverHintUsed == true && HintsUsed.largestHintUsed)
        {
             System.out.println("hover hint used & largest hint used");
             aScore = (score - (score * 0.3));
             score = (int) aScore;
        }
        else if(HintsUsed.lineHintUsed == true && HintsUsed.largestHintUsed)
        {
            System.out.println("line hint used & largest hint used");
            aScore = (score - (score * 0.3));
            score = (int) aScore;
        }
        else if(HintsUsed.hoverHintUsed == true && HintsUsed.smallestHintUsed == true)
        {
            System.out.println("hover hint used & smallest hint used");
            aScore = (score - (score * 0.4));
            score = (int) aScore;
        }
        else if(HintsUsed.lineHintUsed == true && HintsUsed.smallestHintUsed == true)
        {
             System.out.println("line hint used & smallest hint used");
             aScore = (score - (score * 0.4));
             score = (int) aScore;
        }
        else if(HintsUsed.smallestHintUsed && HintsUsed.largestHintUsed)
        {
            System.out.println("smallest hint used & largest hint used");
            aScore = (score - (score * 0.5));
            score = (int) aScore;
        }
        else if(HintsUsed.hoverHintUsed == true)
        {
            System.out.println("hover hint used");
            aScore = (score - (score * 0.1));
            score = (int) aScore;
        }
        else if(HintsUsed.lineHintUsed == true)
        {
            System.out.println("line hint used");
            aScore = score - (score * 0.1);
            score = (int) aScore;
        }
        else if(HintsUsed.largestHintUsed)
        {
            System.out.println("largest hint used");
            aScore = score - (score * 0.2);
            score = (int) aScore;
        }
        else if(HintsUsed.smallestHintUsed)
        {
            System.out.println("smallest hint used");
            aScore = score - (score * 0.3);
            score = (int) aScore;
        }
        else
        {
            score = score;
        }
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
