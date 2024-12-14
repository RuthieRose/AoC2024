package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Day11b {

    public static HashMap<String,Long> blink(HashMap<String, ArrayList<String>> stonesMap, HashMap<String,Long> numStonesMap) {

        HashMap<String,Long> aux = new HashMap<String,Long>();

        for (HashMap.Entry<String,Long> entry : numStonesMap.entrySet()) {

            // item itself
            String itemKey = entry.getKey();
            // num of times item is in the array
            long itemCount = entry.getValue();
            ArrayList<String> result = new ArrayList<String>();

            if (stonesMap.containsKey(itemKey)) {
                // get the item that it translates to
                result = stonesMap.get(itemKey);
            }
            // if item is 0 replace by 1
            else if (itemKey.equals("0")) {
                result = new ArrayList<String>();
                result.add("1");
                // add to hashmap
                stonesMap.put(itemKey,result);
             }
             // if item has even number of digits split
            else if (itemKey.length() % 2 == 0) {
                String[] itemSplit = itemKey.split("");
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
                long secondInt = Long.parseLong(String.join("",second));

                // join and add to aux
                String firstString = String.join("", first);
                String secondString = String.valueOf(secondInt);

                result = new ArrayList<String>();
                result.add(firstString);
                result.add(secondString);

                stonesMap.put(itemKey, result);
                }

            else {
                long num = Long.parseLong(itemKey);
                long num2024 = num * 2024;
                String replace = String.valueOf(num2024);
                //System.out.println(num2024 + " " + replace);
                result = new ArrayList<String>();
                result.add(replace);
                stonesMap.put(itemKey, result);
                }

            // check if it is one or two entries
            // multiply by itemCount
            if (result.size() == 1) {
                String newKey = result.get(0);
                if (aux.containsKey(newKey)) {
                    long count = aux.get(newKey);
                    count += itemCount;
                    aux.put(newKey, count);
                }
                else aux.put(newKey, itemCount);
            }
            else if (result.size() == 2) {
                String newKey1 = result.get(0);
                String newKey2 = result.get(1);
                if (aux.containsKey(newKey1)) {
                    long count = aux.get(newKey1);
                    count += itemCount;
                    aux.put(newKey1, count);
                }
                else aux.put(newKey1, itemCount);
                if (aux.containsKey(newKey2)) {
                    long count = aux.get(newKey2);
                    count += itemCount;
                    aux.put(newKey2, count);
                }
                else aux.put(newKey2, itemCount);
            }
        }
        //System.out.println("aux: " + aux.toString());
        return aux;
    }

    public static void main (String[] args) throws IOException {

        HashMap<String,ArrayList<String>> stonesMap = new HashMap<>();
        HashMap<String,Long> numStonesMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Day11/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();

                String[] arr = line.split(" ");
                
                for (int i = 0; i < arr.length; i++) {
                    String item = arr[i];
                    if (numStonesMap.containsKey(item)) {
                        long n = numStonesMap.get(item);
                        n += 1;
                        numStonesMap.put(item, n);
                    }
                    else {
                        numStonesMap.put(item, 1L);
                    }
                }
                
                for (int i = 0; i < 75; i++) {
                    numStonesMap = blink(stonesMap,numStonesMap);
                }

                //System.out.println(stonesMap.toString());
                //System.out.println("numStonesMap: " + numStonesMap.toString());
                
                long items = 0;

                for (String key : numStonesMap.keySet()) {
                    long value = numStonesMap.get(key);
                    System.out.println("key: " + key + " value: " + value);
                    items += value;
                }

                System.out.println(items);

            }
        }

        
        //System.out.println();
    }

}

