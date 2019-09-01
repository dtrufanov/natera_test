package trufanov.natera;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.*;

public abstract class Edge<N> {
    private final N node1;
    private final N node2;

    public Edge(N node1, N node2) {
        this.node1 = checkNotNull(node1);
        this.node2 = checkNotNull(node2);
    }

    public static <N> Edge<N> ordered(N source, N target) {
        return new Edge.Ordered<>(source, target);
    }

    public static <N> Edge<N> unordered(N nodeU, N nodeV) {
        return new Edge.Unordered<>(nodeV, nodeU);
    }

    public N node1() {
        return node1;
    }

    public N node2() {
        return node2;
    }

    public abstract boolean isOrdered();

    private static final class Ordered<N> extends Edge<N> {
        private Ordered(N node1, N node2) {
            super(node1, node2);
        }

        @Override
        public boolean isOrdered() {
            return true;
        }

        @Override
        public int hashCode() {
            return node1().hashCode() + node2().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (!(obj instanceof Edge)) {
                return false;
            } else {
                Edge<?> other = (Edge)obj;
                if (this.isOrdered() != other.isOrdered()) {
                    return false;
                } else {
                    return this.node1().equals(other.node1) && this.node2().equals(other.node2);
                }
            }
        }

        @Override
        public String toString() {
            return "{" + node1() + " -> " + node2() + "}";
        }
    }

    private static final class Unordered<N> extends Edge<N> {
        private Unordered(N node1, N node2) {
            super(node1, node2);
        }

        @Override
        public boolean isOrdered() {
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(node1(), node2());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (!(obj instanceof Edge)) {
                return false;
            } else {
                Edge<?> other = (Edge)obj;
                if (this.isOrdered() != other.isOrdered()) {
                    return false;
                } else {
                    return this.node1().equals(other.node1) && this.node2().equals(other.node2) ||
                           this.node1().equals(other.node2) && this.node2().equals(other.node1);
                }
            }
        }

        @Override
        public String toString() {
            return "[" + node1() + ", " + node2() + "]";
        }
    }
}
