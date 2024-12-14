package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// count all the positions

public class Day6b {

    // input grid area is 16,900 with 5,444 moves for the first solution.
    // sample grid area is 100 with 44 moves for the first solution.

    public static void main (String[] args) throws IOException {

        Integer obstacles = 0;

        List<List<String>> coordinates = new ArrayList<>();
        Integer y = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Day6/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                List<String> row = Arrays.asList(line.split(""));
                coordinates.add(y,row);
                y+=1;
            }
        }

        // run a loop to add and then remove an obstacle. keep track of where it's at. run the check for loop function

        Integer x_obstacle = 0;
        Integer y_obstacle = 0;

        Integer x_bound = coordinates.get(0).size();
        Integer y_bound = coordinates.size();

        while (y_obstacle < coordinates.size()) {

            //System.out.println("x_obstacle " + x_obstacle + " y_obstacle " + y_obstacle);

            if (coordinates.get(y_obstacle).get(x_obstacle).equals(".")) {
                List<String> workingRow = new ArrayList<>();
                for (String x : coordinates.get(y_obstacle)) workingRow.add(x);
                workingRow.set(x_obstacle, "#");
                coordinates.set(y_obstacle,workingRow);
                if (checkForLoop(coordinates)) obstacles += 1;
                // reset
                workingRow.set(x_obstacle, ".");
                coordinates.set(y_obstacle,workingRow);
            }
            // iterate
            if (x_obstacle < x_bound-1 && y_obstacle < y_bound) x_obstacle += 1;
            else if (y_obstacle < y_bound-1) {
                x_obstacle = 0;
                y_obstacle += 1;
            }
            else break;
        }
        

        System.out.println(obstacles);

    }    

    public static Boolean checkForLoop (List<List<String>> coordinates) throws IOException {

        // count of moves
        Boolean loop = false;
        Integer moveCount = 0;

        // map all obstacles

        Set<String> visited = new HashSet<String>();
        Integer y = coordinates.size()-1;
        Integer x = 0;
        Integer[] guard = new Integer[2];

        
        // map the starting position of the guard

        for (List<String> r : coordinates) {
            for (String x_str : r) {
                if (x_str.equals("^")) {
                    Integer y_int = coordinates.indexOf(r);
                    Integer x_int = r.indexOf(x_str);
                    guard[0] = x_int;
                    guard[1] = y_int;
                }
            }
        }

        Integer x_bound = coordinates.get(0).size();
        Integer y_bound = coordinates.size();

        Integer gridArea = x_bound * y_bound;

        // direction flag
        String direction = "up";

        String visitedPosition = "";

        // add guard's starting position to visited

        visitedPosition = String.valueOf(guard[0]) + "_" + String.valueOf(guard[1]);
        visited.add(visitedPosition);

        // iterate over everywhere she is

        // get next move flag
        Boolean inBounds = true;

        while (inBounds && !loop) {

            //for (String a : visited) System.out.println(a);

            Integer[] checkPosition = new Integer[2];
            if (direction.equals("up")) checkPosition = moveUp(guard);
            if (direction.equals("down")) checkPosition = moveDown(guard);
            if (direction.equals("right")) checkPosition = moveRight(guard);
            if (direction.equals("left")) checkPosition = moveLeft(guard);

            x = checkPosition[0];
            y = checkPosition[1];

            //  if next coordinate is out of bounds break 
            if (y < 0 || y >= y_bound || x < 0 || x >= x_bound) {
                //System.out.println("out of bounds");
                inBounds = false;
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
                    //System.out.println("out of bounds");
                    inBounds = false;
                }

                // if clear make move
                else if (coordinates.get(y).get(x).equals(".") || coordinates.get(y).get(x).equals("^")) {
                    guard[0] = x;
                    guard[1] = y;
                    visitedPosition = String.valueOf(x) + "_" + String.valueOf(y);
                    visited.add(visitedPosition);
                }
            }
            moveCount +=1;
            //System.out.println(moveCount);
            if (moveCount > (2*gridArea)) loop = true;
            } 

    //System.out.println(visited.size());
    //System.out.println("The grid area is " + gridArea);
    //System.out.println("The number of total moves are " + moveCount);
    return loop;
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

