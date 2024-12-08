package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Day8 {

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
        Double x1 = Double.parseDouble(pair[0].split("_")[0]);
        Double y1 = Double.parseDouble(pair[0].split("_")[1]);
        Double x2 = Double.parseDouble(pair[1].split("_")[0]);
        Double y2 = Double.parseDouble(pair[1].split("_")[1]);

        System.out.println("x1 " + String.valueOf(x1) + " y1 " + String.valueOf(y1) + " x2 " + String.valueOf(x2) + " y2 " + String.valueOf(y2));

        Double distance = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));

        System.out.println("distance: " + String.valueOf(distance));

        Double direction_x = x2 - x1;
        Double direction_y = y2 - y1;

        Double l = Math.sqrt(direction_x*direction_x + direction_y * direction_y);
        Double unit_x = direction_x/l;
        Double unit_y = direction_y/l;

        // double the distance
        Double move_factor = 2.0;
        Integer node1_x = (int) Math.round(x1 + move_factor * distance * unit_x);
        Integer node1_y = (int) Math.round(y1 + move_factor * distance * unit_y);

        Integer node2_x = (int) Math.round(x2 - move_factor * distance * unit_x);
        Integer node2_y = (int) Math.round(y2 - move_factor * distance * unit_y);

        System.out.println("node 1: " + String.valueOf(node1_x) + " " + String.valueOf(node1_y));
        System.out.println("node 2: " + String.valueOf(node2_x) + " " + String.valueOf(node2_y));

        if (node1_x >= 0 && node1_x <= x_bound && node1_y >= 0 && node1_y <= y_bound) {
            String coordinate1 = String.valueOf(node1_x) + "_" + String.valueOf(node1_y);
            nodes.add(coordinate1);
        }

        if (node2_x >= 0 && node2_x <= x_bound && node2_y >= 0 && node2_y <= y_bound) {
            String coordinate2 = String.valueOf(node2_x) + "_" + String.valueOf(node2_y);
            nodes.add(coordinate2);
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

        System.out.println("x_bound: " + x_bound + " reminder this is 0-indexed");
        System.out.println("y_bound: " + y_bound + " reminder this is 0-indexed");
        for (String n : nodes) System.out.println(n);
        System.out.println(nodes.size());
    }

}

