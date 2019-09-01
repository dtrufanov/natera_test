package trufanov.natera.test.concurent;

import com.google.testing.threadtester.ThreadedMain;
import com.google.testing.threadtester.ThreadedSecondary;
import org.junit.Test;


public class UndirectedGraphAddVertexTest extends UndirectedGraphTest {
    @ThreadedMain
    public void mainThread() {
        add1 = graph.addVertex(1);
    }

    @ThreadedSecondary
    public void secondThread() {
        add2 = graph.addVertex(1);
    }

    @Test
    public void testAddVertex() {
        test();
    }
}