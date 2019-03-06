package com.mayuran19.exercise.shortestpath;

import com.mayuran19.exercise.shortestpath.algorithm.Edge;
import com.mayuran19.exercise.shortestpath.algorithm.Graph;
import com.mayuran19.exercise.shortestpath.util.FileInputUtil;
import com.mayuran19.exercise.shortestpath.util.UserInput;

import java.util.*;
import java.util.stream.Collectors;

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

        System.out.println();
        System.out.println("****************************");
        System.out.println("Result");
        System.out.println("****************************");

        graph.printDistance("1. The distance of the route A-B-C: ", "A-B-C");
        graph.printDistance("2. The distance of the route A-D: ", "A-D");
        graph.printDistance("3. The distance of the route A-D-C: ", "A-D-C");
        graph.printDistance("4. The distance of the route A-E-B-C-D: ", "A-E-B-C-D");
        graph.printDistance("5. The distance of the route A-E-D: ", "A-E-D");

        //The number of trips starting at C and ending at C with a maximum of 3 stops.
        List<Set<Edge>> paths = graph.getPossiblePaths("C","C");
        long count = paths.stream()
                .filter(p -> graph.getNumberOfNodesForPath(p) <= 3)
                .count();
        System.out.println("6. The number of trips starting at C and ending at C with a maximum of 3 stops: " + count);

        //The number of trips starting at A and ending at C with exactly 4 stops
        List<Set<Edge>> paths1 = graph.getPossiblePaths("A","C");
        long count1 = paths1.stream()
                .filter(p -> graph.getNumberOfNodesForPath(p) == 4)
                .count();
        System.out.println("7. The number of trips starting at A and ending at C with exactly 4 stops: " + count1);

        //The length of the shortest route (in terms of distance to travel) from A to C.
        int min =paths1.stream()
                .map(edgeSet -> edgeSet.stream()
                        .mapToInt(edge -> edge.getWeight())
                        .sum()
                ).min((o1, o2) -> o1.compareTo(o2))
                .get();
        System.out.println("8. The length of the shortest route (in terms of distance to travel) from A to C: " + min);

        //The length of the shortest route (in terms of distance to travel) from B to B.
        List<Set<Edge>> paths2 = graph.getPossiblePaths("B","B");
        int min2 =paths2.stream()
                .map(edgeSet -> edgeSet.stream()
                        .mapToInt(edge -> edge.getWeight())
                        .sum()
                ).min((o1, o2) -> o1.compareTo(o2))
                .get();
        System.out.println("9. The length of the shortest route (in terms of distance to travel) from B to B: " + min2);

        //The number of different routes from C to C with a distance of less than 50. E.g. CDC, CEBC are both trips of less than 30
        List<Set<Edge>> path30 = paths.stream().filter(path -> path.stream().mapToInt(value -> value.getWeight()).sum() < 50)
                .collect(Collectors.toList());
        List<String> less30Paths = new ArrayList<>();
        for(Set<Edge> path : path30){
            String pathStting = path.stream().map(edge1 -> edge1.toString())
                    .collect(Collectors.joining("->"));
            less30Paths.add(pathStting);
        }
        String s = less30Paths.stream().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("10. The number of different routes from C to C with a distance of less than 50. E.g. CDC, CEBC are both trips of less than 30: \n" + s);
    }
}
