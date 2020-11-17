package visuals.game;

import graph.Graph;
import javafx.scene.control.Button;

public class GameMode2 extends GraphView{

    public GameMode2(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        super.graph = graph;

        System.out.println("running gamemode3!");
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


    public void setButtonAction()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i < graph.getN(); i++)
        {
            //copies button i to a new button b
            Button b = buttonList[i];

            //sets the action of the button
            b.setOnAction(actionEvent ->
            {
                buttonAction(b);
                System.out.println("this button click event is being overwritten by gamemode 2");
            });

            //returns the copied button with the added actionEvent to the button list
            buttonList[i] = b;
        }
    }
}
