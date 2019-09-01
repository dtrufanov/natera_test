package trufanov.natera;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseGraph<N> implements Graph<N> {

    protected Map<N, Set<N>> nodeMap = new HashMap<>();

    public boolean addVertex(N node) {
        checkNotNull(node);
        if (nodeMap.containsKey(node)) {
            return false;
        }
        nodeMap.put(node, new HashSet<>());
        return true;
    }

    public List<Edge<N>> getPath(N from, N to) {
        if (!nodeMap.containsKey(from) || !nodeMap.containsKey(to)) {
            return Collections.emptyList();
        }
        List<N> nodes = getNodesPath(from, to);

        return nodesToEdges(nodes);
    }

    private List<N> getNodesPath(N from, N to) {
        LinkedList<N> path = new LinkedList<>();
        LinkedList<Set<N>> visited = new LinkedList<>();

        path.add(from);
        visited.add(Sets.newHashSet(from));
        while (!path.isEmpty()) {
            N current = path.getLast();
            Set<N> successors = nodeMap.get(current);
            if (successors.contains(to)) {
                path.add(to);
                break;
            }
            Optional<N> next = successors.stream().filter(n -> !visited.getLast().contains(n)).findAny();
            if (next.isPresent()) {
                HashSet<N> nextLevel = new HashSet<>(visited.getLast());
                nextLevel.addAll(successors);
                path.add(next.get());
                visited.add(nextLevel);
            } else {
                visited.forEach(s -> s.add(current));
                path.removeLast();
                visited.removeLast();
            }
        }
        return path;
    }

    private List<Edge<N>> nodesToEdges(List<N> nodes) {
        if (nodes.isEmpty()) {
            return Collections.emptyList();
        }
        N prev = null;
        List<Edge<N>> edges = new ArrayList<>();
        for (N node : nodes) {
            if (prev != null) {
                edges.add(isDirected() ? Edge.ordered(prev, node) : Edge.unordered(prev, node));
            }
            prev = node;
        }
        return edges;
    }
}
