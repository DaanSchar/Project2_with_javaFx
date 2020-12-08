package Main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import visuals.menu.GraphicalMenu;


public class Main extends Application
{

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

        GraphicalMenu menu = new GraphicalMenu();
        menu.start(stage);
        Scene menuScene = menu.getMenuScene();
        stage.setScene(menuScene);

        stage.setTitle("Graph Game");
        stage.show();
    }
}
