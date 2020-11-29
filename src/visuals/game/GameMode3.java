package visuals.game;

import gamemodes.RandomPath;
import graph.Graph;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import visuals.menu.EndMenu;
import visuals.menu.GraphicalMenu;

import java.util.Arrays;

public class GameMode3 extends GraphView{

    private Graph graph;
    private RandomPath randomPath;
    private int[] newPath;
    private int vertex;
    private int move;
    private long startTime;
    private long playTime;
    private Stage stage;
    private Results results;


    public GameMode3(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        stage = GraphicalMenu.stage;
        start();
    }

    public void start()
    {
        RandomPath randomPath = new RandomPath(graph.n); //could be a problem -- check!!
        randomPath.makeRandomPath();
        newPath = randomPath.getRandomPath();
        startTime = System.currentTimeMillis();
        showPath();
        System.out.println("running gamemode 3!");
    }


    /**
     * gets the next vertex from the random path
     */
    public int getNextVertex(int[] newPath)
    {
        int newVertex = newPath[move] - 1;
        vertex = newVertex;
        return vertex;
    }

    public void showPath()
    {
        Button b = buttonList[getNextVertex(newPath)];
        //highlights next button to be colored
        hoverEventEntered(b);
        playMove(b);
    }

    public void playMove(Button b)
    {
        b.setOnMouseClicked(actionEvent ->
        {
            if(actionEvent.getButton() == MouseButton.PRIMARY)
            {
                System.out.println("this is button" + b.getText());

                //sets text to the text of the button, so text = 1 if button is 1
                textForButtonAction = Integer.parseInt(b.getText());

                //sets the vertex to be colored to text
                pick.setPickedVertex(textForButtonAction);

                //gets the current color of vertex and increases it by 1
                pick.pickColorUp(colors.getColorOfVertex(textForButtonAction));

                colors.printColorArray();

                //sets the color of the button to the associated color in the randColor list;
                b.setStyle("-fx-background-color: " + randColors[colors.getColorOfVertex(textForButtonAction)] + "; ");


                //coloring the line red if 2 vertices have the same color.
                makeWarningList();
                for(int j = 0;j < m; j++)
                {
                    //needWarningList represents if an edge contains 2 vertices that are the same color or not
                    if(needWarningList[j] == true)
                    {
                        lineList[j].setStroke(Color.RED);
                    }   else {
                        colorLine(j);
                    }
                }
                confirmButton(b);
            }

        else if(actionEvent.getButton() == MouseButton.SECONDARY)
        {
            System.out.println("this is button" + b.getText());

            //sets text to the text of the button, so text = 1 if button is 1
            textForButtonAction = Integer.parseInt(b.getText());

            //sets the vertex to be colored to text
            pick.setPickedVertex(textForButtonAction);

            //gets the current color of vertex and increases it by 1
            pick.pickColorDown(colors.getColorOfVertex(textForButtonAction));

            colors.printColorArray();

            //sets the color of the button to the associated color in the randColor list;
            b.setStyle("-fx-background-color: " + randColors[colors.getColorOfVertex(textForButtonAction)] + "; ");


            //coloring the line red if 2 vertices have the same color.
            makeWarningList();
            for(int j = 0;j < m; j++)
            {
                //needWarningList represents if an edge contains 2 vertices that are the same color or not
                if(needWarningList[j] == true)
                {
                    lineList[j].setStroke(Color.RED);
                }   else {
                    colorLine(j);
                }
            }
            confirmButton(b);
        }
    });
    }

    /**
     * makes confirmation button asking player to confirm color choice
     */

    public void confirmButton(Button b)
    {
        Button confirm = new Button();
        confirm.setText("Confirm?");
        //set position close to vertex being colored
        confirm.setTranslateX(15);
        confirm.setTranslateY(450);
        root.getChildren().add(confirm);

        confirm.setOnAction(actionEvent ->
        {
            confirm(b);
        });
    }

    /**
     * sets confirmation when confirmButton is clicked
     * @param b the vertex - button that is being colored
     */
    public void confirm(Button b) {
        if(move < graph.n - 1)
        {
            hoverEventExited(b);
            move++;
            System.out.println("move" + move);
            showPath();
        }
        else
        {
            System.out.println("endmove" + move);
            end();
        }
    }

    /**
     * @Override setHoverEvent from graphView
     */
    @Override
    public void setHoverEvent()
    {
        System.out.println("setHoverEvent has been overwritten by GameMode3");
    }

    @Override
    public void setButtonAction()
    {
        System.out.println("setButtonAction has been overwritten by GameMode3");
    }

    /**
     * shows next button to be colored
     * @Override from graphView
     * @param b button we want to give these event conditions
     */

    @Override
    protected void hoverEventEntered(Button b)
    {
        //increases the size of the button by 2.5
        b.setScaleX(buttonScaler * 2.5);
        b.setScaleY(buttonScaler * 2.5);

        textForButtonHover = Integer.parseInt(b.getText());

        currentVertex = new Text();
        currentVertex.setText(b.getText());
        currentVertex.setY(55);
        currentVertex.setX(95);
        currentVertex.setFill(Color.WHITE);
        root.getChildren().add(currentVertex);
    }

    /**
     * sets what happens after player move
     * @Override from graphView
     * @param b button we want to give these event conditions
     */
    @Override
    protected void hoverEventExited(Button b)
    {
        //sets the size of the button to the default size
        b.setScaleX(buttonScaler);
        b.setScaleY(buttonScaler);

        textForButtonHover = Integer.parseInt(b.getText());

        //removes the text
        root.getChildren().remove(currentVertex);
    }

    /**
     * when all vertices in path are colored
     */
    @Override
    public void end()
    {
        playTime = (System.currentTimeMillis() - startTime) / 1000;

        //make results object
        Results results = new Results(3, colors.numberOfColors(), graph.getChromNum(), playTime);

        //call endMenu
        EndMenu endMenu = new EndMenu(stage, graph, results);
        stage.setScene(endMenu.getEndMenuScene());
    }

}
