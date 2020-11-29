package visuals.game;


import graph.Graph;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ScoreData;
import visuals.menu.EndMenu;
import visuals.menu.GraphicalMenu;


public class GameMode1 extends GraphView{

    private long startTime;
    private long playTime;
    private int chromNum;
    private Graph graph;
    private Stage stage;
    private Results results;
    //public ScoreData score = new ScoreData();

    public GameMode1(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        stage = GraphicalMenu.stage;
        start();
    }

    public void start()
    {
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

        Results results = new Results(1, colors.numberOfColors(), graph.getChromNum(), playTime);
        //ScoreData.add(results);

        EndMenu endMenu = new EndMenu(stage, graph, results);
        stage.setScene(endMenu.getEndMenuScene());
    }
}
