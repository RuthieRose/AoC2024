package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day12 {

    public static void getArea(List<List<String>> grid, int x_bound, int y_bound, HashMap<String, Integer> visited, List<List<String>> workingArea, int i,int j) {

        // if visited do not add working area
        Boolean visitedFlag = false; 
        String plotCoordinateString = j+"_"+i; 

        if (visited.containsKey(plotCoordinateString)) { 
            visitedFlag = true;
            if (visited.get(plotCoordinateString) > 1) return;
        }

        visited.merge(plotCoordinateString, 1, Integer::sum);

        String plot = grid.get(i).get(j);

        String leftPlot = "";
        String rightPlot = "";
        String topPlot = "";
        String bottomPlot = "";

        // get neighbor plots
        if (j > 0) leftPlot = grid.get(i).get(j-1);
        if (j <= x_bound - 1) rightPlot = grid.get(i).get(j+1);
        if (i > 0) topPlot = grid.get(i-1).get(j);
        if (i <= y_bound - 1) bottomPlot = grid.get(i+1).get(j);

        if (!visitedFlag)  { 
            Boolean addedFlag = false;
            // working area 
            int leftJ = j-1;
            int rightJ = j+1;
            int topi = i-1;
            int bottomi = i+1;
            String plotCoordinateStringLeft = leftJ+"_"+i;
            String plotCoordinateStringRight = rightJ+"_"+i;
            String plotCoordinateStringTop = j+"_"+topi;
            String plotCoordinateStringBottom = j+"_"+bottomi;

            for (int k = 0; k < workingArea.size(); k++) {
                List<String> c = workingArea.get(k);
                if ((c.contains(plotCoordinateStringLeft) && plot.equals(leftPlot)) || (c.contains(plotCoordinateStringRight) && plot.equals(rightPlot)) || (c.contains(plotCoordinateStringTop) && plot.equals(topPlot)) || (c.contains(plotCoordinateStringBottom) && plot.equals(bottomPlot))) {
                    c.add(plotCoordinateString);
                    addedFlag = true;
                }
            }
            if (!addedFlag) {
                List<String> coordinate = new ArrayList<String>();
                coordinate.add(plotCoordinateString);
                workingArea.add(coordinate);
            }
        }
        
        System.out.println("grid" + " x " + j + " y " + i + " plot: " + plot + " leftPlot: " + leftPlot + " rightPlot: " + rightPlot + " topPlot: " + topPlot + " bottomPlot: " + bottomPlot);
        

        // account for out of bounds ? 
        if (j-1 >= 0 && plot.equals(leftPlot)) getArea(grid, x_bound, y_bound, visited, workingArea, i, j-1);
        if (j+1 <= x_bound && plot.equals(rightPlot)) getArea(grid, x_bound, y_bound, visited, workingArea, i, j+1);
        if (i-1 >= 0 && plot.equals(topPlot)) getArea(grid, x_bound, y_bound, visited, workingArea, i-1, j);
        if (i+1 <= y_bound && plot.equals(bottomPlot)) getArea(grid, x_bound, y_bound, visited, workingArea, i+1, j);

    }

    public static void main (String[] args) throws IOException {

        int total = 0;

        // read in grid
        List<List<String>> grid = new ArrayList<>();

        // set up a hashmap for all letters
        HashMap<String,Integer> visited = new HashMap<>();
        List<List<String>> workingArea = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/ruthie/Day12/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String[] arr = line.split("");
                grid.add(Arrays.asList(arr));               
            }
        }
        int x_bound = grid.get(0).size() - 1;
        int y_bound = grid.size() - 1;

        System.out.println(grid.toString());

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {

                getArea(grid, x_bound, y_bound, visited, workingArea, i, j);
                
            }
        }
        
        System.out.println(total);
        System.out.println(workingArea.toString());

        while (workingArea.size() > 0) {
            List<String> list = workingArea.remove(0);
            System.out.println(list.toString());
            int perimeter = 0;
            int area = list.size();

            while (list.size() > 0) {
                String item = list.remove(0);
                // get perimeter count

                String[] xy = item.split("_");
                int i = Integer.parseInt(xy[1]);
                int j = Integer.parseInt(xy[0]);

                String plot = grid.get(i).get(j);

                String leftPlot = "";
                String rightPlot = "";
                String topPlot = "";
                String bottomPlot = "";

                // get neighbor plots
                if (j > 0) leftPlot = grid.get(i).get(j-1);
                if (j <= x_bound - 1) rightPlot = grid.get(i).get(j+1);
                if (i > 0) topPlot = grid.get(i-1).get(j);
                if (i <= y_bound - 1) bottomPlot = grid.get(i+1).get(j);

                // determine perimeter for this square 

                if (!plot.equals(leftPlot))  { 
                    perimeter += 1;
                }
                if (!plot.equals(rightPlot)) { 
                    perimeter += 1;
                }
                if (!plot.equals(topPlot)) { 
                    perimeter += 1;
                }
                if (!plot.equals(bottomPlot)) {
                    perimeter += 1;
                }

            }

            total += (perimeter * area);
        }
            System.out.println(total);
    }

}

