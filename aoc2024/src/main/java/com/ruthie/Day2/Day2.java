package com.ruthie.Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Day2 {
    public static void main (String[] args) throws IOException {
        int count = 0;

        // how many lines are "safe"?
        // numbers have to be all increasing or all decreasing
        // difference has to be >= 1 and <= to 3

        try (BufferedReader reader = new BufferedReader(new FileReader("Day2/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String[] workingLine = line.split(" ");
                
                String direction = "";
                String dec = "dec";
                String inc = "inc";

                int first_num = Integer.parseInt(workingLine[0]);
                int second_num = Integer.parseInt(workingLine[1]);

                if (first_num < second_num) direction = inc;
                if (first_num > second_num) direction = dec;

                for (int i = 0; i < workingLine.length - 1; i++) {

                    int num = Integer.parseInt(workingLine[i]);
                    int next_num = Integer.parseInt(workingLine[i+1]);
                    int diff = Math.abs(next_num - num);
                    if (diff > 3 || diff < 1) break;

                    if (next_num > num && direction == dec) break;   
                    
                    if (num > next_num && direction == inc) break;

                    if (i == workingLine.length - 2) count+=1;                    
                }
        }
        System.out.println(count);   
    }
}
}
