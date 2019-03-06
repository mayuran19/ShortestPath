package com.mayuran19.exercise.shortestpath;

import com.mayuran19.exercise.shortestpath.algorithm.Edge;
import com.mayuran19.exercise.shortestpath.algorithm.Graph;
import com.mayuran19.exercise.shortestpath.algorithm.Node;
import org.junit.Test;

import java.util.stream.Collectors;

public class GraphTest {

    @Test
    public void buildGraph() {
        Edge edge1 = new Edge("A","B",5);
        Edge edge2 = new Edge("B","C",4);
        Edge edge3 = new Edge("C","D",8);
        Edge edge4 = new Edge("D","C",8);
        Edge edge5 = new Edge("D","E",6);
        Edge edge6 = new Edge("A","D",5);
        Edge edge7 = new Edge("C","E",2);
        Edge edge8 = new Edge("E","B",3);
        Edge edge9 = new Edge("A","E",7);

        Graph graph = new Graph();
        graph.buildGraph(new Edge[]{edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9});

        for(Node node : graph.graphNodes){
            System.out.println("Node: " + node.getName());
            System.out.println("Outgoing edges");
            node.getOutgoingEdges().stream().map(edge -> edge.toString())
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
            System.out.println("Incoming edges");
            node.getIncomingEdges().stream().map(edge -> edge.toString())
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
            System.out.println();
            System.out.println();
            System.out.println("*************");
        }

        graph.printDistance("A-B-C");
        graph.printDistance("A-D");
        graph.printDistance("A-D-C");
        graph.printDistance("A-E-B-C-D");
        graph.printDistance("A-E-D");

        graph.buildAllPaths("C", "C");
    }
}