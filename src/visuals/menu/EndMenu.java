package visuals.menu;

import Main.Main;
import graph.Graph;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ScoreData;
import visuals.game.*;

import java.awt.event.ActionEvent;

/**
 * displays results and asks user for the next steps
 *
 */

public class EndMenu extends Application {

    private Scene endMenuScene;
    private Label resultLabel1;
    private Label resultLabel2;
    private Label resultLabel3;
    private Label resultLabel4;
    private Label resultLabel5;
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

        //make labels
        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel3 = new Label();
        resultLabel4 = new Label();
        resultLabel5 = new Label();

        //set text
        resultLabel1.setText("Game Over");
        resultLabel1.setTextFill(Color.WHITE);

        resultLabel2.setText("You used " + results.getNumberOfColors() + " colors");
        resultLabel2.setTextFill(Color.WHITE);

        resultLabel3.setText("The chromatic number for this graph is " + results.getChromNum());
        resultLabel3.setTextFill(Color.WHITE);

        resultLabel4.setText("It took you  " + results.getPlayTime() + " seconds");
        resultLabel4.setTextFill(Color.WHITE);

        if(results.getGamemode() == 1)
        {
            resultLabel5.setText("Your highscore is " + scores.getHighScore1());
        }
        else if(results.getGamemode() == 2)
        {
            resultLabel5.setText("Your highscore is 2");
        }
        else if(results.getGamemode() == 3)
        {
            resultLabel5.setText("Your highscore is " + scores.getHighScore3());
        }
        resultLabel5.setTextFill(Color.WHITE);

        //make VBox containing labels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4, resultLabel5);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);

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

    private VBox results1()
    {
        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel3 = new Label();
        resultLabel4 = new Label();

        resultLabel1.setText("Game Over");
        resultLabel1.setTextFill(Color.WHITE);

        resultLabel2.setText("You used " + results.getNumberOfColors() + " colors");
        resultLabel2.setTextFill(Color.WHITE);

        resultLabel3.setText("The chromatic number for this graph is " + results.getChromNum());
        resultLabel3.setTextFill(Color.WHITE);

        resultLabel4.setText("It took you  " + results.getPlayTime() + " seconds");
        resultLabel4.setTextFill(Color.WHITE);

        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);

        return resBox;
    }

    private VBox results2()
    {
        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel3 = new Label();
        resultLabel4 = new Label();

        resultLabel1.setText("Game Over");
        resultLabel1.setTextFill(Color.WHITE);

        resultLabel2.setText("You used " + results.getNumberOfColors() + " colors");
        resultLabel2.setTextFill(Color.WHITE);

        resultLabel3.setText("The chromatic number for this graph is " + results.getChromNum());
        resultLabel3.setTextFill(Color.WHITE);

        resultLabel4.setText("It took you  " + results.getPlayTime() + " seconds");
        resultLabel4.setTextFill(Color.WHITE);

        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);

        return resBox;
    }

    private VBox results3()
    {
        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel3 = new Label();
        resultLabel4 = new Label();

        resultLabel1.setText("Game Over");
        resultLabel1.setTextFill(Color.WHITE);

        resultLabel2.setText("You used " + results.getNumberOfColors() + " colors");
        resultLabel2.setTextFill(Color.WHITE);

        resultLabel3.setText("The chromatic number for this graph is " + results.getChromNum());
        resultLabel3.setTextFill(Color.WHITE);

        resultLabel4.setText("It took you  " + results.getPlayTime() + " seconds");
        resultLabel4.setTextFill(Color.WHITE);

        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);

        return resBox;
    }

    private void exitProgram()
    {
        System.out.println("Goodbye!");
        //  endMenuScene.close();
    }
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

    private void rerunProgram()
    {
        System.out.println("Rerunning");
        graph.resetColor();
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
