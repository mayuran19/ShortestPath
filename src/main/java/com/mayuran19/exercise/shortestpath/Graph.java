package com.mayuran19.exercise.shortestpath;

import java.util.*;

public class Graph {
    public Set<Node> graphNodes = new HashSet<>();
    public Set<Edge> graphEdges = new HashSet<>();

    public void buildGraph(Edge[] edges){
        for(Edge edge : edges){
            graphEdges.add(edge);
            Node source = edge.getSource();
            Node destination = edge.getDestination();

            if(!graphNodes.contains(source)){
                graphNodes.add(source);
            }else{
                source = graphNodes.stream()
                        .filter(node -> node.getName().equals(edge.getSource().getName()))
                        .findFirst().get();
            }
            source.getOutgoingEdges().add(edge);

            if(!graphNodes.contains(destination)){
                graphNodes.add(destination);
            }else{
                destination = graphNodes.stream()
                        .filter(node -> node.getName().equals(edge.getDestination().getName()))
                        .findFirst().get();
            }
            destination.getIncomingEdges().add(edge);
        }
    }

    public List<Edge> getPath(String path){
        List<Edge> edges = new ArrayList<>();
        String[] nodeNames = path.split("-");
        for(int i = 0; i < nodeNames.length - 1; i++){
            Node source = getNodeByName(nodeNames[i]);
            Node destination = getNodeByName(nodeNames[i + 1]);
            Edge edge = getEdgeBySourceAndDestination(source, destination);
            if(edge == null){
                return null;
            }else{
                edges.add(edge);
            }
        }

        return edges;
    }

    public void printDistance(String path){
        List<Edge> edges = getPath(path);
        if(edges == null){
            System.out.println("NO SUCH ROUTE");
        }else{
            int sum = edges.stream().mapToInt(edge -> edge.getWeight()).sum();
            System.out.println(path + ":" + sum);
        }
    }

    public Node getNodeByName(String name){
        return graphNodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .get();
    }

    public Edge getEdgeBySourceAndDestination(Node source, Node destination){
        Optional<Edge> edgeOptional = graphEdges.stream()
                .filter(edge -> edge.equals(new Edge(source, destination, Integer.MAX_VALUE)))
                .findFirst();
        if(edgeOptional.isPresent()){
            return edgeOptional.get();
        }else{
            return null;
        }
    }

    public void buildAllPaths(String source, String destination){
        graphNodes.stream().forEach(node -> node.setVisited(false));
        Node sourceNode = getNodeByName(source);
        Node destinationNode = getNodeByName(destination);

        List<Edge> path = new ArrayList<>();
        Node currentNode = sourceNode;
        Stack<Edge> stack = new Stack<>();
        visitNode(currentNode, destinationNode, stack);
    }

    /**
     * visit the current node and return the next node to visit
     * @param node
     * @return
     */
    public void visitNode(Node source, Node destination, Stack<Edge> stack){
        Set<Edge> outgoingEdges = source.getOutgoingEdges();
        for(Edge edge : outgoingEdges){
            Node edgeDestination = getNodeByName(edge.getDestination().getName());
            stack.add(edge);
            if(edgeDestination.equals(destination)){
                //Reached destination
                stack.stream()
                        .forEach(edge1 -> System.out.println(edge1.getSource().getName() + "->" + edge1.getDestination().getName()));
                stack.pop();
            } else if(source.isVisited()){
                break;
            } else{
                visitNode(edgeDestination, destination, stack);
            }
        }
        source.setVisited(true);
    }

    public static class NodeBuilder{
        private Set<Node> nodes = new HashSet<>();

        public Node buildNode(String name){
            Node node = new Node(name);
            if(nodes.contains(node)){
                return nodes.stream()
                        .filter(node1 -> node1.equals(node))
                        .findFirst().get();
            }else{
                nodes.add(node);
                return node;
            }
        }
    }

    private static class EdgeBuilder{
        private Set<Edge> edges = new HashSet<>();

        public Edge buildEdge(String source, String destination, int weight){
            Edge edge = new Edge(source, destination, weight);
            if(edges.contains(edge)){
                return edges.stream()
                        .filter(edge1 -> edge1.equals(edge))
                        .findFirst().get();
            }else{
                edges.add(edge);
                return edge;
            }
        }
    }
}
