package trufanov.natera;

import java.util.Set;

public class UndirectedGraph<N> extends BaseGraph<N> {

    public boolean addEdge(N node1, N node2) {
        addVertex(node1);
        addVertex(node2);
        Set<N> successors1 = nodeMap.get(node1);
        Set<N> successors2 = nodeMap.get(node2);
        return successors1.add(node2) || successors2.add(node1);
    }

    public boolean isDirected() {
        return false;
    }
}
