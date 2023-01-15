import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShortestPath<T>   {
    public void calculateShortestPath(Node<T> source) {
        source.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>();
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node<T> currentNode = unsettledNodes.poll();
            currentNode.ho=0;
            for (Map.Entry<Node<T>, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                if (!settledNodes.contains(entry.getKey())) {
                    //evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                    Integer newDistance = currentNode.getDistance() + entry.getValue();
                    if (newDistance < entry.getKey().getDistance()) {
                        entry.getKey().setDistance(newDistance);
                        if(entry.getKey().ho==1) {
                            unsettledNodes.remove(entry.getKey());
                            entry.getKey().ho=0;
                        }
                        unsettledNodes.add(entry.getKey());
                        entry.getKey().ho=1;
                    }
                }
            }
            settledNodes.add(currentNode);
        }

    }

    public int printPaths(Node<T> node) {
        if(node.getDistance()!=Integer.MAX_VALUE) {
             return node.getDistance();
        }else {
            return -1;
        }
    }
}

