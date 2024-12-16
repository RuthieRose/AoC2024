package Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15 {

    // move is only to be utilized if we have determined that there is a box at the potential direction. 
    // this is to handle the complexity of an unknown number of boxes to be moved. 

    public static String move(String robot, String direction, String[][] grid) {

        String[] coordinates = robot.split("_");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        // is there a free space to be moved to?
        // if so what
        // then make the moves

        if (direction.equals("^")) {
            // starts one away from the robot because we have already determined there is a box there
            int potential_destination_y = y-1;
            int n = x;
            String space = grid[potential_destination_y][n];

            while (!space.equals(".") && !space.equals("#") && potential_destination_y < grid.length) {
                potential_destination_y -= 1;
                space = grid[potential_destination_y][n];
            }

            if (space.equals(".")) {
                // only have to alter the robot, the first and the last because everything in between is O. 

                // set robot's current space to a free space
                grid[y][n] = ".";
                // set the first block to the robot
                grid[y-1][n] = "@";
                // update the robot's coordinates
                robot = n + "_" + (y-1);
                // set the potential_destination_y to the box
                grid[potential_destination_y][n] = "O";
            }
            // do nothing for the other cases. the other side of the boxes is a wall. so there is no move.
        }

        else if (direction.equals("v")) {
            // starts one away from the robot because we have already determined there is a box there
            int potential_destination_y = y+1;
            int n = x;
            String space = grid[potential_destination_y][n];

            while (!space.equals(".") && !space.equals("#") && potential_destination_y < grid.length) {
                potential_destination_y += 1;
                space = grid[potential_destination_y][n];
            }

            if (space.equals(".")) {
                // only have to alter the robot, the first and the last because everything in between is O. 

                // set robot's current space to a free space
                grid[y][n] = ".";
                // set the first block to the robot
                grid[y+1][n] = "@";
                // update the robot's coordinates
                robot = n + "_" + (y+1);
                // set the potential_destination_y to the box
                grid[potential_destination_y][n] = "O";
            }
            // do nothing for the other cases. the other side of the boxes is a wall. so there is no move.
        }
        else if (direction.equals("<")) {
            // starts one away from the robot because we have already determined there is a box there
            int m = y;
            int potential_destination_x = x-1;
            String space = grid[m][potential_destination_x];

            while (!space.equals(".") && !space.equals("#") && potential_destination_x > 0) {
                potential_destination_x -= 1;
                space = grid[m][potential_destination_x];
            }

            if (space.equals(".")) {
                // only have to alter the robot, the first and the last because everything in between is O. 

                // set robot's current space to a free space
                grid[m][x] = ".";
                // set the first block to the robot
                grid[m][x-1] = "@";
                // update the robot's coordinates
                robot = (x-1) + "_" + (m);
                // set the potential_destination_y to the box
                grid[m][potential_destination_x] = "O";
            }
            // do nothing for the other cases. the other side of the boxes is a wall. so there is no move.
        }
        else if (direction.equals(">")) {
            // starts one away from the robot because we have already determined there is a box there
            int m = y;
            int potential_destination_x = x+1;
            String space = grid[m][potential_destination_x];

            while (!space.equals(".") && !space.equals("#") && potential_destination_x < grid[0].length) {
                potential_destination_x += 1;
                space = grid[m][potential_destination_x];
            }

            if (space.equals(".")) {
                // only have to alter the robot, the first and the last because everything in between is O. 

                // set robot's current space to a free space
                grid[m][x] = ".";
                // set the first block to the robot
                grid[m][x+1] = "@";
                // update the robot's coordinates
                robot = (x+1) + "_" + (m);
                // set the potential_destination_y to the box
                grid[m][potential_destination_x] = "O";
            }
            // do nothing for the other cases. the other side of the boxes is a wall. so there is no move.
        }
        return robot;
    }

    public static void main (String[] args) throws IOException {

    String input = "Day15/input-moves.txt";
    String gridInput = "Day15/input-grid.txt";

    int y_length = 0;

    if (gridInput.contains("input")) y_length = 50;
    if (gridInput.contains("sample-grid1") || gridInput.contains("sample-grid3")) y_length = 8;
    if (gridInput.contains("sample-grid2")) y_length = 10;

    String[][] grid = new String[y_length][];

    int y_count = 0;

    int total = 0;

    String robotLocation = "";

    List<String> robotMoves = new ArrayList<String>();

    try (BufferedReader reader = new BufferedReader(new FileReader(gridInput))) {
        while(reader.ready()) {
            String line = reader.readLine();          
            String[] row = line.split("");
            grid[y_count] = row;
            y_count += 1;
        } 
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
        while(reader.ready()) {
            String line = reader.readLine();          
            String[] moves = line.split("");
            robotMoves = Arrays.asList(moves);
        }
    }

    for (int i = 0; i < robotMoves.size(); i++) {

        for (int a = 0; a < grid.length; a++) {
            for (int b = 0; b < grid[a].length; b++) {
                if (grid[a][b].equals("@")) {
                    robotLocation = b + "_" + a;
                }
            }
        }

        String nextMove = robotMoves.get(i);
        System.out.println("next move: " + nextMove);
        String[] robotCoordinates = robotLocation.split("_");
        int robot_x = Integer.parseInt(robotCoordinates[0]);
        int robot_y = Integer.parseInt(robotCoordinates[1]);


        if (nextMove.equals("^") && robot_y > 0) {
            String whatIsAtTheNextMove = grid[robot_y-1][robot_x];
            if (whatIsAtTheNextMove.equals("O")) {
                robotLocation = move(robotLocation, nextMove, grid);
                System.out.println("The next move is " + whatIsAtTheNextMove + " so the method was called and the new robotLocation is: " + robotLocation);
            }
            else if (whatIsAtTheNextMove.equals(".")) {
                grid[robot_y][robot_x] = ".";
                grid[robot_y - 1][robot_x] = "@";
                robotLocation =  robot_x + "_" + (robot_y - 1);
                System.out.println("The next move is: " + whatIsAtTheNextMove + " so the robot just moved 1 from " + robot_x + "," + robot_y + " To " + robot_x + "," + (robot_y - 1));
            }
            else if (whatIsAtTheNextMove.equals("#")) {
                System.out.println("Let's see where the problem is");
            }
        }
        else if (nextMove.equals("v") && robot_y < (grid.length - 1)) {
            String whatIsAtTheNextMove = grid[robot_y+1][robot_x];
            if (whatIsAtTheNextMove.equals("O")) {
                robotLocation = move(robotLocation, nextMove, grid);
                System.out.println("The next move is " + whatIsAtTheNextMove + " so the method was called and the new robotLocation is: " + robotLocation);
            }
            else if (whatIsAtTheNextMove.equals(".")) {
                grid[robot_y][robot_x] = ".";
                grid[robot_y + 1][robot_x] = "@";
                robotLocation =  robot_x + "_" + (robot_y + 1);
            }
        }
        else if (nextMove.equals("<") && robot_x > 0) {
            String whatIsAtTheNextMove = grid[robot_y][robot_x - 1];
            if (whatIsAtTheNextMove.equals("O")) {
                robotLocation = move(robotLocation, nextMove, grid);
                System.out.println("The next move is " + whatIsAtTheNextMove + " so the method was called and the new robotLocation is: " + robotLocation);
            }
            else if (whatIsAtTheNextMove.equals(".")) {
                grid[robot_y][robot_x] = ".";
                grid[robot_y][robot_x-1] = "@";
                robotLocation =  (robot_x - 1) + "_" + robot_y;
            }
        }
        else if (nextMove.equals(">") && robot_y < grid[0].length - 1) {
            String whatIsAtTheNextMove = grid[robot_y][robot_x + 1];
            if (whatIsAtTheNextMove.equals("O")) {
                robotLocation = move(robotLocation, nextMove, grid);
                System.out.println("The next move is " + whatIsAtTheNextMove + " so the method was called and the new robotLocation is: " + robotLocation);

            }
            else if (whatIsAtTheNextMove.equals(".")) {
                grid[robot_y][robot_x] = ".";
                grid[robot_y][robot_x+1] = "@";
                robotLocation =  (robot_x + 1) + "_" + robot_y;
            }
        }
        for (String[] r : grid) {
            System.out.println(String.join("", r));
        }
        System.out.println("------------------");
    }
    for (int a = 0; a < grid.length; a++) {
        for (int b = 0; b < grid[a].length; b++) {
            if (grid[a][b].equals("O")) {
                total += ((100 * a) + b);
            }
        }
    }
    System.out.println(total);

    }
}



