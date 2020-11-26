package visuals.game;

public class Results
{
    int numberOfColors;
    int chromNum;
    long playTime;

    public Results(int numberOfColors, int chromNum, long playTime)
    {
        this.numberOfColors = numberOfColors;
        this.chromNum = chromNum;
        this.playTime = playTime;
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

}
