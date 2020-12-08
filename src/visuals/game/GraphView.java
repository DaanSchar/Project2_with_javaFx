/**
 * class that draws the graph and makes it interactable
 *
 * @author Daan & Leo
 */

package visuals.game;

import gamemodes.PickVertexColor;
import graph.ColEdge;
import graph.Colors;
import graph.Graph;
import graph.Vertex;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;


public class GraphView
{

    private ColEdge[] e;
    protected Graph graph;
    protected int n;
    protected int m;

    protected Label hintLabel1;
    protected Label hintLabel2;
    protected Label resultLabel1;
    protected Label resultLabel2;

    protected Button lineHintButton;
    protected Button hoverHintButton;
    protected Button quickHintButton;

    protected boolean lineHintPressed;
    protected boolean hoverHintPressed;
    protected boolean quickHintPressed;

    public Group root;
    protected PickVertexColor pick;
    private int windowSizeX;
    private int windowSizeY;
    protected Colors colors;
    protected int textForButtonAction;
    protected int textForButtonHover;
    private Coordinate[] positions;
    private Grid grid;

    protected int[] connectedVertices;
    protected String[] lineColorList;
    protected Line[] lineList;
    protected int lineCount;
    protected double lineScaler;
    protected Boolean[] needWarningList;

    protected Circle[] circles;
    protected Circle[] circles2;

    protected int vertexCount;
    protected Button button;
    protected Button[] buttonList;
    protected String[] randColors;
    protected Vertex vertex;
    protected double buttonScaler;
    protected Text currentVertex;
    protected Button checkButton;

    protected VBox resBox;
    protected VBox hintBox;


    /**
     *  for some reason i cant use a constructor while linking an fxml file, so when creating a GraphView object,
     *  immediately execute this method as a constructor-replacer
     *
     * @param graph graph object of the graph
     * @param windowSizeX   horizontal size of the window
     * @param windowSizeY   vertical size of the window
     */
    public GraphView(Graph graph, int windowSizeX, int windowSizeY)
    {
        this.graph = graph;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;


        grid = new Grid(windowSizeX, windowSizeY, graph);
        vertex = new Vertex(graph);
        pick = new PickVertexColor(graph);
        colors = graph.getColor();
        lineCount = 0;

        n = graph.getN();
        m = graph.getM();
        e = graph.getE();

        lineList = new Line[m];
        needWarningList = new Boolean[m];
        vertexCount = 0;

        hintLabel1 = new Label();
        hintLabel2 = new Label();
        hintLabel1.setTextFill(Color.LIGHTGRAY);
        hintLabel2.setTextFill(Color.LIGHTGRAY);
        hintBox = new VBox(20);
        hintBox.setPrefWidth(500);
        hintBox.setLayoutX(15);
        hintBox.setLayoutY(350);
        hintBox.getChildren().addAll(hintLabel1, hintLabel2);

        resultLabel1 = new Label();
        resultLabel2 = new Label();
        resultLabel1.setTextFill(Color.LIGHTGRAY);
        resultLabel2.setTextFill(Color.LIGHTGRAY);
        resBox = new VBox(20);
        resBox.setPrefWidth(500);
        resBox.setLayoutX(15);
        resBox.setLayoutY(450);
        resBox.getChildren().addAll(resultLabel1,resultLabel2);

        makeLineColorList();
        scaleButtons();
        scaleLines();
        initArray();
        randomizeAllVertices();
        assignVertices();
        makeRandomColorList();
        setHoverEvent();
        setButtonAction();
        makeLargestHintButton();
        makeSmallestHintButton();
        makeLineHintButton();
        makeHoverHintButton();
        makeQuickHintButton();

        root.getChildren().addAll(hintBox, resBox);

    }

    /**
     * set Labels to null after use
     */
    public void setLabels()
    {
            resultLabel1.setText("");
            resultLabel2.setText("");
    }

    /**
     * makes the quick Hint button to show which vertices are connected to the same color
     */
    protected void makeQuickHintButton()
    {
        quickHintButton = new Button("Quick Hint");
        quickHintButton.setTranslateX(50);
        quickHintButton.setTranslateY(100);

        quickHintPressed = false;

        // pressing once will make the lines red, pressing again will turn it back to gray
        quickHintButton.setOnAction(e ->
        {
            makeWarningList();
            if(!quickHintPressed) {
                for (int j = 0; j < m; j++)
                {
                    //needWarningList represents if an edge contains 2 vertices that are the same color or not
                    if (needWarningList[j] == true) {
                        lineList[j].setStroke(Color.RED);
                    } else {
                        colorLine(j);
                    }
                    quickHintButton.setText("Disable");
                    quickHintPressed = true;
                }
            } else {
                for (int j = 0; j < m; j++) {
                    colorLine(j);
                }
                quickHintButton.setText("Quick Hint");
                quickHintPressed = false;
            }

        });

        root.getChildren().add(quickHintButton);
    }

    /**
     * functionality that shows which vertices should be colored with the the highest color in the array of used colors
     * = the chromatic number
     */
    public void makeLargestHintButton()
    {
        Button largestHintButton = new Button();
        largestHintButton.setText("I need some help");
        largestHintButton.setTranslateX(50);
        largestHintButton.setTranslateY(150);

        largestHintButton.setOnAction(actionEvent ->
        {

            if(!isNotColored())
            {
                // looks for illegal colorings by the user
                if(checkIfNeedWarning())
                {
                  hintLabel1.setText("You have illegal colors in your graph\nbe sure that this is not the case when\nreferring to this hint");
                  return;
                }
                Hint hint = new Hint(graph);

                System.out.println("printing largest numbers");

                hintLabel1.setText("These vertices should be colored the largest color:\n" + Arrays.toString(hint.getLargestColorVertices()));

                HintsUsed.largestHintUsed = true;
            } else {
                hintLabel1.setText("Give it a shot first before trying the hint");
            }

        });

        root.getChildren().add(largestHintButton);
    }

    /**
     *  checks if the graph has no colored vertices
     */
    private boolean isNotColored()
    {
        for(int i = 0; i < graph.getN(); i++)
        {
            if(graph.getColor().getColorOfVertex(i+1) != 0)
            {
                System.out.println("returning false");
                return false;
            }
        }
        System.out.println("returning true again");
        return true;
    }

    /**
     * functionality that shows which vertices should be colored with the smallest number in the array of used colors
     * = usually 1
     */
    public void makeSmallestHintButton()
    {
        Button smallestHintButton = new Button();
        smallestHintButton.setText("I really need some help");
        smallestHintButton.setTranslateX(50);
        smallestHintButton.setTranslateY(200);

        smallestHintButton.setOnAction(actionEvent ->
        {
            if(!isNotColored())
            {
                // looks for illegal colorings by the user
                if(checkIfNeedWarning())
                {
                    hintLabel2.setText("You have illegal colors in your graph\nbe sure that this is not the case when\nreferring to this hint");
                    return;
                }
                Hint hint = new Hint(graph);

                System.out.println("printing smallest numbers");

                hintLabel2.setText("These vertices should be colored with the smallest color:\n" + Arrays.toString(hint.getSmallestColorVertices()));

                HintsUsed.smallestHintUsed = true;

            } else {
                hintLabel2.setText("Give it a shot first");
            }

        });

        root.getChildren().add(smallestHintButton);
    }


    /**
     * enables the functionality to actively show you which vertices are connected to the same color
     */
    protected void makeLineHintButton()
    {
        lineHintButton = new Button("Graph coloring assistant 1");
        lineHintButton.setTranslateX(50);
        lineHintButton.setTranslateY(250);

        lineHintPressed = false;

        lineHintButton.setOnAction(e ->
        {
            lineHintPressed = true;
            HintsUsed.lineHintUsed = true;
        });

        root.getChildren().add(lineHintButton);
    }

    /**
     * makes the button to enable the function that shows you
     * which vertices are connected to the vertex you are currently hovering on
     */
    protected void makeHoverHintButton()
    {
        hoverHintButton = new Button("Graph coloring assistant 2");
        hoverHintButton.setTranslateX(50);
        hoverHintButton.setTranslateY(300);

        hoverHintPressed = false;

        hoverHintButton.setOnAction(e ->
        {
            hoverHintPressed = true;
            HintsUsed.hoverHintUsed = true;
            checkForFaults();
        });

        root.getChildren().add(hoverHintButton);
    }

    /**
     * sets the effect for all buttons when hovering over them
     */
    protected void setHoverEvent()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i < n; i++)
        {
            Button b = buttonList[i];

            //  action taken when entering the button
            b.setOnMouseEntered(e ->
            {
                hoverEventEntered(b);
            });

            //  action taken when exiting the button
            b.setOnMouseExited(e ->
            {
                hoverEventExited(b);
            });
        }
    }

    /**
     * sets the event that will happen when entering the button
     * @param b button we want to give these event conditions
     */
    protected void hoverEventEntered(Button b)
    {
        //increases the size of the button by 1.1
        b.setScaleX(buttonScaler * 1.1);
        b.setScaleY(buttonScaler * 1.1);

        textForButtonHover = Integer.parseInt(b.getText());
        connectedVertices = vertex.getConnVerArray(textForButtonHover);

        circles = new Circle[connectedVertices.length];
        circles2 = new Circle[connectedVertices.length];
        initCircles();

        currentVertex = new Text();
        currentVertex.setText("vertex: " + b.getText() + "\nColor: "  + colors.getColorOfVertex(Integer.parseInt(b.getText())));
        currentVertex.setY(55);
        currentVertex.setX(95);
        currentVertex.setFill(Color.WHITE);
        root.getChildren().add(currentVertex);


        if(hoverHintPressed)
        {

            for(int j = 0; j < connectedVertices.length; j++)
            {
                //scales the buttons adjacent to the vertex by 1.25
                buttonList[connectedVertices[j]-1].setScaleX(buttonScaler * 1.1);
                buttonList[connectedVertices[j]-1].setScaleY(buttonScaler * 1.1);

                Coordinate cord = new Coordinate();
                cord.x = (int) buttonList[connectedVertices[j] - 1].getLayoutX();
                cord.y = (int) buttonList[connectedVertices[j] - 1].getLayoutY();

                //draws a shape on each adjacent vertexcircles[j] = new Circle(cord.x, cord.y, 5);
                circles[j].setFill(Color.WHITE);
                root.getChildren().add(circles[j]);

                //makes a a border around the circle(by making a smaller circle inside the existing circle)
                circles2[j] = new Circle(cord.x, cord.y, 4);
                circles2[j].setFill(Color.RED);
                root.getChildren().add(circles2[j]);
            }
        }
    }

    /**
     * sets the event that will happen when exiting the button
     * @param b button we want to give these event conditions
     */
    protected void hoverEventExited(Button b)
    {
        //sets the size of the button to the default size
        b.setScaleX(buttonScaler);
        b.setScaleY(buttonScaler);

        textForButtonHover = Integer.parseInt(b.getText());

        //removes the text
        currentVertex.setText("vertex: " + "\nColor: ");



        if(hoverHintPressed)
        {
            //sets the size of all connected buttons back to the default size
            for(int j = 0; j < connectedVertices.length; j++)
            {
                buttonList[connectedVertices[j]-1].setScaleX(buttonScaler);
                buttonList[connectedVertices[j]-1].setScaleY(buttonScaler);

                //removes the shapes drawn on the adjacent vertices from root
                root.getChildren().remove(circles[j]);
                root.getChildren().remove(circles2[j]);
            }
        }
    }


    /**
     * sets the event that occurs when clicking the vertex button
     */
    protected void setButtonAction()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i< n; i++)
        {
            //copies button i to a new button b
            Button b = buttonList[i];

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
                    if(lineHintPressed)
                    {
                        makeWarningList();
                        for (int j = 0; j < m; j++)
                        {
                            //needWarningList represents if an edge contains 2 vertices that are the same color or not
                            if (needWarningList[j] == true)
                            {
                                lineList[j].setStroke(Color.RED);
                            } else {
                                colorLine(j);
                            }
                        }
                    }
                    setLabels();
                    autoCheck(); //performs auto-check after each move
                }
                else if(actionEvent.getButton() == MouseButton.SECONDARY)
                {
                    System.out.println("this is button" + b.getText());

                    //sets text to the text of the button, so text = 1 if button is 1
                    textForButtonAction = Integer.parseInt(b.getText());

                    //sets the vertex to be colored to text
                    pick.setPickedVertex(textForButtonAction);

                    //gets the current color of vertex and decreases it by 1
                    pick.pickColorDown(colors.getColorOfVertex(textForButtonAction));

                    colors.printColorArray();

                    //sets the color of the button to the associated color in the randColor list;
                    b.setStyle("-fx-background-color: " + randColors[colors.getColorOfVertex(textForButtonAction)] + "; ");


                    //coloring the line red if 2 vertices have the same color.
                    if(lineHintPressed)
                    {
                        makeWarningList();
                        for (int j = 0; j < m; j++)
                        {
                            //needWarningList represents if an edge contains 2 vertices that are the same color or not
                            if (needWarningList[j] == true)
                            {
                                lineList[j].setStroke(Color.RED);
                            } else {
                                colorLine(j);
                            }
                        }
                    }
                    setLabels();
                    autoCheck(); //performs auto-check after each move
                }

            });

            buttonList[i] = b;
        }
    }

    /**
     * checks after each playmove whether chromNum is reached, if so starts endMenu
     *
     */
    public void autoCheck()
    {
        if (colors.getColoredVert() == graph.getN() && graph.colors.numberOfColors() == graph.getChromNum() && checkIfNeedWarning() == false)
        {
            end();
        }
        else if(colors.getColoredVert() == graph.getN() && checkIfNeedWarning() == true)
        {
            resultLabel1.setText("You colored all vertices - but some colorings are not allowed.");
            resultLabel2.setText("You can do better - try again!");
        }
        else if(colors.getColoredVert() == graph.getN() && checkIfNeedWarning() == false)
        {
            resultLabel1.setText("You colored all vertices - but did not reach the chromatic number.");
            resultLabel2.setText("You used " + colors.numberOfColors() + " colors. The chromatic number for this graph is " + graph.getChromNum());
        }
    }

    /**
     * checks if there are any illegal colorings and highlights edge between illegally colored vertices
     *
     */
    protected void checkForFaults()
    {
        makeWarningList();
        for (int j = 0; j < m; j++)
        {
            //needWarningList represents if an edge contains 2 vertices that are the same color or not
            if (needWarningList[j] == true)
            {
                lineList[j].setStroke(Color.RED);
            } else {
                colorLine(j);
            }
        }

        // waits a couple seconds and colors the lines back to gray
        for (int j = 0; j < m; j++)
        {
            colorLine(j);
        }
    }


    /**
     * determines for each edge if a warning is needed(if both vertices' color are the same) and stores it
     */
    public void makeWarningList()
    {
        for(int i = 0; i < m; i++)
        {
            if( (colors.getColorOfVertex(e[i].u) == colors.getColorOfVertex(e[i].v)) && (colors.getColorOfVertex(e[i].u) != 0))
            {
                needWarningList[i] = true;
            }   else {

                needWarningList[i] = false;
            }
        }
    }

    /**
     * determines if one of the edges needs a warning
     */
    public boolean checkIfNeedWarning()
    {
        for(int i = 0; i < m; i++)
        {
            if ((colors.getColorOfVertex(e[i].u) == colors.getColorOfVertex(e[i].v)) && (colors.getColorOfVertex(e[i].u) != 0))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * end method (gets overridden by individual gamemodes - this one is actually not used!
     *
     */
    public void end()
    {
        resultLabel1.setText("This is the end.");
        resultLabel2.setText("My friend!");
    }


    /**
     * determines the size of the buttons
     */
    protected void scaleButtons()
    {
        buttonScaler = 1.05;

        if(n < 10)
        {
            buttonScaler = 1.50;
        } else {

            if (n < 20) {
                buttonScaler = 1.40;
            } else {

                if (n < 50) {
                    buttonScaler = 1.10;
                } else {

                    if (n < 100) {
                        buttonScaler = 1.00;
                    } else {

                        if (n < 150) {
                            buttonScaler = 0.90;
                        }
                    }
                }
            }
        }
    }


    /**
     * determines the size of the lines
     */
    protected void scaleLines()
    {

        lineScaler = 1.15;

        if(m < 10)
        {
            lineScaler = 7;
        } else {

            if (m < 20) {
                lineScaler = 6;
            } else {

                if (m < 50) {
                    lineScaler = 4.5;
                } else {

                    if (m < 100) {
                        lineScaler = 3.5;
                    } else {

                        if (m < 150) {
                            lineScaler = 2.0;
                        } else {

                            if (m < 300) {
                                lineScaler = 1.6;
                            } else {

                                if (m < 500) {
                                    lineScaler = 1.4;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * makes a list of random colors (HEX color codes as Strings)
     */
    private void makeRandomColorList()
    {

        randColors = new String[1000];

        for(int i = 0; i < randColors.length; i++)
        {
            Random r = new Random();
            int random = r.nextInt(999999);

            //makes sure random is bigger than 100000 before it continues
            while(random < 100000) {
                random = r.nextInt(999999);
            }

            String randomToString = String.valueOf(random);
            String c = "#ff";

            randColors[i] = "#" + randomToString;
        }
    }

    /**
     * initiates the Coordinate array positions and Button array buttonList
     */
    private void initArray()
    {
        positions = new Coordinate[n];

        //sets all indices of positions to an empty coordinate object
        for(int i = 0; i < n; i++)
        {
            Coordinate v = new Coordinate();
            positions[i] = v;
        }

        buttonList = new Button[n];

        //sets all indices of buttonList to empty button objects
        for(int i = 0; i  < n; i++)
        {
            Button button = new Button();
            buttonList[i] = button;
        }
    }


    /**
     * initializes circles[] and circles2[]
     */
    protected void initCircles()
    {
        for(int i = 0; i < circles.length; i++)
        {
            Circle circle = new Circle();
            circles[i] = circle;
            circles2[i] = circle;
        }
    }


    /**
     * gives a vertex a coordinate object
     *
     * @param vertex vertex we want to give a place
     * @param x coordinate we want the vertex to have
     * @param y coordinate we want the vertex to have
     */
    private void placeVertex(int vertex, int x, int y)
    {
        //makes a coordinate object and passes the x and y values to it
        Coordinate v = new Coordinate(x, y);

        //gives positions with index "vertex" the coordinate object
        positions[vertex] = v;
    }


    /**
     * shuffles the array containing all positions for a vertex and assigns each vertex a position.
     */
    public void randomizeAllVertices()
    {
        //shuffles the content of GridPositions to get a random order of all possible possitions on the canvas
        grid.shuffle();

        //assigns these random values to all vertices
        for(int i = 0; i < n; i++)
        {
            Coordinate cord = grid.getRandomCord(i);
            placeVertex(i, cord.x, cord.y);
        }
    }



    /**
     *  retrieves a cord object from the positions list and places a button on that position
     *
     * @param vertex vertex we want to place on the pane
     */
    protected void makeButtonOnVertex(int vertex)
    {
        //retrieves the coordinates of each vertex from positions
        Coordinate cord = new Coordinate();
        cord = positions[vertex];

        //giving each vertex text representing their number
        Text t = new Text("Node " + (vertexCount+1));
        t.setFill(Color.WHITE);
        t.setLayoutX(cord.x - 10);
        t.setLayoutY(cord.y - 25);
        //root.getChildren().add(t);

        //making a new button for each vertex
        button = new Button(   String.valueOf(vertexCount+1));
        buttonList[vertexCount] = button;

        //gives the button a color
        button.setStyle("-fx-background-color: #e1e9f5; ");

        //scales the button and relocates the button a tiny bit to be centered nicely
        button.setScaleX(buttonScaler);
        button.setScaleY(buttonScaler);
        button.setLayoutX(cord.x - 15);
        button.setLayoutY(cord.y - 15);

        root.getChildren().add(button);

        vertexCount++;
    }


    /**
     * draws a line between vertex 1 and vertex 2
     *
     * @param vertex1 starting vertex
     * @param vertex2 ending vertex
     */
    protected void makeLine(int vertex1, int vertex2) {
        //retrieves the coordinates of vertex 1 and 2 from positions
        Coordinate v1 = positions[vertex1];
        Coordinate v2 = positions[vertex2];

        //draws a line from the coordinates of vertex 1 to the coordinates of vertex 2
        Line l = new Line(v1.x, v1.y, v2.x, v2.y);
        l.setStrokeWidth(lineScaler);
        lineList[lineCount] = l;

        colorLine(lineCount);

        lineCount++;
        root.getChildren().add(l);
    }

    /**
     * colors the line
     * @param index of the lineColorList array and lineList array
     */
    protected void colorLine(int index)
    {
        if (lineColorList[index].equals("GREY")) {
            lineList[index].setStroke(Color.GREY);
        } else {
            if (lineColorList[index].equals("WHITE")) {
                lineList[index].setStroke(Color.WHITE);
            } else {
                if (lineColorList[index].equals("BEIGE")) {
                    lineList[index].setStroke(Color.BEIGE);
                } else {
                    if (lineColorList[index].equals("DARKGRAY")) {
                        lineList[index].setStroke(Color.DARKGRAY);
                    }
                }
            }
        }
    }


    /**
     * makes an array containing all drawn lines
     */
    private void makeLineColorList()
    {

        lineColorList = new String[m];

        for(int i = 0; i < m; i++)
        {
            lineColorList[i] = new String();

            //making the line colors random:
            int rand = (int)(Math.random()*100);

            //assigns one of 4 colors to each new line randomly
            if(rand < 25)
            {
                lineColorList[i] = "GREY";
            } else {
                if (rand < 50) {
                    lineColorList[i] = "WHITE";
                } else {
                    if (rand < 75) {
                        lineColorList[i] = "BEIGE";
                    } else {
                        lineColorList[i] = "DARKGRAY";
                    }
                }
            }
        }
    }


    /**
     * executes the makeLine method for each edge and executes the makeCircle method for each vertex
     */
    private void assignVertices()
    {
        root = new Group();

        //makes m lines
        for(int i = 0; i < m; i++)
        {
            makeLine(e[i].u - 1, e[i].v - 1);
        }

        //makes n circles
        for(int i = 0; i < n; i++)
        {
            makeButtonOnVertex(i);
        }

    }


    /**
     *  returns root(Group object)
     */
    public Group getGroup()
    {
        return root;
    }

}
