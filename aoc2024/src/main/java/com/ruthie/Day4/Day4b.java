package com.ruthie.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
 * A char is in the middle 
 * AND
 * (lower left is M with upper right S) OR (lower left is S with upper right M)
 * AND 
 * (upper left is S with lower right M) OR (upper left is M with lower right S)
 * 
*/

/*
 * matching grid conditions
 * 
 * A: x_y
 * AND
 * (M: x-1_y+1 AND S: x+1_y-1) OR (S: x-1_y+1 AND M: x+1_y-1)
 * AND
 * (S: x-1_y-1 and M: x+1_y+1) OR (M: x-1_y-1 AND S: x+1_y+1)
 * 
 */


public class Day4b {
    public static void main (String[] args) throws IOException {

        int y = -1;

        Map<String, List<String>> letterCoordinates = new HashMap<String, List<String>>();

            try (BufferedReader reader = new BufferedReader(new FileReader("Day4/input.txt"))) {
            while(reader.ready()) {

                // i = x
                // y is global to keep grid count. starts at negative one so there is not an issue with the first line
                y += 1;
                // System.out.println("Checking to see where y is at: " + y);
                String line = reader.readLine();
                String[] letters = line.split("");

                // setup location of each letter into a map
                for (int i = 0; i < letters.length; i++) {

                    String coordinate = String.valueOf(i) + "_" + String.valueOf(y);

                    String letter = letters[i];

                    if (letter.equals("M")) {
                        letterCoordinates.computeIfAbsent("M", k -> new ArrayList<String>()).add(coordinate);
                    }
                    if (letter.equals("A")) {
                        letterCoordinates.computeIfAbsent("A", k -> new ArrayList<String>()).add(coordinate);
                    }
                    if (letter.equals("S")) {
                        letterCoordinates.computeIfAbsent("S", k -> new ArrayList<String>()).add(coordinate);
                    }

                }
            }

        }

        int MAScount = getCount(letterCoordinates);
        System.out.println(MAScount);
    }

 public static int getCount(Map<String, List<String>> coordinates) {

    int total = 0;

    List<String> A_coordinates = coordinates.get("A");
    for (int i = 0; i < A_coordinates.size(); i++) {
        String A = (String) A_coordinates.get(i);
        String[] A_coord = A.split("_");
        int A_x = Integer.parseInt(A_coord[0]);
        int A_y = Integer.parseInt(A_coord[1]);

        int ULX = A_x - 1;
        int ULY = A_y - 1;
        int LLX = A_x - 1;
        int LLY = A_y + 1;
        int URX = A_x + 1;
        int URY = A_y - 1;
        int LRX = A_x + 1;
        int LRY = A_y + 1;

        String upperLeftCoordinates = String.valueOf(ULX) + "_" + String.valueOf(ULY);
        String lowerLeftCoordinates = String.valueOf(LLX) + "_" + String.valueOf(LLY);
        String upperRightCoordinates = String.valueOf(URX) + "_" + String.valueOf(URY);
        String lowerRightCoordinates = String.valueOf(LRX) + "_" + String.valueOf(LRY);

        String upperLeft = "";
        String lowerLeft = "";
        String upperRight = "";
        String lowerRight = "";

        /* for (String l: letterCoordinates.keySet()) {
            System.out.println("Current key: " + l);
            for (String k: letterCoordinates.get(l)) {
                System.out.println(k.toString());
                }
            }    
        */

        for (String letter: coordinates.keySet()) {
            for (String coordinate_set: coordinates.get(letter)) {
                if (coordinate_set.equals(upperLeftCoordinates)) upperLeft = letter;
                if (coordinate_set.equals(lowerLeftCoordinates)) lowerLeft = letter;
                if (coordinate_set.equals(upperRightCoordinates)) upperRight = letter;
                if (coordinate_set.equals(lowerRightCoordinates)) lowerRight = letter;
            }
        }

        /*
        * matching grid conditions
        * 
        * A: x_y
        * AND
        * (M: x-1_y+1 AND S: x+1_y-1) OR (S: x-1_y+1 AND M: x+1_y-1)
        * AND
        * (S: x-1_y-1 and M: x+1_y+1) OR (M: x-1_y-1 AND S: x+1_y+1)
        * 
        */

        if ((upperLeft.equals("M") && lowerRight.equals("S")) || (upperLeft.equals("S") && lowerRight.equals("M"))) {
            if ((lowerLeft.equals("M") && upperRight.equals("S")) || (lowerLeft.equals("S") && upperRight.equals("M"))) {
                total += 1;
            }
        }
    }

    return total;
 }

}
