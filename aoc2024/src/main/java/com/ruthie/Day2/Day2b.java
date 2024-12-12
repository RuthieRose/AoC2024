package com.ruthie.Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2b {
    public static void main (String[] args) throws IOException {
        int count = 0;

        // how many lines are "safe"?
        // numbers have to be all increasing or all decreasing
        // difference has to be >= 1 and <= to 3
        // this time one level can be removed and still be considered safe
        // brute force would be trying each line with each level removed if it is considered unsafe
        

        try (BufferedReader reader = new BufferedReader(new FileReader("Day2/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String[] workingLine = line.split(" ");

            Boolean result = checkLine(workingLine);

            if (result) count+=1;
            
            else {
                int minChanges = directionFilter(workingLine);

                if (minChanges < 2) {
                    int l = workingLine.length;
                    int j = 0;
                    while (j < l) {
                        String[] levelList = new String[l-1];

                        int n = 0;
                        for (int k = 0; k < l; k++) {
                            if (k != j) {
                                levelList[n] = workingLine[k];
                                System.out.println(levelList[n]);
                                n+=1;
                            }
                        }
                        System.out.println("Start of levellist");
                        
                        System.out.println("end of levellist");

                        j+=1;

                        Boolean safeWithOneLevel = checkLine(levelList);

                        if (safeWithOneLevel) {
                            count+=1;
                            break;
                        }

                    }

                }
            }
        }
        System.out.println(count);   
    }
}

    public static int directionFilter (String[] workingLine) {
        int increasingCount = 0;
        int decreasingCount = 0;

        for (int i = 0; i < workingLine.length - 1; i++) {

            int num = Integer.parseInt(workingLine[i]);
            int next_num = Integer.parseInt(workingLine[i+1]);

            if (next_num > num) increasingCount+=1;   
                            
            if (num > next_num) decreasingCount+=1;
        }

        return Math.min(increasingCount,decreasingCount);
    }

    public static Boolean checkLine (String[] workingLine) {
        String direction = "";
        String dec = "dec";
        String inc = "inc";

        int first_num = Integer.parseInt(workingLine[0]);
        int second_num = Integer.parseInt(workingLine[1]);

        Boolean flag = true;

        if (first_num < second_num) direction = inc;
        if (first_num > second_num) direction = dec;

        for (int i = 0; i < workingLine.length - 1; i++) {

        int num = Integer.parseInt(workingLine[i]);
        int next_num = Integer.parseInt(workingLine[i+1]);
        int diff = Math.abs(next_num - num);

        if (diff > 3 || diff < 1) flag = false;

        if (next_num > num && direction == dec) flag = false;   
                            
        if (num > next_num && direction == inc) flag = false;

        }
        return flag;
    }
}
