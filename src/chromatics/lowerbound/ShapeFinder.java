package chromatics.lowerbound;

import graph.Graph;

public class ShapeFinder
{
    private ShapeData squareData;
    private ShapeData pentagonData;
    private ShapeData hexagonData;
    private ShapeData septagonData;
    private ShapeData octagonData;
    private ShapeData nonagonData;
    private ShapeData decagonData;


    private Shape[] squares;
    private Shape[] pentagons;
    private Shape[] hexagons;
    private Shape[] septagons;
    private Shape[] octagons;
    private Shape[] nonagons;
    private Shape[] decagons;

    private Graph graph;

    public ShapeFinder(Graph graphObject)
    {
        graph = graphObject;
        findShapes();
    }

    public void findShapes() {
        TriangleFinder triangleData = new TriangleFinder(graph);
        Shape[] triangles = triangleData.getShapes();

        if (triangleData.containsShape()) {
            System.out.println("graph contains triangles");

            squareData = new ShapeData(triangles, graph);
            squares = squareData.getShapes();

            if (squareData.containsShape()) {
                System.out.println("graph contains squares");

                pentagonData = new ShapeData(squares, graph);
                pentagons = pentagonData.getShapes();

                if (pentagonData.containsShape()) {
                    System.out.println("graph contains pentagons");

                    hexagonData = new ShapeData(pentagons, graph);
                    hexagons = hexagonData.getShapes();

                    if (hexagonData.containsShape()) {
                        System.out.println("graph contains hexagon");

                        septagonData = new ShapeData(hexagons, graph);
                        septagons = septagonData.getShapes();

                        if (septagonData.containsShape()) {
                            System.out.println("graph contains septagon");

                            octagonData = new ShapeData(septagons, graph);
                            octagons = octagonData.getShapes();

                            if (octagonData.containsShape()) {
                                System.out.println("graph contains octagon");

                                nonagonData = new ShapeData(octagons, graph);
                                nonagons = nonagonData.getShapes();

                                if (nonagonData.containsShape()) {
                                    System.out.println("graph contains nonagon");

                                    decagonData = new ShapeData(nonagons, graph);
                                    decagons = decagonData.getShapes();

                                    if (decagonData.containsShape()) {
                                        System.out.println("graph contains decagon");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}