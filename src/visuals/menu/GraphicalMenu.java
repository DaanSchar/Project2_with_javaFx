package visuals.menu;

import graph.ColEdge;
import graph.GenerateRandomGraph;
import graph.Graph;
import graph.ReadGraph;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import visuals.game.GraphViewScene;

import java.io.IOException;

public class GraphicalMenu extends Application {

    public static MenuChoices MENU_CHOICES = null;
    private Scene scene;
    private Graph graph;
    private ColEdge[] e;
    private int n;
    private int m;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        // gamemode
        ChoiceBox<String> gameMode = new ChoiceBox<>(FXCollections.observableArrayList("To the end",
                "Timed best attempt", "Random order coloring"));

        Button accept = new Button();
        accept.setText("Accept");
        Text gameModeText = new Text("Select the gamemode");

        //time limit
        TextField timeLimit = new TextField();
        timeLimit.setPromptText("Enter the time limit here");

        //graph choice
        Text graphText = new Text("Select how the graph for the game will be generated");
        ChoiceBox<String> graphChoice = new ChoiceBox<>(FXCollections.observableArrayList("Random", "Imported"));
        TextField vertexAmount = new TextField();
        vertexAmount.setPromptText("Amount of vertices for the random graph");
        TextField edgeAmount = new TextField();
        edgeAmount.setPromptText("Amount of edges for the random graph");
        TextField graphFile = new TextField();
        graphFile.setPromptText("enter the full path to the graph file here");

        // display construction
        VBox root = new VBox();
        root.getChildren().add(gameModeText);
        root.getChildren().add(gameMode);
        root.getChildren().add(timeLimit);
        root.getChildren().add(graphText);
        root.getChildren().add(graphChoice);
        root.getChildren().add(vertexAmount);
        root.getChildren().add(edgeAmount);
        root.getChildren().add(graphFile);
        root.getChildren().add(accept);
        root.setSpacing(5);

        root.setAlignment(Pos.CENTER);

        //accept the data once the accept button is clicked
        accept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                MenuChoices newChoices = new MenuChoices();

                // get game mode first
                if (gameMode.getValue() != null) {
                    switch (gameMode.getValue()) {
                        case "To the end":
                            newChoices.setGameMode(1);
                            break;
                        case "Timed best attempt":
                            newChoices.setGameMode(2);
                            break;
                        case "Random order coloring":
                            newChoices.setGameMode(3);
                            break;
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "You must select a game mode", ButtonType.CLOSE).showAndWait();
                    return;
                }

                //get time if the timed gamemode was selected
                if (newChoices.getGameMode() == 2) {
                    try {
                        newChoices.setTime(Integer.parseInt(timeLimit.getText()));
                    } catch (NumberFormatException e) {
                        new Alert(Alert.AlertType.ERROR, "The time limit must be an integer value", ButtonType.CLOSE).showAndWait();
                        return;
                    }
                }

                //get the graph choice
                if (graphChoice.getValue() != null) {
                    switch (graphChoice.getValue()) {
                        case "Random":
                            newChoices.setRandomGraph(true);
                            break;
                        case "Imported":
                            newChoices.setRandomGraph(false);
                            break;
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "You must select how the graph will be generated", ButtonType.CLOSE).showAndWait();
                    return;
                }

                //get the random graph parameters
                if (newChoices.isRandomGraph()) {
                    // get vertex amount
                    if (!vertexAmount.getText().isBlank()) {
                        try {
                            newChoices.setVertices(Integer.parseInt(vertexAmount.getText()));
                        } catch (NumberFormatException e) {
                            new Alert(Alert.AlertType.ERROR, "Vertex amount must be an integer", ButtonType.CLOSE).showAndWait();
                            return;
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "You must enter a vertex amount", ButtonType.CLOSE).showAndWait();
                        return;
                    }
                    // get edge amount
                    if (!edgeAmount.getText().isBlank()) {
                        try {
                            newChoices.setEdges(Integer.parseInt(edgeAmount.getText()));
                        } catch (NumberFormatException e) {
                            new Alert(Alert.AlertType.ERROR, "Edge amount must be an integer", ButtonType.CLOSE).showAndWait();
                            return;
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "You must enter an edge amount", ButtonType.CLOSE).showAndWait();
                        return;
                    }
                } else {
                    // get the file full path
                    if (!graphFile.getText().isBlank()) {
                        newChoices.setGraphFileName(graphFile.getText());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "You must select a file to import", ButtonType.CLOSE).showAndWait();
                        return;
                    }
                }

                // finally set the return values
                MENU_CHOICES = newChoices;

                // generating a random graph
                if(getMenuChoice().isRandomGraph())
                {
                    try {
                        //  making a random graph object with the chosen vertices and chosen edges
                        GenerateRandomGraph randomGraphObject = new GenerateRandomGraph(MENU_CHOICES.getVertices(), MENU_CHOICES.getEdges());

                        // passing e to menuChoice and making a new graph object
                        getMenuChoice().setE(randomGraphObject.getRandomGraph());
                        MENU_CHOICES.makeGraph();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    //if graph is not set to random, it will read a graph
                    ReadGraph file = new ReadGraph(MENU_CHOICES.getGraphFileName());

                    // making a graph object and passing it to the MENU_CHOICES object
                    MENU_CHOICES.setVertices(file.getFileVertices());
                    MENU_CHOICES.setEdges(file.getFileEdges());
                    MENU_CHOICES.setE(file.getGraph());
                    MENU_CHOICES.makeGraph();
                }

                //  transition to the next scene by making a GraphViewScene object, inserting MENU_CHOICES, inserting
                //  the stage of this class inside the start method of graphViewScene and setting the scene of this
                //  class' stage to the scene made in GraphViewScene
                GraphViewScene graphViewScene = new GraphViewScene(MENU_CHOICES);
                try {
                    graphViewScene.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.setScene(graphViewScene.getGraphViewScene());

            }
        });

        //display
        scene = new Scene(root, 400, 300);
    }

    //retrieve the results
    public static MenuChoices getMenuChoice() {
        return MENU_CHOICES;
    }

    // retrieving the Scene from this class
    public Scene getMenuScene()
    {
        return scene;
    }

    // data storage object
    public class MenuChoices {

        private int gameMode;
        private int time;
        private boolean randomGraph;
        private String graphFileName;
        private int vertices;
        private int edges;
        private Graph graph;
        private ColEdge[] e;

        public MenuChoices() {

        }

        public ColEdge[] getE()
        {
            return e;
        }

        public void setE(ColEdge[] e)
        {
            this.e = e;
        }

        public void makeGraph()
        {
            graph = new Graph();
        }

        public Graph getGraph()
        {
            return graph;
        }

        public int getGameMode() {
            return gameMode;
        }

        public void setGameMode(int gameMode) {
            this.gameMode = gameMode;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public boolean isRandomGraph() {
            return randomGraph;
        }

        public void setRandomGraph(boolean randomGraph) {
            this.randomGraph = randomGraph;
        }

        public String getGraphFileName() {
            return graphFileName;
        }

        public void setGraphFileName(String graphFileName) {
            this.graphFileName = graphFileName;
        }

        public int getVertices() {
            return vertices;
        }

        public void setVertices(int vertices) {
            this.vertices = vertices;
        }

        public int getEdges() {
            return edges;
        }

        public void setEdges(int edges) {
            this.edges = edges;
        }
    }

}
