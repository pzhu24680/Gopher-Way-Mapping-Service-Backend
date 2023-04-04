package com.gopherwayrouting.routingapi.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GraphController {
    @Autowired
    GraphService graphService;
    @Autowired
    PathFindingService pathFindingService;
    @GetMapping("/edges")
    public List<Edge> getAllEdges(){
        return graphService.getAllEdges();
    }
    @GetMapping("/shortestpath/{source}/{destination}")
    public List<ArrayList<Object>> shortestPath(@PathVariable String source, @PathVariable String destination){
        return pathFindingService.shortestPath(source,destination);
    }
}
