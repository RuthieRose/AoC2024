package com.ruthie.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* sample: 
 * horizontal 3
 * horizontal backwards 2
 * vertical 1
 * vertical backwards 2
 * diagonal right up 4
 * diagonal left up 4
 * diagonal right down 1
 * diagonal left down 1
*/

/* matching grid conditions: 
 * horizontal: x: x_y, m: x+1_y, a: x+2_y, s: x+3_y
 * horizontal backwards: s: x_y, a: x+1_y, m: x+2_y, x: x+3_y
 * vertical: x: x_y m: x_y+1 a: x_y+2 s: x_y+3
 * vertical backwards: s: x_y a: x_y+1 m: x_y+2 x: x_y+3
 * diagonal right up: s: x_y a: x-1_y+1 m: x-2_y+2 x: x-3_y+3
 * diagonal left up: s: x_y a: x+1_y+1 m: x+2_y+2 x: x+2_y+2
 * diagonal right down: x: x_y m: x+1_y+1 a: x+2_y+2 s: x+2_y+2
 * diagonal left down: x: x_y m: x-1_y+1 a: x-2_y+2 s: x-3_y+3
 */

 // get all of the letters tracked as to where they are on the grid

public class Day4 {
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

                    if (letter.equals("X")) {
                        letterCoordinates.computeIfAbsent("X", k -> new ArrayList<String>()).add(coordinate);
                    }
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
        /* for (String l: letterCoordinates.keySet()) {
            System.out.println("Current key: " + l);
            for (String k: letterCoordinates.get(l)) {
                System.out.println(k.toString());
                }
            }    
        */
        int hCount = horizontalCount(letterCoordinates);
        int hbCount = horizontalBackwardsCount(letterCoordinates);
        int vCount = verticalCount(letterCoordinates);
        int vbCount = verticalBackwardsCount(letterCoordinates);
        int druCount = diagonalRightUp(letterCoordinates);
        int dluCount = diagonalLeftUp(letterCoordinates);
        int drdCount = diagonalRightDown(letterCoordinates);
        int dldCount = diagonalLeftDown(letterCoordinates);

        System.out.println(hCount + " " + hbCount + " " + vCount + " " + vbCount);   
        System.out.println(druCount + " " + dluCount + " " + drdCount + " " + dldCount);
        System.out.println(hCount + hbCount + vCount + vbCount + druCount + dluCount + drdCount + dldCount);
        }
    }

/* matching grid conditions: 
 * horizontal: x: x_y, m: x+1_y, a: x+2_y, s: x+3_y
 * horizontal backwards: s: x_y, a: x+1_y, m: x+2_y, x: x+3_y
 * vertical: x: x_y m: x_y+1 a: x_y+2 s: x_y+3
 * vertical backwards: s: x_y a: x_y+1 m: x_y+2 x: x_y+3
 * diagonal right up: s: x_y a: x-1_y+1 m: x-2_y+2 x: x-3_y+3
 * diagonal left up: s: x_y a: x+1_y+1 m: x+2_y+2 x: x+2_y+2
 * diagonal right down: x: x_y m: x+1_y+1 a: x+2_y+2 s: x+2_y+2
 * diagonal left down: x: x_y m: x-1_y+1 a: x-2_y+2 s: x-3_y+3
 */

 public static int horizontalCount(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> X_coordinates = coordinates.get("X");
    for (int i = 0; i < X_coordinates.size(); i++) {
        String X = (String) X_coordinates.get(i);
        String[] x_coord = X.split("_");
        int X_x = Integer.parseInt(x_coord[0]);
        int X_y = Integer.parseInt(x_coord[1]);

        int Target_M_x = X_x + 1;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(X_y);

        int Target_A_x = X_x + 2;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(X_y);

        int Target_S_x = X_x + 3;
        String Target_S = String.valueOf(Target_S_x) + "_" + String.valueOf(X_y);

        if (coordinates.get("M").contains(Target_M) && coordinates.get("A").contains(Target_A) && coordinates.get("S").contains(Target_S)) total+=1;
    }

    return total;
 }

 public static int horizontalBackwardsCount(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> S_coordinates = coordinates.get("S");
    for (int i = 0; i < S_coordinates.size(); i++) {
        String S = (String) S_coordinates.get(i);
        String[] s_coord = S.split("_");
        int S_x = Integer.parseInt(s_coord[0]);
        int S_y = Integer.parseInt(s_coord[1]);

        int Target_A_x = S_x + 1;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(S_y);

        int Target_M_x = S_x + 2;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(S_y);

        int Target_X_x = S_x + 3;
        String Target_X = String.valueOf(Target_X_x) + "_" + String.valueOf(S_y);

        if (coordinates.get("A").contains(Target_A) && coordinates.get("M").contains(Target_M) && coordinates.get("X").contains(Target_X)) total+=1;
    }

    return total;
 }

 public static int verticalCount(Map<String, List<String>> coordinates) {

        int total = 0;
        List<String> X_coordinates = coordinates.get("X");
        for (int i = 0; i < X_coordinates.size(); i++) {
            String X = (String) X_coordinates.get(i);
            String[] x_coord = X.split("_");
            int X_x = Integer.parseInt(x_coord[0]);
            int X_y = Integer.parseInt(x_coord[1]);

            int Target_M_y = X_y + 1;
            String Target_M = String.valueOf(X_x) + "_" + String.valueOf(Target_M_y);

            int Target_A_y = X_y + 2;
            String Target_A = String.valueOf(X_x) + "_" + String.valueOf(Target_A_y);

            int Target_S_y = X_y + 3;
            String Target_S = String.valueOf(X_x) + "_" + String.valueOf(Target_S_y);

            if (coordinates.get("M").contains(Target_M) && coordinates.get("A").contains(Target_A) && coordinates.get("S").contains(Target_S)) total+=1;
        }
        return total;
    }

public static int verticalBackwardsCount(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> S_coordinates = coordinates.get("S");
    for (int i = 0; i < S_coordinates.size(); i++) {
        String S = (String) S_coordinates.get(i);
        String[] s_coord = S.split("_");
        int S_x = Integer.parseInt(s_coord[0]);
        int S_y = Integer.parseInt(s_coord[1]);

        int Target_A_y = S_y + 1;
        String Target_A = String.valueOf(S_x) + "_" + String.valueOf(Target_A_y);

        int Target_M_y = S_y + 2;
        String Target_M = String.valueOf(S_x) + "_" + String.valueOf(Target_M_y);

        int Target_X_y = S_y + 3;
        String Target_X = String.valueOf(S_x) + "_" + String.valueOf(Target_X_y);

        if (coordinates.get("A").contains(Target_A) && coordinates.get("M").contains(Target_M) && coordinates.get("X").contains(Target_X)) total+=1;
    }

    return total;
 }  

 public static int diagonalRightUp(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> S_coordinates = coordinates.get("S");
    for (int i = 0; i < S_coordinates.size(); i++) {
        String S = (String) S_coordinates.get(i);
        String[] s_coord = S.split("_");
        int S_x = Integer.parseInt(s_coord[0]);
        int S_y = Integer.parseInt(s_coord[1]);

        int Target_A_x = S_x - 1; 
        int Target_A_y = S_y + 1;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(Target_A_y);

        int Target_M_x = S_x - 2;
        int Target_M_y = S_y + 2;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(Target_M_y);

        int Target_X_x = S_x - 3;
        int Target_X_y = S_y + 3;
        String Target_X = String.valueOf(Target_X_x) + "_" + String.valueOf(Target_X_y);

        if (coordinates.get("A").contains(Target_A) && coordinates.get("M").contains(Target_M) && coordinates.get("X").contains(Target_X)) total+=1;
    }

    return total;
 }
 
 public static int diagonalLeftUp(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> S_coordinates = coordinates.get("S");
    for (int i = 0; i < S_coordinates.size(); i++) {
        String S = (String) S_coordinates.get(i);
        String[] s_coord = S.split("_");
        int S_x = Integer.parseInt(s_coord[0]);
        int S_y = Integer.parseInt(s_coord[1]);

        int Target_A_x = S_x + 1; 
        int Target_A_y = S_y + 1;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(Target_A_y);

        int Target_M_x = S_x + 2;
        int Target_M_y = S_y + 2;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(Target_M_y);

        int Target_X_x = S_x + 3;
        int Target_X_y = S_y + 3;
        String Target_X = String.valueOf(Target_X_x) + "_" + String.valueOf(Target_X_y);

        if (coordinates.get("A").contains(Target_A) && coordinates.get("M").contains(Target_M) && coordinates.get("X").contains(Target_X)) total+=1;
    }

    return total;
 }  
 
 public static int diagonalRightDown(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> X_coordinates = coordinates.get("X");
    for (int i = 0; i < X_coordinates.size(); i++) {
        String X = (String) X_coordinates.get(i);
        String[] x_coord = X.split("_");
        int X_x = Integer.parseInt(x_coord[0]);
        int X_y = Integer.parseInt(x_coord[1]);

        int Target_M_x = X_x + 1;
        int Target_M_y = X_y + 1;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(Target_M_y);

        int Target_A_x = X_x + 2; 
        int Target_A_y = X_y + 2;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(Target_A_y);

        int Target_S_x = X_x + 3;
        int Target_S_y = X_y + 3;
        String Target_S = String.valueOf(Target_S_x) + "_" + String.valueOf(Target_S_y);

        if (coordinates.get("M").contains(Target_M) && coordinates.get("A").contains(Target_A) && coordinates.get("S").contains(Target_S)) total+=1;
    }

    return total;
 }

 public static int diagonalLeftDown(Map<String, List<String>> coordinates) {

    int total = 0;
    List<String> X_coordinates = coordinates.get("X");
    for (int i = 0; i < X_coordinates.size(); i++) {
        String X = (String) X_coordinates.get(i);
        String[] x_coord = X.split("_");
        int X_x = Integer.parseInt(x_coord[0]);
        int X_y = Integer.parseInt(x_coord[1]);

        int Target_M_x = X_x - 1;
        int Target_M_y = X_y + 1;
        String Target_M = String.valueOf(Target_M_x) + "_" + String.valueOf(Target_M_y);

        int Target_A_x = X_x - 2; 
        int Target_A_y = X_y + 2;
        String Target_A = String.valueOf(Target_A_x) + "_" + String.valueOf(Target_A_y);

        int Target_S_x = X_x - 3;
        int Target_S_y = X_y + 3;
        String Target_S = String.valueOf(Target_S_x) + "_" + String.valueOf(Target_S_y);

        if (coordinates.get("M").contains(Target_M) && coordinates.get("A").contains(Target_A) && coordinates.get("S").contains(Target_S)) total+=1;
    }

    return total;
 } 
}
