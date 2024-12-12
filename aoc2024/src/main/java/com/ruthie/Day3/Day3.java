package com.ruthie.Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3 {
    public static void main (String[] args) throws IOException {

        int total = 0;
        
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern nums = Pattern.compile("[0-9]{1,3}");
        

        // use regex mul\([0-9]{1,3},[0-9]{1,3})\) and then add to an array
        // pop the array to add the multiplied numbers

        try (BufferedReader reader = new BufferedReader(new FileReader("Day3/input.txt"))) {
            while(reader.ready()) {
                
                String line = reader.readLine();
                Matcher matcher = pattern.matcher(line);
                List<Integer> numsList = new ArrayList<Integer>();

                while (matcher.find()) {
                    String multSet = matcher.group();
                    System.out.println(multSet);

                    Matcher numsMatcher = nums.matcher(multSet);

                    while (numsMatcher.find()) {
                        int num = Integer.parseInt(numsMatcher.group());
                        numsList.add(num);
                    }
                }
                while (numsList.size() > 0) {
                    int num1 = numsList.remove(0);
                    int num2 = numsList.remove(0);

                    total += (num1 * num2);
                }

        }
        System.out.println(total);   
    }
}
}
