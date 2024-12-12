package com.ruthie.Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3b {
    public static void main (String[] args) throws IOException {

        int total = 0;
        int dontCount = 0;
        
        Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\))|do\\(\\)|don't\\(\\)");   

        // use regex mul\([0-9]{1,3},[0-9]{1,3})\) and then add to an array
        // part two have to add in or to get do and don't
        // pop the array to add the multiplied numbers using the do and don't as flags

        try (BufferedReader reader = new BufferedReader(new FileReader("Day3/input.txt"))) {
            while(reader.ready()) {
                
                String line = reader.readLine();
                Matcher matcher = pattern.matcher(line);
                Boolean flag = true;
                List<String> filteredList = new ArrayList<String>();
                Pattern numPattern = Pattern.compile("[0-9]{1,3}");

                while (matcher.find()) {
                    String multSet = matcher.group();
                    filteredList.add(multSet);
                }

                while (filteredList.size() > 0) {
                    String item = filteredList.remove(0);
                    if (item.equals("do()") || item.equals("don't()")) System.out.println(item);
                    if (item.equals("don't()")) {
                        flag = false;
                        dontCount+=1;
                    }
                    else if (item.equals("do()")) flag = true;
                    else if (flag == true && item.charAt(0) == 'm') {
                        List<Integer> numSet = new ArrayList<Integer>();

                        Matcher numMatcher = numPattern.matcher(item);
                        while (numMatcher.find()) {
                            int num = Integer.parseInt(numMatcher.group());
                            numSet.add(num);
                            System.out.println("adding num " + num);
                        }

                        int num1 = numSet.get(0);
                        int num2 = numSet.get(1);
                        System.out.println("adding to total " + num1 * num2);
                        int mult = num1 * num2;
                        total += mult;
                        System.out.println(total);
                    }
                }
        }
        System.out.println(total);   
        System.out.println(dontCount);
    }
}
}
