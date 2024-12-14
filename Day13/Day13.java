package Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day13 {

    public static void main (String[] args) throws IOException {

        int total = 0;

        List<String> input = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/ruthie/Day13/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                input.add(line);
            } 
        }

        while (input.size() > 0) {
            
            String line1 = input.remove(0);
            String line2 = input.remove(0);
            String line3 = input.remove(0);

            // blank line
            input.remove(0);

            String A_line = line1.substring(10);
            String B_line = line2.substring(10);
            String P_line = line3.substring(7);
            
            // System.out.println(A_line + " " + B_line + " " + P_line);

            String[] A = A_line.split(", ");
            String[] B = B_line.split(", ");
            String[] P = P_line.split(", ");

            String A_x_string = A[0];
            String A_y_string = A[1];

            String B_x_string = B[0];
            String B_y_string = B[1];

            String P_x_string = P[0];
            String P_y_string = P[1];

            int A_x = Integer.parseInt(A_x_string.split("\\+")[1]);
            int A_y = Integer.parseInt(A_y_string.split("\\+")[1]);

            int B_x = Integer.parseInt(B_x_string.split("\\+")[1]);
            int B_y = Integer.parseInt(B_y_string.split("\\+")[1]);

            int P_x = Integer.parseInt(P_x_string.split("=")[1]);
            int P_y = Integer.parseInt(P_y_string.split("=")[1]);

            int A_count_num = (P_x * B_y) - (P_y * B_x);
            int A_count_den = (A_x * B_y) - (A_y * B_x);
            int B_count_num = (A_x * P_y) - (A_y * P_x);
            int B_count_den = (A_x * B_y) - (A_y * B_x); 

            Boolean remainderFlag = false;

            if (A_count_num % A_count_den != 0) remainderFlag = true;
            if (B_count_num % B_count_den != 0) remainderFlag = true;

            int A_count = A_count_num/A_count_den;
            int B_count = B_count_num/B_count_den;      

           // System.out.println(A_x + " " + A_y + " " + B_x +" " + B_y + " " + P_x + " " + P_y);

            if (A_count <= 100 && B_count <= 100 && !remainderFlag) {
                total += (A_count * 3) + B_count;
                System.out.println(A_count + " " + B_count);
            }
        }

        System.out.println(total);

        
    }
}



