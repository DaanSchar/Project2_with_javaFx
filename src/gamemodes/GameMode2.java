package gamemodes;

import graph.Graph;

import java.util.TimerTask;

public class GameMode2 extends TimerTask {
    private Graph graph;
    private boolean keepPlaying;

    public void run()
    {
        System.out.println("This is the end of the game. \nYou used " + graph.colors.numberOfColors() + " colors.");
    }

    public GameMode2(Graph graphObject)
    {
        graph = graphObject;
        keepPlaying = true;
    }

    public void play()
    {
        while(keepPlaying)
        {
            playerMove();
            graph.colors.printColorArray();
        }
    }

    public void playerMove()
    {
        PickVertexColor vertex1 = new PickVertexColor(graph);
        vertex1.pickVertex();
        vertex1.pickColor();
    }
}



