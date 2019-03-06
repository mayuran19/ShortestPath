package com.mayuran19.exercise.shortestpath.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public Set<Node> graphNodes = new HashSet<>();
    public Set<Edge> graphEdges = new HashSet<>();

    /**
     * Method to build the graph based on provided Edge array
     * @param edges
     */
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

    /**
     * When a path is given as string A-B-C-D, this method will convert it
     * into list of Edge objects in the order
     * @param path
     * @return
     */
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

    /**
     * Print the distance of a path
     * Path string will be for example A-B-C-D-E
     * @param questionString
     * @param path
     */
    public void printDistance(String questionString, String path){
        List<Edge> edges = getPath(path);
        if(edges == null){
            System.out.println(questionString + "NO SUCH ROUTE");
        }else{
            int sum = edges.stream().mapToInt(edge -> edge.getWeight()).sum();
            System.out.println(questionString + sum);
        }
    }

    /**
     * Get the Node object using the node name
     * @param name
     * @return
     */
    public Node getNodeByName(String name){
        return graphNodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .get();
    }

    /**
     * Get the Edge object using source node name and destination node name
     * @param source
     * @param destination
     * @return
     */
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

    /**
     * Build the possible paths from source to destination
     * Currently it just print the paths
     * @param source
     * @param destination
     */
    public List<Set<Edge>> getPossiblePaths(String source, String destination){
        graphNodes.stream().forEach(node -> node.setVisited(false));
        Node sourceNode = getNodeByName(source);
        Node destinationNode = getNodeByName(destination);

        List<Set<Edge>> possiblePaths = new ArrayList<>();
        Node currentNode = sourceNode;
        Stack<Edge> stack = new Stack<>();
        visitNode(currentNode, destinationNode, stack, possiblePaths);

        return possiblePaths;
    }

    /**
     * 1. Visit the source node
     * 2. Get all outgoing edges for source node
     * 3. In loop, follow the each edge from the source node
     * 4. Add the current node to stack
     * 5. If the current node is same as destination, print the path and pop the stack
     * 6. If the current node is marked as visited, break the loop
     * 7. Else recurrsivly visit the nodes
     * @param source
     * @param destination
     * @param stack
     * @return
     */
    public void visitNode(Node source, Node destination, Stack<Edge> stack, List<Set<Edge>> possiblePaths){
        Set<Edge> outgoingEdges = source.getOutgoingEdges();
        for(Edge edge : outgoingEdges){
            if(stack.contains(edge)){
                //If the node has been visited already, don't visit again
                continue;
            }else{
                edge.setVisited(true);
                Node edgeDestination = getNodeByName(edge.getDestination().getName());
                stack.add(edge);
                if(edgeDestination.equals(destination)){
                    //Reached destination, print the full path and pop the node
                    String pathStting = stack.stream().map(edge1 -> edge1.toString())
                            .collect(Collectors.joining("->"));
                    //System.out.println(pathStting);
                    Set<Edge> paths = new HashSet<>();
                    possiblePaths.add(paths);
                    stack.stream().forEach(edge1 -> {
                        paths.add(edge1);
                    });
                    //stack.pop();
                    visitNode(edgeDestination, destination, stack, possiblePaths);
                } else{
                    //Next node, visit recurrsively
                    visitNode(edgeDestination, destination, stack, possiblePaths);
                }
            }
        }

        //Recursion is finished here
        source.getOutgoingEdges().stream().forEach(edge -> edge.setVisited(false));
        if(stack.size() > 0){
            stack.pop();
        }
        //All the edges are visited, marked the node as visited
        source.setVisited(true);
    }

    /**
     * Return the number of nodes in a path
     * @param path
     * @return
     */
    public int getNumberOfNodesForPath(Set<Edge> path){
        Set<Node> nodes = new HashSet<>();
        for(Edge edge : path){
            nodes.add(edge.getSource());
            nodes.add(edge.getDestination());
        }

        return nodes.size();
    }

    //@TODO Since the Node and Edges are fixed, we can use object pooling. To be completed later. Currently not in use
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

    //@TODO Since the Node and Edges are fixed, we can use object pooling. To be completed later. Currently not in use
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
