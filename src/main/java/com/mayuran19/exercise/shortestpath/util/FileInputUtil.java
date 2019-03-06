package com.mayuran19.exercise.shortestpath.util;

import com.mayuran19.exercise.shortestpath.algorithm.Edge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInputUtil {
    public static List<Edge> getEdgesFromFile(){
        List<Edge> edges = new ArrayList<>();
        try {
            Path path = Paths.get(FileInputUtil.class.getClassLoader()
                    .getResource("input.txt").toURI());
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                line = line.replace("<","").replace(">","");
                String[] array = parseInput(line);
                Edge edge1 = new Edge(array[0],array[1],Integer.valueOf(array[2]));
                edges.add(edge1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return edges;
    }

    public static String[] parseInput(String input){
        Pattern pattern = Pattern.compile("[A-Z],[A-Z],[0-999]");
        Matcher matcher = pattern.matcher(input);
        if(!matcher.find()){
            System.out.println("Invalid input. Format should be A,B,9");
            return null;
        }else{
            return input.split(",");
        }
    }
}
