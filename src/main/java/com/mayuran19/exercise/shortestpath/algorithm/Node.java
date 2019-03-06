package com.mayuran19.exercise.shortestpath.algorithm;

import java.util.*;

public class Node {
    private String name;
    private boolean isVisited = false;
    private Set<Edge> outgoingEdges = new HashSet<>();
    private Set<Edge> incomingEdges = new HashSet<>();

    public Node(String name) {
        //Ensure name is not null
        assert name != null;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void setOutgoingEdges(Set<Edge> outgoingEdges) {
        this.outgoingEdges = outgoingEdges;
    }

    public Set<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public void setIncomingEdges(Set<Edge> incomingEdges) {
        this.incomingEdges = incomingEdges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
