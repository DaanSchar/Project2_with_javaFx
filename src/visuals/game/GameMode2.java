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
import visuals.menu.EndMenu;
import visuals.menu.GraphicalMenu;
//import visuals.menu.EndMenu;

import java.awt.event.MouseEvent;

public class GameMode2 extends GraphView {

    private static final int TIME = GraphicalMenu.getMenuChoice().getTime();
    private Timeline countdown;
    private Label countdownLabel = new Label();
    private Label textLabel = new Label();
    //enables binding of timeSeconds value to timerLabel text
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(TIME);
    private long startTime;
    private long playTime;
    private Graph graph;
    private Stage stage;
    private Results results;

    public GameMode2(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        stage = GraphicalMenu.stage;
        start();
    }

    public void start()
    {
        setTimer();
        startTime = System.currentTimeMillis();
        System.out.println("running gamemode 2!");
    }

    /**
     * sets up the timer
     */

    public void setTimer() {
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
                try {
                    timeOut();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * when time is over before chromNum is reached
     * returns: number of colors used
     *
     */

    public void timeOut() throws Exception {

        playTime = (System.currentTimeMillis() - startTime) / 1000;

        Results results = new Results(2, colors.numberOfColors(), graph.getChromNum(), playTime);

        EndMenu endMenu = new EndMenu(stage, graph, results);
        stage.setScene(endMenu.getEndMenuScene());

    }

    /**
     * when chromNum is reached before time is out
     * returns: time needed to complete
     */

    @Override
    public void end() {

        playTime = (System.currentTimeMillis() - startTime) / 1000;

        Results results = new Results(2, colors.numberOfColors(), graph.getChromNum(), playTime);

        EndMenu endMenu = new EndMenu(stage, graph, results);
        stage.setScene(endMenu.getEndMenuScene());
    }
}

