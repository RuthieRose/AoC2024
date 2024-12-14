package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day14 {

    public static void main (String[] args) throws IOException {

        int UL = 0;
        int UR = 0;
        int LL = 0;
        int LR = 0;

        int total = 0;

        // the robots wrap, maybe use modulo 

        // get each robot's position after 100 iterations (for loop?)

        // multiply UL * UR * LL * LR

        // List<String> robots = new ArrayList<String>();

        // actual grid is 101 wide, so x_bound is 100
        // actual grid is 103 tall  so y_bound is 102

        // sample grid x_bound is 10 (11 wide)
        // sample grid y_bound is 6 (7 tall)

        // sample: if robot's position is x == 5 or y == 3 then it is not counted in the total. 
        // input: if robot's position is x == 50 or y == 51 then it is not counted in the total.
        String filename = "src/main/java/com/ruthie/Day14/input.txt";

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

                for (int i = 0; i < 100; i++) {
                    robot_x += robot_vx;
                    if (robot_x > x_bound) robot_x = robot_x % x_bound - 1;
                    else if (robot_x < 0) robot_x = (x_bound + robot_x + 1);

                    robot_y += robot_vy;
                    if (robot_y > y_bound) robot_y = robot_y % y_bound - 1;
                    else if (robot_y < 0) robot_y = (y_bound + robot_y + 1);
    
                }

                //System.out.println(robot_x + " " + robot_y);

                // upper left quadrant: 
                    // sample: x < 5 && y < 3
                    // input: x < 50 && y < 51
                if (robot_x < x_median && robot_y < y_median) UL += 1;
                // upper right quadrant: 
                    // sample: x > 5 && y < 3
                    // input: x > 50 && y < 51
                if (robot_x > x_median && robot_y < y_median) UR += 1;
                // lower left quadrant: 
                    // sample: x < 5 && y > 3
                    // input: x < 50 && y > 51
                if (robot_x < x_median && robot_y > y_median) LL += 1;
                // lower right quadrant: 
                    // sample: x > 5 && y > 3
                    // input: x > 50 && y > 51
                if (robot_x > x_median && robot_y > y_median) LR += 1;

            } 
        }
        total = (UL * UR * LL * LR);
        System.out.println(total);
       
    }
}



