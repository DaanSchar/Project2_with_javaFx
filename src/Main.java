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

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main extends Application
{


    static Graph graph;
    static ColEdge[] e;
    static int n;
    static int m;
    static final int r = 15;
    static final int windowSizeX = 1000;
    static final int windowSizeY = 800;
    Group root;



    public static void main(String args[]) throws IOException
    {
        //starts the menu
        Menu.startMenu();

        graph = Menu.getGraphObject();
        e = graph.getE();
        n = graph.getN();
        m = graph.getM();

        //  test
        for(int i = 0; i < 4; i++)
        {
            System.out.println("test");
        }

        launch(args);
    }


    @Override
    public void start(Stage Stage) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("visuals/game/test.fxml"));

        Translate translate = new Translate();
        translate.setX(100);

        GraphView graphView = new GraphView();
        graphView.startGraphView(graph,windowSizeX,windowSizeY, r);
        root = graphView.getGroup();

        Pane layout = new Pane();
        layout.getChildren().addAll(parent, root);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(layout,windowSizeX, windowSizeY);
        Stage.setScene(scene);

        Stage.setTitle("title text");
        Stage.show();
    }

}
