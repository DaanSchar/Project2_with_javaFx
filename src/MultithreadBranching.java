public class MultithreadBranching {
/*
    private static graph.Graph graph;
    private int chromaticNumber;
    private int computations;
    private int totalThreads;

    private branchingThread1 t1;
    private branchingThread2 t2;
    private branchingThread3 t3;
    private branchingThread4 t4;

    private int resultT1;
    private int resultT2;
    private int resultT3;
    private int resultT4;

    public MultithreadBranching(graph.Graph graphObject, int totalComputations) throws InterruptedException {
        totalThreads = 1;
        graph = graphObject;
        computations = totalComputations;

        runThread();

        getAllResults();

        calcMTChromNum();
    }

    public void runThread() throws InterruptedException {
        t1 = new branchingThread1(graph, computations);
        t2 = new branchingThread2(graph, computations);
     //   t3 = new branchingThread3(graph, computations);
      //  t4 = new branchingThread4(graph, computations);

        t1.start();
        t2.start();
      //  t3.start();
       // t4.start();
    }

    public void getAllResults() throws InterruptedException {

            t1.join();
            t2.join();

            resultT1 = t1.getResultT1();
            System.out.println("done with resultT1");
            System.out.println(resultT1);

            resultT2 = t2.getResultT2();
            System.out.println("done with resultT2");
            System.out.println(resultT2);

         //  resultT3 = t3.getResultT3();
         //  resultT4 = t4.getResultT4();
    }

    public void calcMTChromNum() throws InterruptedException {

        int[] allMTchomNumarray = { resultT1};
        chromaticNumber = allMTchomNumarray[0];

        for(int i = 0; i < totalThreads; i++)
        {
            if(allMTchomNumarray[i] < chromaticNumber)
            {
                chromaticNumber = allMTchomNumarray[i];
            }
        }
    }






    public int getMultiThreadedChromNum()
    {
        return chromaticNumber;
    }


}


class branchingThread1 extends Thread
{
    private static int n;
    private static int m;
    private static graph.Graph graph;
    private int chromaticNumberThread;
    private int computations;

    public branchingThread1(graph.Graph graphObject, int totalComputations)
    {
        computations = totalComputations;
        graph = graphObject;
        n = graph.n;
        m = graph.m;
    }

    public void run()
    {
        synchronized (this)
        {
            chromatics.branching.Branching branchingThread = new chromatics.branching.Branching(graph, computations);
            chromaticNumberThread = branchingThread.getBranchingNum();
            System.out.println("DONE RUNNING THREAD 1");
        }

    }

    public int getResultT1() throws InterruptedException {
        synchronized (this)
        {
            wait();
            return chromaticNumberThread;
        }
    }
}



class branchingThread2 extends Thread
{
    private static int n;
    private static int m;
    private static graph.Graph graph;
    private int chromaticNumberThread;
    private int computations;

    public branchingThread2(graph.Graph graphObject, int totalComputations)
    {
        computations = totalComputations;
        graph = graphObject;
        n = graph.n;
        m = graph.m;
    }

    public void run()
    {
        synchronized (this)
        {
            chromatics.branching.Branching branchingThread = new chromatics.branching.Branching(graph, computations);
            chromaticNumberThread = branchingThread.getBranchingNum();
            System.out.println("DONE RUNNING THREAD 2");
        }

    }

    public int getResultT2() throws InterruptedException {
        synchronized (this)
        {
            wait();
            return chromaticNumberThread;
        }
    }
}



class branchingThread3 extends Thread {
    private static int n;
    private static int m;
    private static graph.Graph graph;
    private int chromaticNumberThread;
    private int computations;

    public branchingThread3(graph.Graph graphObject, int totalComputations) {
        computations = totalComputations;
        graph = graphObject;
        n = graph.n;
        m = graph.m;
    }

    public void run() {
        synchronized (this) {
            chromatics.branching.Branching branchingThread = new chromatics.branching.Branching(graph, computations);
            chromaticNumberThread = branchingThread.getBranchingNum();
            System.out.println("DONE RUNNING THREAD 3");
        }

    }

    public int getResultT3() throws InterruptedException {
        synchronized (this) {
            wait();
            return chromaticNumberThread;
        }
    }
}



class branchingThread4 extends Thread
{
    private static int n;
    private static int m;
    private static graph.Graph graph;
    private int chromaticNumberThread;
    private int computations;

    public branchingThread4(graph.Graph graphObject, int totalComputations)
    {
        computations = totalComputations;
        graph = graphObject;
        n = graph.n;
        m = graph.m;
    }

    public void run()
    {
        synchronized (this)
        {
            chromatics.branching.Branching branchingThread = new chromatics.branching.Branching(graph, computations);
            chromaticNumberThread = branchingThread.getBranchingNum();
            System.out.println("DONE RUNNING THREAD 4");
        }

    }

    public int getResultT4() throws InterruptedException {
        synchronized (this)
        {
            wait();
            return chromaticNumberThread;
        }
    }
*/
}
