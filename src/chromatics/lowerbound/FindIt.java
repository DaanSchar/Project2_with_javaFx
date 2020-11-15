package chromatics.lowerbound;

import graph.Graph;
import graph.Vertex;

public class FindIt
{

    private Graph graph;
    private int n;
    private int m;

    public FindIt(Graph graph)
    {
        this.graph = graph;
        n = graph.getM();

        find();
    }


    /**
     * this method works by checking is vertex a is connected to b, if vertex b is connected to c
     * and if vertex c is connected to a, establishing a "triangle shape".
     * if so, it checks if all these vertices are connected to vertex d, establishing a "square shape",
     * where all vertices are connected. this keeps going on until the largest shape has been found.
     * (unless the shape is greater than the limit of this algorithm)
     *
     */
    public void find()
    {
        Vertex vertex = new Vertex(graph);

        for(int a = 0; a < n + 1; a++)
        {

            int[] arrA = vertex.getConnVerArray(a);

            for(int b = 0; b < n + 1; b++)
            {
                int[] arrB = vertex.getConnVerArray(b);

                //checks if b is connected to a
                if(arrayContains(arrA, b))
                {

                    for(int c = 0; c < n + 1; c++)
                    {

                        int[] arrC = vertex.getConnVerArray(c);

                        //checks if c is connected to b
                        if(arrayContains(arrB, c))
                        {

                            for(int z = 0; z < n + 1; z++)
                            {
                                if(arrayContains(arrC, a))
                                {
                                    //System.out.println(a + " " + b + " " + c);

                                    for(int d = 0; d < n + 1; d++)
                                    {
                                        int[] arrD = vertex.getConnVerArray(d);

                                        if(arrayContains(arrD,a) && arrayContains(arrD,b) && arrayContains(arrD,c))
                                        {
                                            //System.out.println(a + " " + b + " " + c + " " + d);

                                            for(int e = 0; e < n + 1; e++)
                                            {
                                                int[] arrE = vertex.getConnVerArray(e);

                                                if(arrayContains(arrE,a) && arrayContains(arrE,b) && arrayContains(arrE,c) && arrayContains(arrE,d))
                                                {
                                                    //System.out.println(a + " " + b + " " + c + " " + d + " " + e);

                                                    for(int f = 0; f < n + 1; f++)
                                                    {
                                                        int[] arrF = vertex.getConnVerArray(f);

                                                        if(arrayContains(arrF,a) && arrayContains(arrF,b) && arrayContains(arrF,c) && arrayContains(arrF,d) && arrayContains(arrF,e))
                                                        {
                                                            //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);

                                                            for(int g = 0; g < n + 1; g++)
                                                            {
                                                                int[] arrG = vertex.getConnVerArray(g);

                                                                if(arrayContains(arrG,a) && arrayContains(arrG,b) && arrayContains(arrG,c) && arrayContains(arrG,d) && arrayContains(arrG,e) && arrayContains(arrG,f))
                                                                {
                                                                   // System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g);

                                                                    for(int h = 0; h < n + 1; h++)
                                                                    {
                                                                        int[] arrH = vertex.getConnVerArray(h);

                                                                        if(arrayContains(arrH,a) && arrayContains(arrH,b) && arrayContains(arrH,c) && arrayContains(arrH,d) && arrayContains(arrH,e) && arrayContains(arrH,f) && arrayContains(arrH,g))
                                                                        {
                                                                            //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h);

                                                                            for(int i = 0; i < n + 1; i++)
                                                                            {
                                                                                int[] arrI = vertex.getConnVerArray(i);

                                                                                if(arrayContains(arrI,a) && arrayContains(arrI,b) && arrayContains(arrI,c) && arrayContains(arrI,d) && arrayContains(arrI,e) && arrayContains(arrI,f) && arrayContains(arrI,g) && arrayContains(arrI,h))
                                                                                {
                                                                                    //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i);

                                                                                    for(int j = 0; i < n + 1; j++)
                                                                                    {
                                                                                        int[] arrJ = vertex.getConnVerArray(j);

                                                                                        if(arrayContains(arrJ,a) && arrayContains(arrJ,b) && arrayContains(arrJ,c) && arrayContains(arrJ,d) && arrayContains(arrJ,e) && arrayContains(arrJ,f) && arrayContains(arrJ,g) && arrayContains(arrJ,h) && arrayContains(arrJ,i))
                                                                                        {
                                                                                            //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j);

                                                                                            for(int k = 0; k < n + 1; k++)
                                                                                            {
                                                                                                int[] arrK = vertex.getConnVerArray(k);

                                                                                                if(arrayContains(arrK,a) && arrayContains(arrK,b) && arrayContains(arrK,c) && arrayContains(arrK,d) && arrayContains(arrK,e) && arrayContains(arrK,f) && arrayContains(arrK,g) && arrayContains(arrK,h) && arrayContains(arrK,i) && arrayContains(arrK,j))
                                                                                                {
                                                                                                    //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j + " " + k);

                                                                                                    for(int l = 0; l < n + 1; l++)
                                                                                                    {
                                                                                                        int[] arrL = vertex.getConnVerArray(l);

                                                                                                        if(arrayContains(arrL,a) && arrayContains(arrL,b) && arrayContains(arrL,c) && arrayContains(arrL,d) && arrayContains(arrL,e) && arrayContains(arrL,f) && arrayContains(arrL,g) && arrayContains(arrL,h) && arrayContains(arrL,i) && arrayContains(arrL,j) && arrayContains(arrL,k))
                                                                                                        {
                                                                                                            //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j + " " + k + " " + l);

                                                                                                            for(int m = 0; m < n + 1; m++)
                                                                                                            {
                                                                                                                int[] arrM = vertex.getConnVerArray(m);

                                                                                                                if(arrayContains(arrM,a) && arrayContains(arrM,b) && arrayContains(arrM,c) && arrayContains(arrM,d) && arrayContains(arrM,e) && arrayContains(arrM,f) && arrayContains(arrM,g) && arrayContains(arrM,h) && arrayContains(arrM,i) && arrayContains(arrM,j) && arrayContains(arrM,k) && arrayContains(arrM,l))
                                                                                                                {
                                                                                                                    //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j + " " + k + " " + l + " " + m);

                                                                                                                    //  skipping n because n = number of vertices
                                                                                                                    for(int o = 0; o < n + 1; o++)
                                                                                                                    {
                                                                                                                        int[] arrO = vertex.getConnVerArray(o);

                                                                                                                        if(arrayContains(arrO,a) && arrayContains(arrO,b) && arrayContains(arrO,c) && arrayContains(arrO,d) && arrayContains(arrO,e) && arrayContains(arrO,f) && arrayContains(arrO,g) && arrayContains(arrO,h) && arrayContains(arrO,i) && arrayContains(arrO,j) && arrayContains(arrO,k) && arrayContains(arrO,l) && arrayContains(arrO,m))
                                                                                                                        {

                                                                                                                            //System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j + " " + k + " " + l + " " + m + " " + o);

                                                                                                                            for(int p = 0; p < n + 1; p++)
                                                                                                                            {
                                                                                                                                int[] arrP = vertex.getConnVerArray(p);

                                                                                                                                if(arrayContains(arrP,a) && arrayContains(arrP,b) && arrayContains(arrP,c) && arrayContains(arrP,d) && arrayContains(arrP,e) && arrayContains(arrP,f) && arrayContains(arrP,g) && arrayContains(arrP,h) && arrayContains(arrP,i) && arrayContains(arrP,j) && arrayContains(arrP,k) && arrayContains(arrP,l) && arrayContains(arrP,m) && arrayContains(arrP,o))
                                                                                                                                {
                                                                                                                                    System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j + " " + k + " " + l + " " + m + " " + o + " " + p);
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


        }
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

}
