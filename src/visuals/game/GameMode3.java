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
import java.util.Arrays;

public class GameMode3 extends GraphView{

    private Graph graph;
    private RandomPath randomPath;
    private int[] newPath;
    private int vertex;
    private int move;
    private long startTime;
    private long playTime;
    private Label resultLabel3 = new Label();
    private int totalVertices;


    public GameMode3(Graph graph, int windowSizeX, int windowSizeY) {
        super(graph, windowSizeX, windowSizeY);
        this.graph = graph;
        move = move;
        vertex = vertex;
        start();
        System.out.println(graph.n);
    }

    public void start()
    {
        RandomPath randomPath = new RandomPath(graph.n); //could be a problem -- check!!
        randomPath.makeRandomPath();
        newPath = randomPath.getRandomPath();
        startTime = System.currentTimeMillis();
        showPath();
        System.out.println("running gamemode 3!");
        System.out.println(move);
        System.out.println(Arrays.toString(newPath));
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
                checkIfNeedWarning();
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
     * asks player to confirm color choice
     */

    public void confirmButton(Button b)
    {
        Button confirm = new Button();
        root.getChildren().add(confirm);
        confirm.setText("Confirm?");
        //set position close to vertex being colored
        confirm.setOnAction(actionEvent ->
        {
            confirm(b);
        });
    }

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
     * @Override setHoverEvent
     */
    @Override
    public void setHoverEvent()
    {
        System.out.println("setHoverEvent has been overwritten by GameMode3");
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
        //connectedVertices = vertex.getConnVerArray(textForButtonHover);

        //circles = new Circle[connectedVertices.length];
        //circles2 = new Circle[connectedVertices.length];
        //initCircles();

        currentVertex = new Text();
        currentVertex.setText(b.getText());
        currentVertex.setY(55);
        currentVertex.setX(95);
        currentVertex.setFill(Color.WHITE);
        root.getChildren().add(currentVertex);

        /*for(int j = 0; j < connectedVertices.length; j++)
        {
            //scales the buttons adjacent to the vertex by 1.25
            buttonList[connectedVertices[j]-1].setScaleX(buttonScaler * 1.1);
            buttonList[connectedVertices[j]-1].setScaleY(buttonScaler * 1.1);

            Coordinate cord = new Coordinate();
            cord.x = (int)buttonList[connectedVertices[j]-1].getLayoutX();
            cord.y = (int)buttonList[connectedVertices[j]-1].getLayoutY();

            //draws a shape on each adjacent vertex
            circles[j] = new Circle(cord.x, cord.y, 5);
            circles[j].setFill(Color.WHITE);
            root.getChildren().add(circles[j]);

            //makes a a border around the circle(by making a smaller circle inside the existing circle)
            circles2[j] = new Circle(cord.x, cord.y, 4);
            circles2[j].setFill(Color.RED);
            root.getChildren().add(circles2[j]);
        }*/
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

        /*//sets the size of all connected buttons back to the default size
        for(int j = 0; j < connectedVertices.length; j++)
        {
            buttonList[connectedVertices[j]-1].setScaleX(buttonScaler);
            buttonList[connectedVertices[j]-1].setScaleY(buttonScaler);

            //removes the shapes drawn on the adjacent vertices from root
            root.getChildren().remove(circles[j]);
            root.getChildren().remove(circles2[j]);
        }*/
    }

    /**
     * when all vertices in path are colored
     */
    @Override
    public void end()
    {
        playTime = (System.currentTimeMillis() - startTime) / 1000;
        //add resultLabel1

        if(colors.numberOfColors() == graph.getChromNum())
        {
            resultLabel1.setText("You found the chromatic number! You are a graph-coloring hero.");
            resultLabel1.setTextFill(Color.BLACK);
            resultLabel2.setText("The chromatic number for this graph is " + graph.getChromNum() + "You used " + colors.numberOfColors() + " colors.");
            resultLabel2.setTextFill(Color.BLACK);
            resultLabel3.setText("It took you: " + playTime + " seconds to complete");
            resultLabel3.setTextFill(Color.BLACK);
        }
        else if(colors.numberOfColors() != graph.getChromNum())
        {
            resultLabel1.setText("Game Over.");
            resultLabel1.setTextFill(Color.BLACK);
            resultLabel2.setText("You used " + colors.numberOfColors() + " colors. The chromatic number for this graph is " + graph.getChromNum());
            resultLabel2.setTextFill(Color.BLACK);
            resultLabel3.setText("It took you: " + playTime + " seconds to complete");
            resultLabel3.setTextFill(Color.BLACK);
        }


        //add resBox containing resultLabels
        VBox resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.getChildren().addAll(resultLabel1,resultLabel2, resultLabel3);
        resBox.setLayoutX(15);
        resBox.setLayoutY(550);
        root.getChildren().add(resBox);
    }


}
