package Day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day9 {

    /* example 12345
     odd num is size of the file - 1,3,5
     even num is size of free space - 2,4
     count of file is 0-indexed

    example: 12345

    0..111....22222

    Move the last position first into free spaces:

    0..111....22222
    02.111....2222.
    022111....222..
    0221112...22...
    02211122..2....
    022111222......
     
    Calculate the checksum :
    0*0 + 2*1 + 2 * 2 + 1 * 3 + 1 * 4 + 1 * 5 + 6 * 2 + 7 * 2 + 8 * 2 = ___
    
    Add up the checksums for each line 
     
     */

    
    public static void main (String[] args) throws IOException {

        long total_checksum = 0L;
        int fileNumCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Day9/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();

                String[] arr = line.split("");
                List<String> filesystem = new ArrayList<String>();

                for (int i = 0; i < arr.length; i++) {
                    //even num is size of free space - 2,4
                    if (i % 2 == 1) {
                        // count is the element itself
                        int count = (int) Integer.parseInt(arr[i]);
                        for (int j = 0; j < count; j++) {
                            filesystem.add(".");
                        }
                    }
                    //odd num is size of the file - 1,3,5
                    else {
                        int count = (int) Integer.parseInt(arr[i]);
                        for (int j= 0; j < count; j++) {
                            filesystem.add(Integer.toString(fileNumCount));
                        }
                        fileNumCount+=1;
                    }
                }

                List<String> aux = new ArrayList<String>();

                int pointer = filesystem.size() - 1;

                for (int i = 0; i < filesystem.size(); i++) {
                    if (pointer < i) break;
                    String item = filesystem.get(i);
                    if (!item.equals(".")) {
                        aux.add(item);
                    }
                    else {
                        String lastItem = filesystem.get(pointer);
                        while (lastItem.equals(".")) {
                            pointer -= 1;
                            lastItem = filesystem.get(pointer);
                        }
                        aux.add(lastItem);
                        pointer -= 1;
                    }
                    
                }

                // System.out.println(aux.toString());
                // compute checksum

                for (int i = 0; i < aux.size(); i++) {
                    long item = Integer.parseInt(aux.get(i));
                    long total = item * i;
                    total_checksum += total;

                }

                System.out.println(filesystem);
            };    
        }

        
        System.out.println(total_checksum);
    }

}

