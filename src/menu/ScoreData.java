/**
 * data storage object to store highscores from all gamemodes
 * @author Leo
 */

package menu;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScoreData {

    private static FileReader fileReader;
    private static Scanner in;
    private static FileWriter fileWriter;
    private static PrintWriter out;

    private static int highScore1;
    private static int highScore2;
    private static int highScore3;

    public ScoreData()
    {

    }

    /**
     * adds a new score and when it is a new highScore prints it to the file
     * @param gamemode the gamemode for which the score counts
     * @param score the score that was achieved
     */
    public void add(int gamemode, int score) {
        if (gamemode == 1) {
            try {
                fileReader = new FileReader("scores1.txt");
                in = new Scanner(fileReader);
            } catch (Throwable e) {
                System.out.println("error");
            }

            highScore1 = Integer.parseInt(in.nextLine());
            System.out.println("highscore: " + highScore1);
            in.close();

            if (highScore1 < score) {
                highScore1 = score;
                System.out.println("new highscore: " + highScore1);
                try {
                    fileWriter = new FileWriter("scores1.txt", false);
                    out = new PrintWriter(fileWriter);
                } catch (Throwable e) {
                    System.out.println("error");
                }

                out.print(highScore1);
                out.close();
                System.out.println("score added: " + highScore1);

            }

        }
        else if(gamemode == 2)
        {
            try {
                fileReader = new FileReader("scores2.txt");
                in = new Scanner(fileReader);
            } catch (Throwable e) {
                System.out.println("error");
            }

            highScore2 = Integer.parseInt(in.nextLine());
            System.out.println("highscore: " + highScore2);
            in.close();

            if (highScore2 < score) {
                highScore2 = score;
                System.out.println("new highscore: " + highScore2);
                try {
                    fileWriter = new FileWriter("scores2.txt", false);
                    out = new PrintWriter(fileWriter);
                } catch (Throwable e) {
                    System.out.println("error");
                }

                out.print(highScore2);
                out.close();
                System.out.println("score added: " + highScore2);

            }
        }
        else if(gamemode == 3)
        {
            try {
                fileReader = new FileReader("scores3.txt");
                in = new Scanner(fileReader);
            } catch (Throwable e) {
                System.out.println("error");
            }

            highScore3 = Integer.parseInt(in.nextLine());
            System.out.println("highscore: " + highScore3);
            in.close();

            if (highScore3 < score) {
                highScore3 = score;
                System.out.println("new highscore: " + highScore3);
                try {
                    fileWriter = new FileWriter("scores3.txt", false);
                    out = new PrintWriter(fileWriter);
                } catch (Throwable e) {
                    System.out.println("error");
                }

                out.print(highScore3);
                out.close();
                System.out.println("score added: " + highScore3);

            }
        }
    }

    public static void remove(double amount)
    {



    }

    public static int getHighScore1()
    {
        return highScore1;
    }

    public static int getHighScore2()
    {
        return highScore2;
    }

    public static int getHighScore3()
    {
        return highScore3;
    }
}



