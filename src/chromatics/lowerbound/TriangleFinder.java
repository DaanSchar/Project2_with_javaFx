package chromatics.lowerbound;

import graph.Graph;
import graph.Vertex;

public class TriangleFinder
{

    private Graph graph;
    private int n;
    private Shape[] outputShapes;
    private boolean containsShape;

    public TriangleFinder(Graph graphObject)
    {
        graph = graphObject;
        n = graph.n;
        containsShape = false;

        find();
    }


    /**
     * method to find if a graph contains triangles, as in: a is connected to b, which is connected to c, which
     * is connected to a
     */
    private void find() {

        Vertex vertex = new Vertex(graph);
        int count = 1;

        Shape[] tempT = new Shape[1000000];
        initShapesArray(tempT);

        for(int a = 0; a < n + 1; a++)
        {

            int[] arrA = vertex.getConnVerArray(a);

            for(int i = 0; i < arrA.length + 1; i++)
            {
                if (arrayContains(arrA, i))
                {

                    int[] arrI = vertex.getConnVerArray(i);

                    for(int j = 0; j < arrI.length + 1; j++)
                    {
                        if(arrayContains(arrI, j))
                        {

                            int[] arrJ = vertex.getConnVerArray(j);

                            if( (arrayContains(arrJ, a)))
                            {
                                boolean duplicate = false;

                                for(int l = 0; l < count; l++)
                                {
                                    if( (contains(tempT[l], a)) && (contains(tempT[l], i)) && (contains(tempT[l], j)) )
                                    {
                                        duplicate = true;
                                    }
                                }

                                if(duplicate == false)
                                {
                                    Shape c = new Shape();
                                    c.a = a;
                                    c.b = i;
                                    c.c = j;


                                    tempT[count - 1] = c;
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }


        if(count > 1)
        {
            containsShape = true;
        }


        //transports all triangle objects to an array that is sufficiently in size

        int length = 0;

        for(int i = 0; i < tempT.length; i++)
        {
            if(tempT[i].a == 0)
            {
                length = i;
                break;
            }
        }

        outputShapes = new Shape[length];

        for(int i = 0; i < outputShapes.length; i++)
        {
            outputShapes[i] = tempT[i];
        }

    }

    /**
     * returns containsTriangle
     *
     * @return
     * boolean containsTriangle
     */
    public boolean containsShape()
    {
        return containsShape;
    }

    /**
     * returns triangle array t which contains all triangles
     *
     * @return
     * triangle array t
     */
    public Shape[] getShapes()
    {
        return outputShapes;
    }


    /**
     * checks if an int array contains a certain value
     *
     * @param a
     * array we a looking inside
     *
     * @param vertex
     * vertex, or int value we ar looking for inside the array
     *
     * @return
     * returns the index of the array where vertex is found
     */
    private boolean arrayContains(int[] a, int vertex)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == vertex)
            {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Shape shape, int value)
    {
        if(shape.a == value)
        {
            return true;
        }
        if(shape.b == value)
        {
            return true;
        }
        if(shape.c == value)
        {
            return true;
        }
        return false;
    }


    /**
     * creates a chromatics.lowerbound.Triangle object for every index of array
     */
    private void initShapesArray(Shape[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            Shape c = new Shape();
            array[i] = c;
        }
    }

    /**
     * prints t, which contains all triangles
     */
    public void print()
    {
        System.out.println("Triangles in this graph: ");
        for(int i = 0; i < outputShapes.length; i++)
        {
            System.out.println(outputShapes[i].a + " " + outputShapes[i].b + " " + outputShapes[i].c);
        }
        System.out.println();
    }

}
