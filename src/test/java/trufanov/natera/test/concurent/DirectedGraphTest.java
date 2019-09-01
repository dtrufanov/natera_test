package trufanov.natera.test.concurent;

import com.google.testing.threadtester.ThreadedBefore;
import trufanov.natera.DirectedGraph;

public abstract class DirectedGraphTest extends BaseGraphTest  {
    @ThreadedBefore
    public void before() {
        graph = new DirectedGraph<>();
    }
}