package com.mayuran19.exercise.shortestpath;

import com.mayuran19.exercise.shortestpath.algorithm.Edge;
import com.mayuran19.exercise.shortestpath.algorithm.Graph;
import com.mayuran19.exercise.shortestpath.util.UserInput;

import java.util.*;

/**
 * @author Mayuran Satchithanantham
 */
public class Application {
    private static final String CALCULATE = "CALCULATE";
    private static final String QUIT = "QUIT";

    public static void main(String[] args) {
        Set<Edge> edges = new HashSet<>();
        Graph graph = new Graph();

        System.out.println("Welcome to PROBLEM ONE: TRAINS");
        boolean validInput = true;
        String text = UserInput.promptInput("Input the data in following format <A,B,5>[Eg: A,B,5]");
        while (validInput){
            text = UserInput.promptInput("");
            if(text.equals(QUIT)){
                System.exit(0);
            }else if(text.equals(CALCULATE)){
                graph.buildGraph(edges.toArray(new Edge[edges.size()]));
            }else{
                String[] edgeValues = UserInput.parseInput(text);
                if(edgeValues == null){
                    continue;
                }
                Edge edge = new Edge(edgeValues[0], edgeValues[1], Integer.valueOf(edgeValues[2]));
                edges.add(edge);
            }
        }
    }
}
