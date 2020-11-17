package Main;

import graph.ColEdge;
import graph.Graph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import menu.Menu;
import visuals.game.GraphView;
import visuals.menu.GraphicalMenu;

import java.io.IOException;

public class Main extends Application
{

    private Scene graphViewScene;
    public static Graph graph;
    public static ColEdge[] e;
    public static int n;
    public static int m;
    static final int r = 15;
    public static final int windowSizeX = 1000;
    public static final int windowSizeY = 800;
    public static Group root;


    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //-----MENU------
        GraphicalMenu menu = new GraphicalMenu();
        menu.start(stage);
        Scene menuScene = menu.getMenuScene();
        stage.setScene(menuScene);

        stage.setTitle("title text");
        stage.show();

        GraphicalMenu.MenuChoices cc = GraphicalMenu.getMenuChoice();


        Translate translate = new Translate();
        translate.setX(100);

        GraphView graphView = new GraphView();
        graphView.startGraphView(graph,windowSizeX,windowSizeY);
        root = graphView.getGroup();

        Pane layout = new Pane();
        layout.getChildren().addAll(root);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        graphViewScene = new Scene(layout,windowSizeX, windowSizeY);



    }
}
