package visuals.game;

import graph.Graph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import visuals.menu.GraphicalMenu;
//import visuals.menu.EndMenu;

import java.awt.event.MouseEvent;

public class GameMode2 extends GraphView{

    private static final int TIME = GraphicalMenu.getMenuChoice().getTime(); //import that from menu-choice
    private Timeline countdown;
    private Label countdownLabel = new Label();
    private Label textLabel = new Label();
    private Label resultLabel1 = new Label();
    private Label resultLabel2 = new Label();
    //enables binding of timeSeconds value to timerLabel text
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(TIME);
    private Stage stage;

    public GameMode2(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        super.graph = graph;
        setTimer();
        System.out.println("running gamemode 2!");
    }

    /**
     * sets up the timer
     *
     */

    public void setTimer()
    {
        //add label displaying countdown
        countdownLabel.textProperty().bind(timeSeconds.asString());
        countdownLabel.setTextFill(Color.BLACK);

        //add textLabel
        textLabel.setText("COUNTDOWN");
        textLabel.setTextFill(Color.BLACK);

        //add vBox containing countdownLabel and textLabel
        VBox timeBox = new VBox(20);
        timeBox.setAlignment(Pos.BASELINE_CENTER);
        timeBox.setPrefWidth(100);
        timeBox.getChildren().addAll(countdownLabel, textLabel);
        timeBox.setLayoutX(15);
        timeBox.setLayoutY(650);

        root.getChildren().add(timeBox);

        //make timeline to countdown
        timeSeconds.set(TIME);
        countdown = new Timeline();
        countdown.getKeyFrames().add(new KeyFrame(Duration.seconds(TIME + 1), new KeyValue(timeSeconds, 0)));
        countdown.playFromStart();

        //determines end of game (when countdown stops)
        countdown.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                countdown.stop();
                end();
            }
        });
    }

    public void end()
    {
        //add resultLabel1
        resultLabel1.setText("This is the end of the game.");
        resultLabel1.setTextFill(Color.BLACK);
        resultLabel2.setText("You used " + graph.colors.numberOfColors() + " colors.");
        resultLabel2.setTextFill(Color.BLACK);

        //add resBox containing resultLabels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2);
        resBox.setLayoutX(15);
        resBox.setLayoutY(350);

        root.getChildren().add(resBox);

        System.out.println("This is the end of the game");
        //there appears to be a problem here: graph.colors.numberOfColors returns 1 even if no color
        //was selected
        System.out.println("You used " + graph.colors.numberOfColors() + " colors.");
    }

    protected void setHoverEvent()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i < graph.getN(); i++)
        {
            Button b = buttonList[i];

            //  action taken when entering the button
            b.setOnMouseEntered(e ->
            {
                hoverEventEntered(b);
                System.out.println("this hover enter event has been overwritten by gamemode 2");
            });

            //  action taken when exiting the button
            b.setOnMouseExited(e ->
            {
                hoverEventExited(b);
                System.out.println("this hover exit event has been overwritten by gamemode 2");
            });
        }
    }
}
