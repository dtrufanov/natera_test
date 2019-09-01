package trufanov.natera.test.concurent;

import com.google.testing.threadtester.ThreadedBefore;
import trufanov.natera.UndirectedGraph;

public abstract class UndirectedGraphTest extends BaseGraphTest {
    @ThreadedBefore
    public void before() {
        graph = new UndirectedGraph<>();
    }
}
