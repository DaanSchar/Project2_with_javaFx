package visuals.game;


import graph.Graph;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import visuals.menu.EndMenu;


public class GameMode1 extends GraphView{

    private long startTime;
    private long playTime;
    private int chromNum;
    private Label resultLabel1 = new Label();
    private Label resultLabel2 = new Label();
    private Label resultLabel3 = new Label();
    private Graph graph;

    public GameMode1(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        startTime = System.currentTimeMillis();
        System.out.println("running gamemode 1!");

    }

    /**
     * when chromNum is reached
     */
    @Override
    public void end()
    {
        playTime = (System.currentTimeMillis() - startTime) / 1000;
        //add resultLabel1
        resultLabel1.setText("You found the chromatic number! You are a graph-coloring hero.");
        resultLabel1.setTextFill(Color.BLACK);
        resultLabel2.setText("The chromatic number for this graph is " + graph.getChromNum());
        resultLabel2.setTextFill(Color.BLACK);
        resultLabel3.setText("It took you: " + playTime + " seconds to complete");
        resultLabel3.setTextFill(Color.BLACK);

        //add resBox containing resultLabels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2, resultLabel3);
        resBox.setLayoutX(15);
        resBox.setLayoutY(550);
        root.getChildren().add(resBox);

        Button b = new Button();
        b.setText("Try again");

    }
}
