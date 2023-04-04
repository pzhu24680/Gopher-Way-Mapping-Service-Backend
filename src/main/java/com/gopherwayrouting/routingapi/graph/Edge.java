package com.gopherwayrouting.routingapi.graph;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Edges")
public class Edge {
    @Id
    private int id;
    private String source;
    private String destination;
    private int distance;

    private String type;
    public Edge(){};
    public Edge(int id, String source, String destination, int distance,String type){
        this.id=id;
        this.source=source;
        this.destination=destination;
        this.distance=distance;
        this.type=type;
    }
    public int getId(){return this.id;}
    public String getSource(){return this.source;}
    public String getDestination(){return this.destination;}
    public int getDistance(){return this.distance;}
    public String getType(){return this.type;}
    public void setId(int id){this.id=id;}
    public void setSource(String source){this.source=source;}
    public void setDestination(String destination){this.destination=destination;}
    public void setDistance(int distance){this.distance=distance;}
    public void setType(String type){this.type=type;}



}
