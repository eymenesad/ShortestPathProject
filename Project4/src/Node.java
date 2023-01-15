import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node<T> implements Comparable<Node<T>> {
    private final T name;
    public Integer zo = 0;
    public Integer ho = 0;
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();

    public Node(T name) {
        this.name = name;
    }

    public void addAdjacentNode(Node<T> node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public T getName() {
        return this.name;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public Map<Node<T>, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }


    public void setAdjacentNodes(Map<Node<T>, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
