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
    private Graph graph;
    private Stage stage;
    private Results results;
    private GraphicalMenu.MenuChoices MENU_CHOICES;

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

        //add resultLabel1

        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel3 = new Label();
        resultLabel4 = new Label();
        Button tryAgain = new Button();
        Button mainMenu = new Button();
        Button exit = new Button();

        resultLabel1.setText("Game Over");
        resultLabel1.setTextFill(Color.WHITE);

        resultLabel2.setText("You used " + results.getNumberOfColors() + " Colors");
        resultLabel2.setTextFill(Color.WHITE);

        resultLabel3.setText("The Chromatic Number is " + results.getChromNum());
        resultLabel3.setTextFill(Color.WHITE);

        resultLabel4.setText("You took  " + results.getPlayTime() + " Seconds");
        resultLabel4.setTextFill(Color.WHITE);

        //Buttons in End Menu

        tryAgain.setText("Try Again");
        tryAgain.setTextFill(Color.BLACK);
        tryAgain.setOnAction(e -> rerunProgram());
        mainMenu.setText("Back to Main Menu");
        mainMenu.setTextFill(Color.BLACK);
        mainMenu.setOnAction(e -> goToMainMenu());
        exit.setText("Exit Game");
        exit.setTextFill(Color.BLACK);
        exit.setOnAction(e -> Platform.exit());

        //add resBox containing resultLabels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2,resultLabel3,resultLabel4,tryAgain,mainMenu,exit);
        resBox.setLayoutX(15);
        resBox.setLayoutY(35);

        Pane layout = new Pane();
        layout.getChildren().add(resBox);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        endMenuScene = new Scene(layout, Main.endgamewindowSizeX, Main.endgamewindowSizeY);

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
