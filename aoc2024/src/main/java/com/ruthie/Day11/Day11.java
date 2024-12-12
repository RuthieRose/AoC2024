package com.ruthie.Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day11 {

    public static List<String> blink(List<String> arr) {

        List<String> aux = new ArrayList<String>();

        for (int i = 0; i < arr.size(); i++) {
            String item = arr.get(i);

            // if item is 0 replace by 1
            if (item.equals("0")) {
                aux.add("1");
            }
            // if item has even number of digits split
            else if (item.length() % 2 == 0) {
                String[] itemSplit = item.split("");
                int l = itemSplit.length/2;
                String[] first = new String[l];
                String[] second = new String[l];

                for (int j = 0; j < l; j++) {
                    String m = itemSplit[j];
                    String n = itemSplit[j+l];
                    first[j] = m;
                    second[j] = n;
                }

                // remove leading 0
                int secondInt = Integer.parseInt(String.join("",second));


                // join and add to aux
                String firstString = String.join("", first);
                String secondString = String.valueOf(secondInt);

                aux.add(firstString);
                aux.add(secondString);
            }
            else {
                long num = Long.parseLong(item);
                long num2024 = num * 2024;
                String replace = String.valueOf(num2024);
                //System.out.println(num2024 + " " + replace);
                aux.add(replace);
            }
        }

        return aux;
    }

    public static void main (String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/ruthie/Day11/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();

                String[] arr = line.split(" ");
                
                List<String> s = blink(Arrays.asList(arr));
                System.out.println(s);
                List<String> t = blink(s);
                System.out.println(t);

                for (int i = 0; i < 21; i+=2) {
                    s = blink(t);
                    //System.out.println(s);

                    t = blink(s);
                    //System.out.println(t);

                }

                s = blink(t);

                System.out.println(s.size());

            }
        }

        
        //System.out.println();
    }

}

