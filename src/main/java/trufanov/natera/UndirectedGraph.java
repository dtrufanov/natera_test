package trufanov.natera;

import java.util.Set;

public class UndirectedGraph<N> extends BaseGraph<N> {

    public boolean addEdgeInternal(N node1, N node2) {
        Set<N> successors1 = nodeMap.get(node1);
        Set<N> successors2 = nodeMap.get(node2);
        boolean add1 = successors1.add(node2);
        boolean add2 = successors2.add(node1);
        return add1 || add2;
    }

    public boolean isDirected() {
        return false;
    }
}
