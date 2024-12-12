package com.ruthie.Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Day8b {

    public static List<String[]> generateCoordinatePairs(List<String> coordinates) {
        List<String[]> pairs = new ArrayList<String[]>();

        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = i+1; j < coordinates.size(); j++) {
                pairs.add(new String[] {coordinates.get(i), coordinates.get(j)});
            }
        }

        return pairs;
    }


    public static void generateNodes(String[] pair, Integer x_bound, Integer y_bound, HashSet<String> nodes) {
        String point1 = pair[0];
        String point2 = pair[1];
        nodes.add(point1);
        nodes.add(point2);
        int x1 = Integer.parseInt(pair[0].split("_")[0]);
        int y1 = Integer.parseInt(pair[0].split("_")[1]);
        int x2 = Integer.parseInt(pair[1].split("_")[0]);
        int y2 = Integer.parseInt(pair[1].split("_")[1]);

        System.out.println("x1 " + String.valueOf(x1) + " y1 " + String.valueOf(y1) + " x2 " + String.valueOf(x2) + " y2 " + String.valueOf(y2));

        double slope = (x2 - x1) == 0 ? Double.MAX_VALUE : (double)(y2 - y1) / (x2 - x1);

        for (int px = 0; px <= x_bound; px++) {
            for (int py = 0; py <= y_bound; py++) {
                // Calculate the slope from (x1, y1) to (px, py)
                double pointSlope = (px - x1) == 0 ? Double.MAX_VALUE : (double)(py - y1) / (px - x1);
    
                // Check if this point lies on the line
                if (pointSlope == slope) {
                    // If it does, add the point to the set
                    // Hey today I learned that you can just concatenate without value of...
                    String coordinate = px + "_" + py;
                    nodes.add(coordinate);
                }
            }
        }

    }
    
    public static void main (String[] args) throws IOException {

        int y = -1;
        int x_bound = 0;
        int y_bound = 0;


        Map<Character,ArrayList<String>> antennae =  new HashMap<Character,ArrayList<String>>();
        HashSet<String> nodes = new HashSet<String>();
        

        try (BufferedReader reader = new BufferedReader(new FileReader("Day8/input.txt"))) {
            while(reader.ready()) {
                y += 1;
                String line = reader.readLine();
                x_bound = line.length()-1;
                for (int i = 0; i < line.length(); i++) {
                    Character a = line.charAt(i);
                    if (!a.equals('.')) {
                        String coordinate = String.valueOf(i) + "_" + String.valueOf(y);
                        antennae.computeIfAbsent(a, k -> new ArrayList<String>()).add(coordinate);
                    }
                }             
            };    
        }

        y_bound = y;
        for (Character a : antennae.keySet() ) {
            System.out.println(a + ": " + antennae.get(a).toString());
            List<String[]> pairs = generateCoordinatePairs(antennae.get(a));
            for (String[] p : pairs) {
                System.out.println(Arrays.toString(p));
                generateNodes(p, x_bound, y_bound, nodes);              
            }
        }
        for (String n : nodes) System.out.println(n);
        System.out.println("x_bound: " + x_bound + " reminder this is 0-indexed");
        System.out.println("y_bound: " + y_bound + " reminder this is 0-indexed");
        
        System.out.println(nodes.size());
    }

}

