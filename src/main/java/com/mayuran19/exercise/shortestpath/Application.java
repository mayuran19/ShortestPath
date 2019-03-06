package com.mayuran19.exercise.shortestpath;

import com.mayuran19.exercise.shortestpath.algorithm.Edge;
import com.mayuran19.exercise.shortestpath.algorithm.Graph;
import com.mayuran19.exercise.shortestpath.util.FileInputUtil;
import com.mayuran19.exercise.shortestpath.util.UserInput;

import java.util.*;

/**
 * @author Mayuran Satchithanantham
 */
public class Application {
    private static final String CALCULATE = "1";
    private static final String QUIT = "0";

    public static void main(String[] args) {
        Graph graph = new Graph();

        /*System.out.println("Welcome to PROBLEM ONE: TRAINS");
        System.out.println("Enter 0 to quit");
        System.out.println("Enter 1 to calculate");
        //String text = UserInput.promptInput("Input the data in following format <A,B,5>[Eg: A,B,5]");
        System.out.println();
        System.out.println("Input the data in following format <A,B,5>[Eg: A,B,5]");
        while (true){
            String text = UserInput.promptInput("");
            if(text.equals(QUIT)){
                System.exit(0);
            }else if(text.equals(CALCULATE)){
                graph.buildGraph(edges.toArray(new Edge[edges.size()]));
                break;
            }else{
                String[] edgeValues = UserInput.parseInput(text);
                //System.out.println(Arrays.deepToString(edgeValues));
                if(edgeValues == null){
                    continue;
                }
                Edge edge = new Edge(edgeValues[0], edgeValues[1], Integer.valueOf(edgeValues[2]));
                edges.add(edge);
                System.out.println(edge + " added to graph");
            }
        }

        Edge edge1 = new Edge("A","B",5);
        Edge edge2 = new Edge("B","C",4);
        Edge edge3 = new Edge("C","D",8);
        Edge edge4 = new Edge("D","C",8);
        Edge edge5 = new Edge("D","E",6);
        Edge edge6 = new Edge("A","D",5);
        Edge edge7 = new Edge("C","E",2);
        Edge edge8 = new Edge("E","B",3);
        Edge edge9 = new Edge("A","E",7);*/

        List<Edge> edgeList = FileInputUtil.getEdgesFromFile();
        Edge[] edges = new Edge[edgeList.size()];
        graph.buildGraph(edgeList.toArray(edges));

        graph.printDistance("The distance of the route A-B-C: ", "A-B-C");
        graph.printDistance("The distance of the route A-D: ", "A-D");
        graph.printDistance("The distance of the route A-D-C: ", "A-D-C");
        graph.printDistance("The distance of the route A-E-B-C-D: ", "A-E-B-C-D");
        graph.printDistance("The distance of the route A-E-D: ", "A-E-D");

        //The number of trips starting at C and ending at C with a maximum of 3 stops.
        List<Set<Edge>> paths = graph.getPossiblePaths("C","C");
        long count = paths.stream()
                .filter(p -> graph.getNumberOfNodesForPath(p) <= 3)
                .count();
        System.out.println("The number of trips starting at C and ending at C with a maximum of 3 stops: " + count);

        //The number of trips starting at A and ending at C with exactly 4 stops
        List<Set<Edge>> paths1 = graph.getPossiblePaths("A","C");
        long count1 = paths1.stream()
                .filter(p -> graph.getNumberOfNodesForPath(p) == 4)
                .count();
        System.out.println("The number of trips starting at A and ending at C with exactly 4 stops: " + count1);
    }
}
