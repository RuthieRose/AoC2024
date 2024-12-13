package com.ruthie.Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day13b {

    public static void main (String[] args) throws IOException {

        BigInteger total = new BigInteger("0");

        List<String> input = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/ruthie/Day13/sample.txt"))) {
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

            BigInteger A_x = new BigInteger(A_x_string.split("\\+")[1]);
            BigInteger A_y = new BigInteger(A_y_string.split("\\+")[1]);

            BigInteger B_x = new BigInteger(B_x_string.split("\\+")[1]);
            BigInteger B_y = new BigInteger(B_y_string.split("\\+")[1]);

            BigInteger P_x = new BigInteger(P_x_string.split("=")[1]).add(new BigInteger ("10000000000000"));
            BigInteger P_y = new BigInteger(P_y_string.split("=")[1]).add(new BigInteger ("10000000000000"));
            System.out.println(P_x + " " + P_y);

            BigInteger A_count_num_left = P_x.multiply(B_y);
            BigInteger A_count_num_right = P_y.multiply(B_x);
            BigInteger A_count_num = A_count_num_left.subtract(A_count_num_right);

            BigInteger A_count_den_left = A_x.multiply(B_y);
            BigInteger A_count_den_right = A_y.multiply(B_x);
            BigInteger A_count_den = A_count_den_left.subtract(A_count_den_right);
            System.out.println(A_count_num + " " + A_count_den);


            BigInteger B_count_num_left = A_x.multiply(P_y);
            BigInteger B_count_num_right = A_y.multiply(P_x);
            BigInteger B_count_num = B_count_num_left.subtract(B_count_num_right);

            BigInteger B_count_den_left = A_x.multiply(B_y);
            BigInteger B_count_den_right = A_y.multiply(B_x);      
            BigInteger B_count_den = B_count_den_left.subtract(B_count_den_right);
            
            Boolean remainderFlag = true;

            if (A_count_den.compareTo(new BigInteger("0")) <= 0) remainderFlag = true;
            else if ((A_count_num.mod(A_count_den)).compareTo(new BigInteger("0")) == 0) remainderFlag = false;

            if (B_count_den.compareTo(new BigInteger("0")) <= 0) remainderFlag = true;         
            else if ((B_count_num.mod(B_count_den)).compareTo(new BigInteger("0")) == 0) remainderFlag = false;

            BigInteger A_count = A_count_num.divide(A_count_den);
            BigInteger B_count = B_count_num.divide(B_count_den);      

           // System.out.println(A_x + " " + A_y + " " + B_x +" " + B_y + " " + P_x + " " + P_y);

           if (!remainderFlag) {
                BigInteger token_mult = new BigInteger("3");
                BigInteger A_tokens = A_count.multiply(token_mult); 
                BigInteger game_total = A_tokens.add(B_count);
                total.add(game_total);
           } 
        }

        System.out.println(total);

        
    }
}



