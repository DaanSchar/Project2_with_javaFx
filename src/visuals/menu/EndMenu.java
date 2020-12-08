/**
 * class that makes menu that appears after the game has ended
 * displays results and highscore and asks the user whether to play again or quit the game
 * @author Felix & Leo
 */


package visuals.menu;

import Main.Main;
import graph.Graph;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ScoreData;
import visuals.game.*;


public class EndMenu extends Application {

    private Scene endMenuScene;
    private Label resultLabel1;
    private Label resultLabel2;
    private Label resultLabel3;
    private Label resultLabel4;
    private Label resultLabel5;
    private Label headLabel;

    private Graph graph;
    private Stage stage;
    private Results results;
    private GraphicalMenu.MenuChoices MENU_CHOICES;

    public ScoreData scores;


    public EndMenu(Stage stage, Graph graph, Results results)
    {
        this.graph = graph;
        this.stage = stage;
        this.results = results;
        MENU_CHOICES = GraphViewScene.setMenuChoices();
        start(stage);

    }

    @Override
    public void start(Stage stage){

        //make buttons
        Button tryAgain = new Button();
        Button mainMenu = new Button();
        Button exit = new Button();

        //set text
        tryAgain.setText("Try Again");
        tryAgain.setTextFill(Color.BLACK);
        tryAgain.setOnAction(e -> rerunProgram());
        mainMenu.setText("Back to Main Menu");
        mainMenu.setTextFill(Color.BLACK);
        mainMenu.setOnAction(e -> goToMainMenu());
        exit.setText("Exit Game");
        exit.setTextFill(Color.BLACK);
        exit.setOnAction(e -> Platform.exit());

        //add VBox containing buttons
        VBox buttonBox = new VBox(20);
        buttonBox.setPrefWidth(500);
        buttonBox.getChildren().addAll(tryAgain,mainMenu,exit);
        buttonBox.setLayoutX(15);
        buttonBox.setLayoutY(35);

        //make resultLabels
        headLabel = new Label();
        headLabel.setTextFill(Color.WHITE);
        headLabel.setText("Game over");
        resultLabel1 = new Label();
        resultLabel1.setTextFill(Color.WHITE);
        resultLabel2 = new Label();
        resultLabel2.setTextFill(Color.WHITE);
        resultLabel3 = new Label();
        resultLabel3.setTextFill(Color.WHITE);
        resultLabel4 = new Label();
        resultLabel4.setTextFill(Color.WHITE);
        resultLabel5 = new Label();
        resultLabel5.setTextFill(Color.WHITE);

        //make resultBox
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4, resultLabel5);

        if(results.getGamemode() == 1)
        {
            results1();
        }
        else if(results.getGamemode() == 2)
        {
            results2();
        }
        else if(results.getGamemode() == 3)
        {
            results3();
        }

        //putting it together
        VBox menuBox = new VBox(20);
        menuBox.setPrefWidth(500);
        menuBox.getChildren().addAll(resBox, buttonBox);
        menuBox.setLayoutX(15);
        menuBox.setLayoutY(35);

        Pane layout = new Pane();
        layout.getChildren().addAll(menuBox);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        endMenuScene = new Scene(layout, Main.endgamewindowSizeX, Main.endgamewindowSizeY);

    }

    /**
     * setting results for gamemode 1
     */
    private void results1()
    {
        resultLabel1.setText("You used " + results.getNumberOfColors() + " colors");

        resultLabel2.setText("The chromatic number for this graph is " + results.getChromNum());

        resultLabel3.setText("It took you  " + results.getPlayTime() + " seconds");

        resultLabel4.setText("Your score: " + results.getScore());

        resultLabel5.setText(("This is your highscore " + scores.getHighScore1()));

    }

    /**
     * setting results for gamemode 2
     */
    private void results2()
    {
        resultLabel1.setText("You used " + results.getNumberOfColors() + " colors");

        resultLabel2.setText("The chromatic number for this graph is " + results.getChromNum());

        resultLabel3.setText("It took you  " + results.getPlayTime() + " seconds");

        resultLabel4.setText("Your score: " + results.getScore());

        resultLabel5.setText(("This is your highscore " + scores.getHighScore2()));

    }

    /**
     * setting results for gamemode 3
     */
    private void results3()
    {
        resultLabel1.setText("You used " + results.getNumberOfColors() + " colors");

        resultLabel2.setText("The chromatic number for this graph is " + results.getChromNum());

        resultLabel3.setText("It took you  " + results.getPlayTime() + " seconds");

        resultLabel4.setText("Your score: " + results.getScore());

        resultLabel5.setText(("This is your highscore: " + scores.getHighScore3()));

    }

    /**
     * starts new graphicalMenu -> user goes back to main menu
     */
    private void goToMainMenu()
    {
        System.out.println("Going to Main Menu!");
        GraphicalMenu menu = new GraphicalMenu();
        try {
            menu.start(stage);
        }catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(menu.getMenuScene());
    }

    /**
     * starts new GraphviewScene with the same parameters -> runs game with the same parameters
     */
    private void rerunProgram()
    {
        System.out.println("Rerunning");
        graph.resetColor();
        HintsUsed.reset();
        GraphViewScene graphViewScene = new GraphViewScene(MENU_CHOICES);
        try {
            graphViewScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(graphViewScene.getGraphViewScene());
    }


    public Scene getEndMenuScene()
    {
        return endMenuScene;
    }

}
