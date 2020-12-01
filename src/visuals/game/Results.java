package visuals.game;

//import menu.ScoreData;

import menu.ScoreData;

public class Results
{
    private int gamemode;
    private int numberOfColors;
    private int chromNum;
    private long playTime;
    //private int level;
    public ScoreData scores;


    public Results(int gamemode, int numberOfColors, int chromNum, long playTime)
    {
        this.gamemode = gamemode;
        this.numberOfColors = numberOfColors;
        this.chromNum = chromNum;
        this.playTime = playTime;
        //this.level = getLevel();
        ScoreData.add(gamemode, numberOfColors, chromNum, playTime);

    }

    public int getNumberOfColors()
    {
        return numberOfColors;
    }

    public int getChromNum()
    {
        return chromNum;
    }

    public long getPlayTime()
    {
        return playTime;
    }

    /*public int getLevel()
    {

    }*/

    public int getGamemode()
    {
        return gamemode;
    }

}
