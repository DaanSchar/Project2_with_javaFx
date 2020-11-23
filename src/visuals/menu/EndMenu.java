/*package visuals.menu;

import Main.Main;
import graph.Graph;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * displays results and asks user for the next steps
 *
 */

/*public class EndMenu extends Application {

    private Scene endMenuScene;
    private Label resultLabel1;
    private Label resultLabel2;
    private Graph graph;

    public EndMenu()
    {


    }

    @Override
    public void start(Stage stage) throws Exception {

        //add resultLabel1
        resultLabel1.setText("This is the end of the game.");
        resultLabel1.setTextFill(Color.BLACK);
        resultLabel2.setText("You used " + graph.colors.numberOfColors()  + " colors.");
        resultLabel2.setTextFill(Color.BLACK);

        //add resBox containing resultLabels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2);
        resBox.setLayoutX(15);
        resBox.setLayoutY(350);

        Pane layout = new Pane();
        layout.getChildren().add(resBox);
        layout.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));

        endMenuScene = new Scene(layout, Main.windowSizeX, Main.windowSizeY);

    }

    public Scene getEndMenuScene()
    {
        return endMenuScene;
    }

}*/
