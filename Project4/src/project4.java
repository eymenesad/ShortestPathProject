import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


public class project4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            br = new BufferedReader(
                    new FileReader(args[0]));
            bw = new BufferedWriter(
                    new FileWriter(args[1]));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int count = 0;
        int nodeCount=0;
        ArrayList<String> flagList = new ArrayList<>();
        ArrayList<Node<String>> nodeArrayList = new ArrayList<>();
        HashMap<String, Node<String>> ne = new HashMap<>();
        String begin ="";
        String end ="";
        String bos = "";
        String line;
        Long tic = System.currentTimeMillis();
        try{
        while((line =br.readLine())!=null){
            String[] tokens = line.split("\s");
            if (count==0){
                bos = tokens[0];
                count +=1;
                continue;
            }
            if (count==1){
                nodeCount = Integer.parseInt(tokens[0]);
            }
            if (count==2){
                begin=tokens[0];
                end = tokens[1];
            }
            if (count == 3){
                for(int i = 0; i<nodeCount;i++){
                    flagList.add(tokens[i]);
                }
            }
            if (count>3) {
                Node<String> node;

                if (!ne.containsKey(tokens[0])) {
                    node = new Node<>(tokens[0]);
                }
                else{
                    node = ne.get(tokens[0]);
                }
                if(!nodeArrayList.contains(node)){
                    nodeArrayList.add(node);
                }

                ne.put(tokens[0], node);
                for (int j = 1; j < tokens.length; j += 2) {
                    Node<String> nodez;
                    if (!ne.containsKey(tokens[j])) {
                        nodez = new Node<>(tokens[j]);
                    }
                    else{
                        nodez = ne.get(tokens[j]);
                    }
                    node.addAdjacentNode(nodez, Integer.parseInt(tokens[j + 1]));
                    nodez.addAdjacentNode(node,Integer.parseInt(tokens[j + 1]));
                    if(!nodeArrayList.contains(nodez)){
                        nodeArrayList.add(nodez);

                    }
                    ne.put(tokens[j], nodez);
                }


            }
            count += 1;
        }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        ShortestPath<String> dijkstra = new ShortestPath<>();
        dijkstra.calculateShortestPath(ne.get(begin));
        bw.write(dijkstra.printPaths(ne.get(end))+"\n");

        for(int i = 0; i<nodeArrayList.size(); i++){
            nodeArrayList.get(i).setDistance(Integer.MAX_VALUE);
        }


        SecondPath<String> second = new SecondPath<>();

        bw.write(second.calculateSecondPath(ne.get(flagList.get(0)), flagList)+"");

        Long toc = System.currentTimeMillis();

        br.close();
        bw.close();






    }
}