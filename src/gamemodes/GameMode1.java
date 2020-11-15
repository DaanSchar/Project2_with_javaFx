package gamemodes;

import chromatics.branching.Branching;
import graph.Graph;

public class GameMode1
{

    private Graph graph;
    private Graph colorGraph;
    private int computations;
    private boolean keepPlaying;

    public GameMode1(Graph graphObject)
    {
        graph = graphObject;
        keepPlaying = true;
        computations = 250;
    }

    public void play()
    {
        Branching branching = new Branching(graph, computations);
        long startTime = System.currentTimeMillis();
        while(branching.getBranchingNum() != countColors())
        {
            playerMove();
            graph.colors.printColorArray();
        }
        long totalTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("You finished coloring the graph!");
        System.out.println("It took you: " + totalTime + " second to complete");
    }

    public void playerMove()
    {
        PickVertexColor vertex1 = new PickVertexColor(graph);
        vertex1.pickVertex();
        vertex1.pickColor();
    }

    public int countColors() {
        int result = 0;
        for (int i = 0; i < graph.colors.getColorArray().length; i++) {
            int j = 0;
            for (j = 0; j < i; j++) {
                if (graph.colors.getColorArray()[i] == graph.colors.getColorArray()[j]) {
                    break;
                }
            }

            if (i == j) {
                result++;
            }
        }
        return result;
    }

}
