package Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.List;

public class Day13b {

    public static void main (String[] args) throws IOException {

        BigDecimal total = new BigDecimal("0");

        List<String> input = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Day13/input.txt"))) {
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


            String[] A = A_line.split(", ");
            String[] B = B_line.split(", ");
            String[] P = P_line.split(", ");

            String A_x_string = A[0];
            String A_y_string = A[1];

            String B_x_string = B[0];
            String B_y_string = B[1];

            String P_x_string = P[0];
            String P_y_string = P[1];

            BigDecimal A_x = new BigDecimal(A_x_string.split("\\+")[1]);
            BigDecimal A_y = new BigDecimal(A_y_string.split("\\+")[1]);

            BigDecimal B_x = new BigDecimal(B_x_string.split("\\+")[1]);
            BigDecimal B_y = new BigDecimal(B_y_string.split("\\+")[1]);

            BigDecimal P_x = new BigDecimal(P_x_string.split("=")[1]).add(new BigDecimal ("10000000000000"));
            BigDecimal P_y = new BigDecimal(P_y_string.split("=")[1]).add(new BigDecimal ("10000000000000"));

            System.out.println("A_x " + A_x + " B_x " + B_x + " A_y " + A_y + " B_y " + B_y + " P_x " + P_x + " P_y " + P_y);

            BigDecimal A_count_num_left = P_x.multiply(B_y);
            System.out.println("A_count_num_left " + A_count_num_left);
            BigDecimal A_count_num_right = P_y.multiply(B_x);
            System.out.println("A_count_num_right " + A_count_num_right);
            BigDecimal A_count_num = A_count_num_left.subtract(A_count_num_right);

            BigDecimal A_count_den_left = A_x.multiply(B_y);
            BigDecimal A_count_den_right = A_y.multiply(B_x);
            BigDecimal A_count_den = A_count_den_left.subtract(A_count_den_right);
            System.out.println("A_count_num " + A_count_num + " A_count_den " + A_count_den);


            BigDecimal B_count_num_left = A_x.multiply(P_y);
            BigDecimal B_count_num_right = A_y.multiply(P_x);
            BigDecimal B_count_num = B_count_num_left.subtract(B_count_num_right);

            BigDecimal B_count_den_left = A_x.multiply(B_y);
            BigDecimal B_count_den_right = A_y.multiply(B_x);      
            BigDecimal B_count_den = B_count_den_left.subtract(B_count_den_right);
            System.out.println("B_count_num " + B_count_num + " B_count_den " + B_count_den);

            BigDecimal A_count = A_count_num.divide(A_count_den, 10, RoundingMode.HALF_EVEN);
            BigDecimal B_count = B_count_num.divide(B_count_den, 10, RoundingMode.HALF_EVEN);      

            System.out.println("A_count " + A_count + " B_count " + B_count);
            System.out.println("----------------------------" + A_count.setScale(0, RoundingMode.HALF_DOWN).compareTo(A_count));
            System.out.println("----------------------------" + B_count.setScale(0, RoundingMode.HALF_DOWN).compareTo(B_count));


            if (A_count.setScale(0, RoundingMode.HALF_DOWN).compareTo(A_count) == 0 && B_count.setScale(0,RoundingMode.HALF_DOWN).compareTo(B_count) == 0) {
                BigDecimal token_mult = new BigDecimal("3");
                System.out.println(token_mult + " token_mult");
                BigDecimal A_tokens = (A_count.multiply(token_mult)).setScale(0, RoundingMode.HALF_DOWN); 
                System.out.println(A_tokens + " A_tokens");
                BigDecimal game_total = A_tokens.add(B_count.setScale(0, RoundingMode.HALF_DOWN));
                System.out.println("game total " + game_total);
                total = total.add(game_total);
            }
        }

        System.out.println(total);

        
    }
}


