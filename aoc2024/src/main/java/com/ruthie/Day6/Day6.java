package com.ruthie.Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// count all the positions

public class Day6 {
    public static void main (String[] args) throws IOException {

        // map all obstacles

        List<List<String>> coordinates = new ArrayList<>();
        Set<String> visited = new HashSet<String>();
        Integer y = 0;
        Integer x = 0;
        Integer[] guard = new Integer[2];

        try (BufferedReader reader = new BufferedReader(new FileReader("Day6/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                List<String> row = Arrays.asList(line.split(""));
                coordinates.add(y,row);
                y+=1;
            }
        }
        
        // map the starting position of the guard

        for (List<String> r : coordinates) {
            for (String x_str : r) {
                if (x_str.equals("^")) {
                    Integer y_int = coordinates.indexOf(r);
                    Integer x_int = r.indexOf(x_str);
                    guard[0] = x_int;
                    guard[1] = y_int;
                    System.out.println("Guard starting position: " + x_int + "," + y_int);
                }
            }
        }

        Integer x_bound = coordinates.get(0).size();
        Integer y_bound = coordinates.size();

        // direction flag
        String direction = "up";

        String visitedPosition = "";

        // add guard's starting position to visited

        visitedPosition = String.valueOf(guard[0]) + "_" + String.valueOf(guard[1]);
        visited.add(visitedPosition);

        

        // iterate over everywhere she is

        // get next move flag
        Boolean inBounds = true;

        while (inBounds) {

            System.out.println("The guard is at: " + guard[0] + "," + guard[1]);
            for (String a : visited) System.out.println(a);

            Integer[] checkPosition = new Integer[2];
            if (direction.equals("up")) checkPosition = moveUp(guard);
            if (direction.equals("down")) checkPosition = moveDown(guard);
            if (direction.equals("right")) checkPosition = moveRight(guard);
            if (direction.equals("left")) checkPosition = moveLeft(guard);

            x = checkPosition[0];
            y = checkPosition[1];

            //  if next coordinate is out of bounds break 
            if (y < 0 || y >= y_bound || x < 0 || x >= x_bound) {
                System.out.println("out of bounds");
                System.out.println("The number of places visited is " + visited.size());
                inBounds = false;
                break;
            }

            // if next coordinate is clear make the move, direction stays the same
            else if (coordinates.get(y).get(x).equals(".") || coordinates.get(y).get(x).equals("^")) {
                guard[0] = x;
                guard[1] = y;
                visitedPosition = String.valueOf(x) + "_" + String.valueOf(y);
                visited.add(visitedPosition);
            }

            // this is tricky!! how many times does it need to turn? 
            else while (coordinates.get(y).get(x).equals("#")) {
                // turn right
                if (direction.equals("up")) direction = "right";
                else if (direction.equals("right")) direction = "down";
                else if (direction.equals("down")) direction = "left";
                else if (direction.equals("left")) direction = "up";

                // check coordinates
                if (direction.equals("up")) checkPosition = moveUp(guard);
                if (direction.equals("down")) checkPosition = moveDown(guard);
                if (direction.equals("right")) checkPosition = moveRight(guard);
                if (direction.equals("left")) checkPosition = moveLeft(guard);

                x = checkPosition[0];
                y = checkPosition[1];

                // if clear but out of bounds break
                if (y < 0 || y >= y_bound || x < 0 || x >= x_bound) {
                    System.out.println("out of bounds");
                    System.out.println("The number of places visited is " + visited.size());
                    inBounds = false;
                    break;
                }

                // if clear make move
                else if (coordinates.get(y).get(x).equals(".") || coordinates.get(y).get(x).equals("^")) {
                    guard[0] = x;
                    guard[1] = y;
                    visitedPosition = String.valueOf(x) + "_" + String.valueOf(y);
                    visited.add(visitedPosition);
                }
                
            } 
        }

    System.out.println(visited.size());
  }

    // next move up 
    public static Integer[] moveUp (Integer[] currentPosition) {

        Integer[] newPosition = new Integer[2];

        newPosition[0] = currentPosition[0];
        newPosition[1] = currentPosition[1] - 1;
        return newPosition;
    }

    // next move down 
    public static Integer[] moveDown (Integer[] currentPosition) {

        Integer[] newPosition = new Integer[2];

        newPosition[0] = currentPosition[0];
        newPosition[1] = currentPosition[1] + 1;
        return newPosition;
    }

    // next move right 
    public static Integer[] moveRight (Integer[] currentPosition) {

        Integer[] newPosition = new Integer[2];

        newPosition[0] = currentPosition[0] + 1;
        newPosition[1] = currentPosition[1];
        return newPosition;
    }

    // next move left 
    public static Integer[] moveLeft (Integer[] currentPosition) {

        Integer[] newPosition = new Integer[2];

        newPosition[0] = currentPosition[0] - 1;
        newPosition[1] = currentPosition[1];
        return newPosition;
    }

}

