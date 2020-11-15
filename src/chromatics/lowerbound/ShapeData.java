package chromatics.lowerbound;

import graph.Graph;
import graph.Vertex;

public class ShapeData
{

    private Graph graph;
    private Shape[] inputShapes;
    private Shape[] outputShapes;
    private boolean containsShape;
    private int n;


    public ShapeData(Shape[] shapeArray, Graph graphObject)
    {
        inputShapes = shapeArray;
        graph = graphObject;
        n = graph.getN();
        containsShape = false;

        find();
    }


    public void find()
    {
        //  finds all squares in a graph

        int count = 1;

        Shape[] tempArray = new Shape[10000000];
        initArray(tempArray);

        for (int i = 0; i < n + 1; i++) {

            Vertex vertex = new Vertex(graph);
            int[] vArray = vertex.getConnVerArray(i);

            for (int j = 0; j < inputShapes.length; j++)
            {
                if (arrayContainsShape(vArray, inputShapes[j]))
                {
                    Shape q = new Shape();

                    Shape shape = inputShapes[j];

                    if(shape.d == 0)
                    {
                        q.a = shape.a;
                        q.b = shape.b;
                        q.c = shape.c;
                        q.d = i;
                    } else {
                        if(shape.e == 0)
                        {
                            q.a = shape.a;
                            q.b = shape.b;
                            q.c = shape.c;
                            q.d = shape.d;
                            q.e = i;
                        } else {
                            if (shape.f == 0) {
                                q.a = shape.a;
                                q.b = shape.b;
                                q.c = shape.c;
                                q.d = shape.d;
                                q.e = shape.e;
                                q.f = i;
                            } else {
                                if (shape.g == 0) {
                                    q.a = shape.a;
                                    q.b = shape.b;
                                    q.c = shape.c;
                                    q.d = shape.d;
                                    q.e = shape.e;
                                    q.f = shape.f;
                                    q.g = i;
                                } else {
                                    if (shape.h == 0) {
                                        q.a = shape.a;
                                        q.b = shape.b;
                                        q.c = shape.c;
                                        q.d = shape.d;
                                        q.e = shape.e;
                                        q.f = shape.f;
                                        q.g = shape.g;
                                        q.h = i;
                                    } else {
                                        if (shape.i == 0) {
                                            q.a = shape.a;
                                            q.b = shape.b;
                                            q.c = shape.c;
                                            q.d = shape.d;
                                            q.e = shape.e;
                                            q.f = shape.f;
                                            q.g = shape.g;
                                            q.h = shape.h;
                                            q.i = i;
                                        } else {
                                            if (shape.j == 0) {
                                                q.a = shape.a;
                                                q.b = shape.b;
                                                q.c = shape.c;
                                                q.d = shape.d;
                                                q.e = shape.e;
                                                q.f = shape.f;
                                                q.g = shape.g;
                                                q.h = shape.h;
                                                q.i = shape.i;
                                                q.j = i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    tempArray[count - 1] = q;
                    count++;
                }
            }
        }

        outputShapes = new Shape[count - 1];
        initArray(outputShapes);

        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i].a != 0) {
                outputShapes[i] = tempArray[i];
            }
        }

        if (count > 1) {
            containsShape = true;
        }
    }


    private boolean arrayContainsShape(int[] array, Shape shape)
    {

        if (    (arrayContains(array, shape.a) || shape.a == 0) &&
                (arrayContains(array, shape.b) || shape.b == 0) &&
                (arrayContains(array, shape.c) || shape.c == 0) &&
                (arrayContains(array, shape.d) || shape.d == 0) &&
                (arrayContains(array, shape.e) || shape.e == 0) &&
                (arrayContains(array, shape.f) || shape.f == 0) &&
                (arrayContains(array, shape.g) || shape.g == 0) &&
                (arrayContains(array, shape.h) || shape.h == 0) &&
                (arrayContains(array, shape.i) || shape.i == 0) &&
                (arrayContains(array, shape.j) || shape.j == 0) &&
                (arrayContains(array, shape.k) || shape.k == 0) &&
                (arrayContains(array, shape.l) || shape.l == 0) &&
                (arrayContains(array, shape.m) || shape.m == 0) &&
                (arrayContains(array, shape.n) || shape.n == 0) &&
                (arrayContains(array, shape.o) || shape.o == 0) &&
                (arrayContains(array, shape.p) || shape.p == 0)   )

        {
            return true;
        }
        return false;
    }


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


    private void initArray(Object[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            Shape shape = new Shape();
            array[i] = shape;
        }
    }


    public Shape[] getShapes()
    {
            return outputShapes;
    }

    public boolean containsShape()
    {
        return containsShape;
    }

    public void print()
    {
        System.out.println("Shapes:");
        for(int i = 0; i < outputShapes.length; i++)
        {
            System.out.println();
            System.out.print(outputShapes[i].a + " ");
            System.out.print(outputShapes[i].b + " ");
            System.out.print(outputShapes[i].c + " ");
            System.out.print(outputShapes[i].d + " ");
            System.out.print(outputShapes[i].e + " ");
            System.out.print(outputShapes[i].f + " ");
            System.out.print(outputShapes[i].g + " ");
            System.out.print(outputShapes[i].h + " ");
            System.out.print(outputShapes[i].i + " ");
            System.out.print(outputShapes[i].j + " ");
            System.out.print(outputShapes[i].k + " ");
            System.out.print(outputShapes[i].l + " ");
            System.out.print(outputShapes[i].m + " ");
            System.out.print(outputShapes[i].n + " ");
            System.out.print(outputShapes[i].o + " ");
            System.out.print(outputShapes[i].p + " ");
        }
    }
}
