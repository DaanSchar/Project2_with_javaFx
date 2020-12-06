package graph;

import graph.ColEdge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
  * all handling of reading the graph txt files happens in the graph.ReadGraph class
  * with some small additions
  *
  * @Author DKE Maastricht
  */

public class ReadGraph {

    public final static boolean DEBUG = false;
    public final static String COMMENT = "//";
    //! n is the number of vertices in the graph
    static int n = -1;
    //! m is the number of edges in the graph
    static int m = -1;
    //! e will contain the edges of the graph
    ColEdge e[] = null;
    String inputfile;


    /**
      *
      * this is a constructor. all it does is when you use the line Readgraph nameOfReadGrapObject = new graph.ReadGraph(parameter);,
      * the code inside the constuctor gets excecuted. thats it.
      *
      * @param fileName the name of the desired graph filename we want to read
      */
    public ReadGraph(String fileName)
    {
        inputfile = fileName;
        readGraphMethod();
    }


    /**
      *
      * @return n; total Vertices of the graph file
      */
    public int getFileVertices()
    {
        return n;
    }

    /**
      *
      * @return m; total Edges of the graph file
      */
    public int getFileEdges()
    {
        return m;
    }

    /**
      *
      * @return e; the full graph as a gameVisuals.ColEdge[]
      */
    public ColEdge[] getGraph()
    {
        return e;
    }


    /**
      *  reads the file and generates n, m and e
      */
    private void readGraphMethod() {

            if (inputfile.length() < 1) {
                System.out.println("Error! No filename specified.");
                System.exit(0);
            }



            boolean seen[] = null;

            try {
                FileReader fr = new FileReader(inputfile);
                BufferedReader br = new BufferedReader(fr);

                String record = new String();


                while ((record = br.readLine()) != null) {
                    if (record.startsWith("//")) continue;
                    break;
                }

                if (record.startsWith("VERTICES = ")) {
                    n = Integer.parseInt(record.substring(11));
                    if (DEBUG) System.out.println(COMMENT + " Number of vertices = " + n);
                }

                seen = new boolean[n + 1];

                record = br.readLine();

                if (record.startsWith("EDGES = ")) {
                    m = Integer.parseInt(record.substring(8));
                    if (DEBUG) System.out.println(COMMENT + " Expected number of edges = " + m);
                }

                e = new ColEdge[m];

                for (int d = 0; d < m; d++) {
                    if (DEBUG) System.out.println(COMMENT + " Reading edge " + (d + 1));
                    record = br.readLine();
                    String data[] = record.split(" ");
                    if (data.length != 2) {
                        System.out.println("Error! Malformed edge line: " + record);
                        System.exit(0);
                    }
                    e[d] = new ColEdge();

                    e[d].u = Integer.parseInt(data[0]);
                    e[d].v = Integer.parseInt(data[1]);

                    seen[e[d].u] = true;
                    seen[e[d].v] = true;

                    if (DEBUG) System.out.println(COMMENT + " Edge: " + e[d].u + " " + e[d].v);

                }

                String surplus = br.readLine();
                if (surplus != null) {
                    if (surplus.length() >= 2) if (DEBUG)
                        System.out.println(COMMENT + " gamemodes.Warning: there appeared to be data in your file after the last edge: '" + surplus + "'");
                }

            } catch (IOException ex) {
                // catch possible io errors from readLine()
                System.out.println("Error! Problem reading file " + inputfile);
                System.exit(0);
            }

            for (int x = 1; x <= n; x++) {
                if (seen[x] == false) {
                    if (DEBUG)
                        System.out.println(COMMENT + " gamemodes.Warning: vertex " + x + " didn't appear in any edge : it will be considered a disconnected vertex on its own.");
                }
            }
        }
}
