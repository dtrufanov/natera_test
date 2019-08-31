package trufanov.natera;

import java.util.Set;

public class DirectedGraph<N> extends BaseGraph<N> {

    public boolean addEdge(N node1, N node2) {
        addVertex(node1);
        addVertex(node2);
        Set<N> successors = nodeMap.get(node1);
        return successors.add(node2);
    }

    public boolean isDirected() {
        return true;
    }
}
