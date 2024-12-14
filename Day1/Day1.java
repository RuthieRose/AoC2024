package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Day1 {
    public static void main (String[] args) throws IOException {
        int count = 0;

        // have to set up 2 arrays and take the first out of each pair and put in the first array and the second out of each pair and put in the second array
        ArrayList<Integer> set1 = new ArrayList<Integer>();
        ArrayList<Integer> set2 = new ArrayList<Integer>();

        try (Stream<String> lines = Files.lines(Paths.get("Day1/sample.txt"))) {
            lines
                .map(l -> l.split("   "))
                .forEach(a -> {
                    set1.add(Integer.parseInt(a[0]));
                    set2.add(Integer.parseInt(a[1]));
                    //System.out.println(a[0]);
                    //System.out.println(a[1]);
                });
        }
        Collections.sort(set1);
        Collections.sort(set2);
        
        for (int i = 0; i < set1.size(); i++) {
            int a = (int) set1.get(i);
            int b = (int) set2.get(i);
            int diff = Math.abs(a - b);
            count += diff;
        }
        System.out.println(count);   
    }
}
