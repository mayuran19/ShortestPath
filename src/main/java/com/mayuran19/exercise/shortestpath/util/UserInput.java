package com.mayuran19.exercise.shortestpath.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mayuran Satchithanantham
 */
public class UserInput {
    public static String promptInput(String prompt){
        Scanner reader = new Scanner(System.in);
        System.out.println(prompt + ":");
        String text = reader.nextLine();

        return text;
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
