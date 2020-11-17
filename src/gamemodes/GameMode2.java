package gamemodes;

import graph.Graph;

public class GameMode2  {
    private Graph graph;
    private boolean keepPlaying;


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



