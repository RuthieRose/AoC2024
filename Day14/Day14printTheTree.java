package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Day14printTheTree {

    public static void main (String[] args) throws IOException {

        //left portion of array is iterations, right portion is count

        String[][] grid = new String[103][101];
        for (String[] x : grid) {
            Arrays.fill(x, " ");
        }
        List<int[]> coordinates = new ArrayList<int[]>();

        int total = 0;

        String filename = "Day14/input.txt";

        int x_bound_sample = 10;
        int y_bound_sample = 6;

        int x_bound_input = 100;
        int y_bound_input = 102;

        int x_median_sample = 5;
        int y_median_sample = 3;

        int x_median_input = 50;
        int y_median_input = 51;

        int x_bound = -1;
        int y_bound = -1;
        int x_median = -1;
        int y_median = -1;

        if (filename.contains("sample")) {
            x_bound = x_bound_sample;
            y_bound = y_bound_sample;
            x_median = x_median_sample;
            y_median = y_median_sample;
        }

        else if (filename.contains("input")) {{
            x_bound = x_bound_input;
            y_bound = y_bound_input;
            x_median = x_median_input;
            y_median = y_median_input;
        }}

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while(reader.ready()) {
                String line = reader.readLine();
                
                String[] robotInput = line.split(" ");

                //System.out.println(Arrays.toString(robotInput));

                String[] robotCoordinates = robotInput[0].substring(2).split(",");
                String[] robotVelocity = robotInput[1].substring(2).split(",");

                int robot_x = Integer.parseInt(robotCoordinates[0]);
                int robot_y = Integer.parseInt(robotCoordinates[1]);

                int robot_vx = Integer.parseInt(robotVelocity[0]);
                int robot_vy = Integer.parseInt(robotVelocity[1]);

               // System.out.println(x_bound + " " + y_bound + " " + x_median + " " + y_median);

                for (int i = 1; i <= 7623; i++) {
                    robot_x += robot_vx;
                    if (robot_x > x_bound) robot_x = robot_x % x_bound - 1;
                    else if (robot_x < 0) robot_x = (x_bound + robot_x + 1);

                    robot_y += robot_vy;
                    if (robot_y > y_bound) robot_y = robot_y % y_bound - 1;
                    else if (robot_y < 0) robot_y = (y_bound + robot_y + 1);
    
                }

                int[] robot = {robot_x,robot_y};

                coordinates.add(robot);

            } 
        }
        

        for (int i = 0; i < coordinates.size(); i++) {
            int[] robot = coordinates.get(i);
            int x = robot[0];
            int y = robot[1];
            grid[y][x] = "X";
        }
        
        PrintWriter writer = new PrintWriter("Day14/MerryChristmas.txt");
        
        for (String[] x : grid) {

            writer.println(String.join(" ", x));
        }

        writer.close();
       
    }
}



