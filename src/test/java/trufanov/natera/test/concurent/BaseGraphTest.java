package trufanov.natera.test.concurent;

import com.google.testing.threadtester.AnnotatedTestRunner;
import com.google.testing.threadtester.MethodOption;
import com.google.testing.threadtester.ThreadedAfter;
import trufanov.natera.BaseGraph;

import static org.junit.Assert.assertTrue;

public abstract class BaseGraphTest {
    protected BaseGraph<Integer> graph;
    protected boolean add1;
    protected boolean add2;

    @ThreadedAfter
    public void after() {
        assertTrue(add1 ^ add2);
    }

    protected void test() {
        AnnotatedTestRunner runner = new AnnotatedTestRunner();
        runner.setMethodOption(MethodOption.ALL_METHODS, null);
        runner.runTests(this.getClass(), BaseGraph.class);
    }
}