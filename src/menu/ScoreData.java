package menu;

import visuals.game.Results;

/**
 * data storage object to store scores from all gamemodes
 */
public class ScoreData
{
    private int level;
    private int gamemode;
    private int numberOfColors;
    private int chromNum;
    private int diff;
    private long playTime;
    private static long highScore1;
    private static int count1;
    private static long highScore3;
    private static int count3;


    public ScoreData()
    {


    }

    public static void add(int gamemode, int numberOfColors, int chromNum, long playTime)
    {
        if(gamemode == 1)
        {
            setHighScore1(playTime);
        }
        else if(gamemode == 2)
        {
            setHighScore2();
        }
        else if(gamemode == 3)
        {
            setHighScore3(playTime);
        }
    }

    public static void setHighScore1(long playTime)
    {
        if(count1 == 0 || playTime > highScore1)
        {
            highScore1 = playTime;
        }
        count1++;
        System.out.println("Highscore added!");
    }

    public static void setHighScore2()
    {

    }

    public static void setHighScore3(long playTime)
    {
        if(count3 == 0 || playTime > highScore1)
        {
            highScore3 = playTime;
        }
        count3++;

    }






}
