package trufanov.natera.test.concurent;

import com.google.testing.threadtester.ThreadedMain;
import com.google.testing.threadtester.ThreadedSecondary;
import org.junit.Test;


public class DirectedGraphAddEdgeTest extends DirectedGraphTest {
    @ThreadedMain
    public void mainThread() {
        add1 = graph.addEdge(1, 2);
    }

    @ThreadedSecondary
    public void secondThread() {
        add2 = graph.addEdge(1, 2);
    }

    @Test
    public void testAddVertex() {
        test();
    }
}