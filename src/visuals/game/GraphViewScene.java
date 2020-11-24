package visuals.game;


import Main.Main;
import graph.Graph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
//import visuals.menu.EndMenu;
import visuals.menu.GraphicalMenu;

public class GraphViewScene extends Application {

    private Scene graphViewScene;
    private Graph graph;
    private static GraphicalMenu.MenuChoices menuChoices;
    private Group root;

    public GraphViewScene(GraphicalMenu.MenuChoices menuChoices)
    {
        this.menuChoices = menuChoices;
        this.graph = menuChoices.getGraph();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Translate translate = new Translate();
        translate.setX(100);

        GraphView graphView;

        // determines which gamemode class will be run
        if(menuChoices.getGameMode() == 1)
        {
            graphView = new GameMode1(graph, Main.windowSizeX,Main.windowSizeY);
            root = graphView.getGroup();
        }
        if(menuChoices.getGameMode() == 2)
        {
            graphView = new GameMode2(graph, Main.windowSizeX,Main.windowSizeY);
            root = graphView.getGroup();
        }
        if(menuChoices.getGameMode() == 3)
        {
            graphView = new GameMode3(graph, Main.windowSizeX,Main.windowSizeY);
            root = graphView.getGroup();
        }


        System.out.println("gamemode = " + menuChoices.getGameMode());


        Pane layout = new Pane();
        layout.getChildren().addAll(root);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        graphViewScene = new Scene(layout,Main.windowSizeX, Main.windowSizeY);

        /*//  transition to the next scene by making a endMenu object, inserting
        //  the stage of this class inside the start method of endMenu and setting the scene of this
        //  class' stage to the scene made in endMenu
        EndMenu endMenu = new EndMenu();
        try {
            endMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(endMenu.getEndMenuScene());*/

    }
    public static GraphicalMenu.MenuChoices setMenuChoices()
    {
        return menuChoices;
    }




    public Scene getGraphViewScene()
    {
        return graphViewScene;
    }

}
