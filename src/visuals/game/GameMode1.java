/**
 * class that implements gamemode1
 * @author: Leo
 */


package visuals.game;


import graph.Graph;
import javafx.stage.Stage;
import visuals.menu.EndMenu;
import visuals.menu.GraphicalMenu;


public class GameMode1 extends GraphView{

    private long startTime;
    private long playTime;
    private Graph graph;
    private Stage stage;
    //public ScoreData score = new ScoreData();

    public GameMode1(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        stage = GraphicalMenu.stage;
        start();
    }

    /**
     * starts the game
     */
    public void start()
    {
        startTime = System.currentTimeMillis();
        System.out.println("running gamemode 1!");
    }

    /**
     * ends game when chromNum is reached
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
