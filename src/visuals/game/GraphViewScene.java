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
import visuals.menu.GraphicalMenu;

public class GraphViewScene extends Application {

    private Scene graphViewScene;
    private Graph graph;
    private GraphicalMenu.MenuChoices menuChoices;
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

        GraphView graphView = new GraphView();
        graphView.startGraphView(graph, Main.windowSizeX,Main.windowSizeY);
        root = graphView.getGroup();

        Pane layout = new Pane();
        layout.getChildren().addAll(root);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        graphViewScene = new Scene(layout,Main.windowSizeX, Main.windowSizeY);
    }

    public Scene getGraphViewScene()
    {
        return graphViewScene;
    }

}
