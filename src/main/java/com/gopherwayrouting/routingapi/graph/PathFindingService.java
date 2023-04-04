package com.gopherwayrouting.routingapi.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PathFindingService {
    @Autowired
    private GraphService graphService;
    private Map<String,ArrayList<ArrayList<Object>>> graph;
    public PathFindingService(GraphService graphService) {
        this.graphService=graphService;
        this.graph=new HashMap<String,ArrayList<ArrayList<Object>>>();
        List<Edge> data = graphService.getAllEdges();
        for(Edge edge:data){
            String source=edge.getSource();
            String destination=edge.getDestination();
            int distance=edge.getDistance();
            String type=edge.getType();
            if(!graph.containsKey(source)){
                graph.put(source,new ArrayList<ArrayList<Object>>());
            }
            if(!graph.containsKey(destination)){
                graph.put(destination,new ArrayList<ArrayList<Object>>());
            }
            graph.get(destination).add(new ArrayList<Object>(){{add(source);add(distance);add(type);}});
            graph.get(source).add(new ArrayList<Object>(){{add(destination);add(distance);add(type);}});
        }
    }
    public List<ArrayList<Object>> shortestPath(String source, String destination){
        HashSet<String> seen=new HashSet<String>();
        Map<String,ArrayList<Object>> distances=new HashMap<String,ArrayList<Object>>();
        PriorityQueue<ArrayList<Object>> minheap= new PriorityQueue<ArrayList<Object>>(new Comparator<ArrayList<Object>>() {
            @Override
            public int compare(ArrayList<Object> list1, ArrayList<Object> list2) {
                Integer num1 = (Integer) list1.get(0);
                Integer num2 = (Integer) list2.get(0);
                return num1.compareTo(num2);
            }
        });
        minheap.add(new ArrayList<Object>(){{add(0);add(source);add(null);add(null);}});
        while(minheap.size()!=0) {
            int time = (int) minheap.peek().get(0);
            String node = (String) minheap.peek().get(1);
            String prevNode = (String) minheap.peek().get(2);
            String type=(String) minheap.peek().get(3);
            minheap.poll();
            if (seen.contains(node)) {
                continue;
            }
            seen.add(node);
            distances.put(node, new ArrayList<Object>() {{
                add(time);
                add(prevNode);
                add(type);
            }});
            for (ArrayList<Object> edge : graph.get(node)) {
                minheap.add(new ArrayList<Object>() {{
                    add(time + (int) edge.get(1));
                    add(edge.get(0));
                    add(node);
                    add(edge.get(2));
                }});
            }
        }
        ArrayList<ArrayList<Object>> path=new ArrayList<ArrayList<Object>>();
        String curr=destination;
        while(!curr.equals(source)){
            path.add(distances.get(curr));
            curr=(String)distances.get(curr).get(1);
        }
//        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
