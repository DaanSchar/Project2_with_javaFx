package visuals.game;

import graph.Graph;
import javafx.scene.control.Button;

public class GameMode3 extends GraphView{

    private Graph graph;

    public GameMode3(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;

        System.out.println("running gamemode3!");
    }

}
