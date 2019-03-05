package com.mayuran19.exercise.shortestpath.model;

import java.util.Objects;

public class Edge {
    private Node source;
    private Node destination;
    private int weight;

    public Edge(Node source, Node destination, int weight) {
        //Ensure source, destination and weight is not null
        assert source != null;
        assert destination != null;
        assert weight >= 0;

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(String source, String destination, int weight) {
        //Ensure source, destination and weight is not null
        assert source != null;
        assert destination != null;
        assert weight >= 0;

        this.source = new Node(source);
        this.destination = new Node(destination);
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(source, edge.source) &&
                Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return source.getName() + "=>" + destination.getName() + "(" + weight + ")";
    }
}
