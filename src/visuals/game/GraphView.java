/**
 * class that draws the graph
 *
 * @author Daan
 */

package visuals.game;

import gamemodes.PickVertexColor;
import gamemodes.Warning;
import graph.ColEdge;
import graph.Colors;
import graph.Graph;
import graph.Vertex;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;


public class GraphView
{

    private Group root;
    private Graph graph;
    private PickVertexColor pick;
    private int r;
    private int windowSizeX;
    private int windowSizeY;
    private Colors colors;
    private int textForButtonAction;
    private int textForButtonHover;
    private Coordinate[] positions;
    private Grid grid;
    private ColEdge[] e;
    private int n;
    private int m;

    private int[] connectedVertices;
    private String[] lineColorList;
    private int lineCount;
    private Line[] lineList;
    private Boolean[] needWarningList;

    private Circle[] circles;
    private Circle[] circles2;

    private int vertexCount;
    public Button button;
    private Button[] buttonList;
    private String[] randColors;
    private Vertex vertex;
    private double buttonScaler;
    private double lineScaler;
    private Text currentVertex;


    /**
     *  for some reason i cant use a constructor while linking an fxml file, so when creating a GraphView object,
     *  immediately execute this method as a constructor-replaces
     *
     * @param graph graph object of the graph
     * @param windowSizeX   horizontal size of the window
     * @param windowSizeY   vertical size of the window
     * @param r radius of the circles we draw
     */
    public void startGraphView(Graph graph, int windowSizeX, int windowSizeY, int r)
    {
        this.graph = graph;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.r = r;


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

        makeLineColorList();
        scaleButtons();
        scaleLines();
        initArray();
        randomizeAllVertices();
        assignVertices();
        makeRandomColorList();
        setHoverEvent();
        setButtonAction();
    }

    /**
     * determines the size of the buttons
     */
    private void scaleButtons()
    {

        buttonScaler = 1.05;

        if(n < 10)
        {
            buttonScaler = 1.50;
        } else {

            if(n < 20)
            {
                buttonScaler = 1.40;
            } else {

                if(n < 50)
                {
                    buttonScaler = 1.10;
                } else {

                    if(n < 100)
                    {
                        buttonScaler = 1.00;
                    } else {

                        if(n < 150)
                        {
                            buttonScaler = 0.90;
                        }}}}}
    }


    /**
     * determines the size of the lines
     */
    private void scaleLines()
    {

        lineScaler = 1.15;

        if(m < 10)
        {
            lineScaler = 7;
        } else {

            if(m < 20)
            {
                lineScaler = 6;
            } else {

                if(m < 50)
                {
                    lineScaler = 4.5;
                } else {

                    if(m < 100)
                    {
                        lineScaler = 3.5;
                    } else {

                        if(m < 150)
                        {
                            lineScaler = 2.0;
                        } else {

                            if(m < 300)
                            {
                                lineScaler = 1.6;
                            } else {

                                if(m < 500)
                                {
                                    lineScaler = 1.4;
                                }
                        }}}}}}
    }

    /**
     * sets the effect for all buttons when hovering over them
     */
    private void setHoverEvent()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i < n; i++)
        {
            Button b = buttonList[i];

            //  action taken when entering the button
            b.setOnMouseEntered(e ->
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
                currentVertex.setText(b.getText());
                currentVertex.setY(55);
                currentVertex.setX(95);
                currentVertex.setFill(Color.WHITE);
                root.getChildren().add(currentVertex);

                for(int j = 0; j < connectedVertices.length; j++)
                {
                    //scales the buttons adjacent to the vertex by 1.25
                    buttonList[connectedVertices[j]-1].setScaleX(buttonScaler * 1.1);
                    buttonList[connectedVertices[j]-1].setScaleY(buttonScaler * 1.1);

                    Coordinate cord = new Coordinate();
                    cord.x = (int)buttonList[connectedVertices[j]-1].getLayoutX();
                    cord.y = (int)buttonList[connectedVertices[j]-1].getLayoutY();

                    //draws a shape on each adjacent vertex
                    circles[j] = new Circle(cord.x, cord.y, r-10);
                    circles[j].setFill(Color.WHITE);
                    root.getChildren().add(circles[j]);

                    //makes a a border around the circle(by making a smaller circle inside the existing circle)
                    circles2[j] = new Circle(cord.x, cord.y, r-11);
                    circles2[j].setFill(Color.RED);
                    root.getChildren().add(circles2[j]);
                }
            });

            //  action taken when exiting the button
            b.setOnMouseExited(e ->
            {
                //sets the size of the button to the default size
                b.setScaleX(buttonScaler);
                b.setScaleY(buttonScaler);

                textForButtonHover = Integer.parseInt(b.getText());

                //removes the text
                root.getChildren().remove(currentVertex);

                //sets the size of all connected buttons back to the default size
                for(int j = 0; j < connectedVertices.length; j++)
                {
                    buttonList[connectedVertices[j]-1].setScaleX(buttonScaler);
                    buttonList[connectedVertices[j]-1].setScaleY(buttonScaler);

                    //removes the shapes drawn on the adjacent vertices from root
                    root.getChildren().remove(circles[j]);
                    root.getChildren().remove(circles2[j]);
                }
            });
        }
    }


    /**
     * sets the action of all buttons of the graph
     */
    private void setButtonAction()
    {
        //  loops because it needs these effects need to be applied to all buttons in the button list
        for(int i = 0; i< n; i++)
        {
            //copies button i to a new button b
            Button b = buttonList[i];

            //sets the action of the button
            b.setOnAction(actionEvent ->
            {
                System.out.println("this is button" + b.getText());

                //sets text to the text of the button, so text = 1 if button is 1
                textForButtonAction = Integer.parseInt(b.getText());

                //sets the vertex to be colored to text
                pick.setPickedVertex(textForButtonAction);

                //gets the current color of vertex and increases it by 1
                pick.pickColor2(colors.getColorOfVertex(textForButtonAction));

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

            });

            //returns the copied button with the added actionEvent to the button list
            buttonList[i] = b;
        }
    }


    public void checkIfNeedWarning()
    {
        for(int i = 0; i < m; i++)
        {
            if( (colors.getColorOfVertex(e[i].u) == colors.getColorOfVertex(e[i].v)) && (colors.getColorOfVertex(e[i].u) != 0)  )
            {
                needWarningList[i] = true;
            }   else {
                needWarningList[i] = false;
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
    private void initCircles()
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
    private void makeCircle(int vertex)
    {
        //retrieves the coordinates of eacht vertex from positions
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
    public void makeLine(int vertex1, int vertex2) {
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
    private void colorLine(int index)
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

        System.out.println(Arrays.toString(lineColorList));

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
            makeCircle(i);
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
