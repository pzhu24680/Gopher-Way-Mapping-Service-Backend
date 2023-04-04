package com.gopherwayrouting.routingapi.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {
    @Autowired
    private GraphRepository graphRepository;
    public List<Edge> getAllEdges(){
        List<Edge>edges = new ArrayList<>();
        graphRepository.findAll().forEach(edges::add);
        return edges;
    }
}
