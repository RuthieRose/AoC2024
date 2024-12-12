package com.ruthie.Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day1b {
    public static void main (String[] args) throws IOException {
        int count = 0;
        Map<String, Integer> map = new HashMap<String,Integer>();

        // this time have to set up a dict. set1 are the keys and the frequency of each key in set 2 is the value
        ArrayList<String> set1 = new ArrayList<String>();
        ArrayList<String> set2 = new ArrayList<String>();

        try (Stream<String> lines = Files.lines(Paths.get("Day1/input.txt"))) {
            lines
                .map(l -> l.split("   "))
                .forEach(a -> {
                    set1.add(a[0]);
                    set2.add(a[1]);
                });
        }
        set1.forEach(item -> map.put((String) item,0));
        set2.forEach(item -> {
            if (map.containsKey(item)) {
                int c = (int) map.get(item);
                c+=1;
                map.put((String) item,c);
            }
        });
        
        for (int i = 0; i < set1.size(); i++) {
            String item = (String) set1.get(i);
            int keyValue = Integer.parseInt(item);
            int multiplier = map.get(item);
            int total = keyValue * multiplier;
            count += total;
        };
        
        System.out.println(count);   
    }
}
