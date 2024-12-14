package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Day10 {

        public static void dfs(List<List<String>> grid, String zero_coordinate, int y, int x, int previousVal, HashMap <String, List<String>> visited, ArrayList<String> currentPath, HashMap<String, List<List<String>>> scores) {
            
            // if out of bounds or visited return 
            if (y < 0 || y >= grid.size() || x < 0 || x >= grid.get(0).size() || visited.get(zero_coordinate).contains(x+"_"+y)) return;
            
            // get diff

            int currentVal = Integer.parseInt(grid.get(y).get(x));

            if (currentVal - previousVal != 1) return;

            // mark current cell as visited
            visited.get(zero_coordinate).add(x + "_" + y);
            
            currentPath.add(x + "_" + y);

            if (currentVal == 9) scores.get(zero_coordinate).add(new ArrayList<>(currentPath));
        

            // get next direction in all four directions
            dfs(grid, zero_coordinate, y+1, x, currentVal, visited, currentPath, scores);
            dfs(grid, zero_coordinate, y-1, x, currentVal, visited, currentPath, scores);
            dfs(grid, zero_coordinate, y, x+1, currentVal, visited, currentPath, scores);
            dfs(grid, zero_coordinate, y, x-1, currentVal, visited, currentPath, scores);

            // backtrack
            currentPath.remove(currentPath.size() - 1);

        }

    public static void main (String[] args) throws IOException {

        List<List<String>> grid = new ArrayList<List<String>>();
        HashMap<String, List<String>> visited = new HashMap<String, List<String>>();
        HashMap<String, List<List<String>>> scores = new HashMap<>();

        // need to set up a hashmap of visited for each 0 point

        try (BufferedReader reader = new BufferedReader(new FileReader("Day10/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();

                String[] arr = line.split("");
                grid.add(Arrays.asList(arr));

            }
        }

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int val = Integer.parseInt(grid.get(i).get(j));
                if (val == 0) {
                    // add 0 coordinate to hashmap visited
                    String coordinate = j + "_" + i;
                    visited.put(coordinate, new ArrayList<>());
                    visited.get(coordinate).add(coordinate);
                    System.out.println("visited " + visited.toString());
                    scores.put(coordinate, new ArrayList<>());

                    // start function
                    dfs(grid, coordinate, i+1, j, 0, visited, new ArrayList<String>(), scores);
                    dfs(grid, coordinate, i-1, j, 0, visited, new ArrayList<String>(), scores);
                    dfs(grid, coordinate, i, j+1, 0, visited, new ArrayList<String>(), scores);
                    dfs(grid, coordinate, i, j-1, 0, visited, new ArrayList<String>(), scores);
                }
            }
        }

        //System.out.println(grid.toString());
        
        System.out.println(scores.toString());

        int total_scores = 0;

        for (String t : scores.keySet()) {
            total_scores += scores.get(t).size();
        }
        System.out.println(total_scores);
    }

}

