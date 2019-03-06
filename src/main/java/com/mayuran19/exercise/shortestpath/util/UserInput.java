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
        String text = null;
        if(prompt == null || prompt.equals("")){
            text = reader.nextLine();
        }else{
            System.out.println(prompt);
            text = reader.nextLine();
        }

        return text;
    }


}
