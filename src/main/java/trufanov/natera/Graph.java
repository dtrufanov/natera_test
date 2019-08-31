package trufanov.natera;

import java.util.List;

public interface Graph<N> {
    boolean addVertex(N node);
    boolean addEdge(N node1, N node2);
    List<Edge<N>> getPath(N from, N to);
    boolean isDirected();
}
