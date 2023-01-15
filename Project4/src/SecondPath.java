import java.util.*;

public class SecondPath<T> {
    public int calculateSecondPath(Node<T> source, ArrayList<String> flagged) {
        source.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>();
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        int sum = 0;
        int count = 0;
        while (count<flagged.size()) {
            if(unsettledNodes.isEmpty()){
                return -1;
            }
            Node<T> currentNode = unsettledNodes.poll();
            currentNode.zo=0;
            if(flagged.contains(currentNode.getName())) {
                sum += currentNode.getDistance();
                currentNode.setDistance(0);
                settledNodes.add(currentNode);

                count +=1;
                for (Map.Entry<Node<T>, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                    if (!settledNodes.contains(entry.getKey())) {
                        Integer newDistance = currentNode.getDistance() + entry.getValue();
                        if (newDistance < entry.getKey().getDistance()) {
                            entry.getKey().setDistance(newDistance);
                            if(entry.getKey().zo==1) {
                                unsettledNodes.remove(entry.getKey());
                                entry.getKey().zo=0;
                            }
                            unsettledNodes.add(entry.getKey());
                            entry.getKey().zo=1;
                        }
                    }
                }
            }else{
                for (Map.Entry<Node<T>, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                    if (!settledNodes.contains(entry.getKey())) {
                        Integer newDistance = currentNode.getDistance() + entry.getValue();
                        if (newDistance < entry.getKey().getDistance()) {
                            entry.getKey().setDistance(newDistance);
                            if(entry.getKey().zo==1) {
                                unsettledNodes.remove(entry.getKey());
                                entry.getKey().zo=0;
                            }
                            unsettledNodes.add(entry.getKey());
                            entry.getKey().zo=1;
                        }
                    }
                }

            }
        }
        return sum;



    }

}
