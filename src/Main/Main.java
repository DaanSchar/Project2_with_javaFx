package Main;

import graph.ColEdge;
import graph.Graph;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menu.ScoreData;
import visuals.menu.GraphicalMenu;


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
    public static final int endgamewindowSizeX = 400;
    public static final int endgamewindowSizeY = 350;
    public static Group root;


    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ScoreData scores = new ScoreData();
        GraphicalMenu menu = new GraphicalMenu();
        menu.start(stage);
        Scene menuScene = menu.getMenuScene();
        stage.setScene(menuScene);

        stage.setTitle("Graph Game");
        stage.show();
    }
}
