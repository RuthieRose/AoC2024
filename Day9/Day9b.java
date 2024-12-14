package Day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day9b {

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

    public static int getSpacesInEmptyBlock(int idx, List<String> filesystem) {
        int spaces = 0;
        while (filesystem.get(idx).equals(".") && idx < filesystem.size()) {
            spaces += 1;
            idx += 1;
        }

        return spaces;
    }

    public static int locateFirstEmptyBlock(int idx, List<String> filesystem) {
        int p = 0;
        for (int i = idx; i < filesystem.size(); i++) {
            if (filesystem.get(i).equals(".")) {
                p = i;
                break;
            }
        }
        return p;
    }
    
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


                int currentId = fileNumCount - 1;
                
                //System.out.println("currentId: " + currentId);
                // while currentId is greater than 0 AND current available empty block is to the left of the currentId

                while (currentId >= 0) {
                    
                    //System.out.println("Inside first while block");

                    // get currentId
                    String currentIdString = String.valueOf(currentId);
                    //System.out.println(currentIdString);

                    // get first index and last index and total block length
                    int firstIndexOfCurrentId = filesystem.indexOf(currentIdString);
                    int lastIndexOfCurrentId = filesystem.lastIndexOf(currentIdString);
                    //System.out.println(filesystem.toString());
                    //System.out.println("The first index of currentId " + currentId + ": " + firstIndexOfCurrentId);
                    //System.out.println("The last index of currentId " + currentId + ": " + lastIndexOfCurrentId);

                    int numBlocksNeededForCurrentId = lastIndexOfCurrentId - firstIndexOfCurrentId + 1;

                    //System.out.println("Number of blocks needed for currentId " + currentId + ": "  + numBlocksNeededForCurrentId);

                    // search for blocks that will accommodate the id block length
                    int emptyBlocksPointer = locateFirstEmptyBlock(0, filesystem);
                    while (emptyBlocksPointer < firstIndexOfCurrentId) {

                        //System.out.println("The emptyBlocksPointer is at: " + emptyBlocksPointer);
                        int availableBlockCount = getSpacesInEmptyBlock(emptyBlocksPointer, filesystem);

                        //System.out.println("blocks available: " + availableBlockCount);

                        // relocate the items 
                        if (numBlocksNeededForCurrentId <= availableBlockCount) {
                            for (int i = emptyBlocksPointer, j = firstIndexOfCurrentId; j <= lastIndexOfCurrentId; i++, j++) {
                                filesystem.set(i,currentIdString);
                                filesystem.set(j, ".");
                            }
                            break;
                        }
                        else emptyBlocksPointer = locateFirstEmptyBlock(emptyBlocksPointer+1, filesystem);
                    }
                
                    currentId -=1 ;
                }

                

                // System.out.println(aux.toString());
                // compute checksum

                for (int i = 0; i < filesystem.size(); i++) {
                    String fsItem = filesystem.get(i);
                    if (!fsItem.equals(".")) {
                        long item = Integer.parseInt(filesystem.get(i));
                        long total = item * i;
                        total_checksum += total;
                    }
                }

                //System.out.println(filesystem);
            };    
        }

        
        System.out.println(total_checksum);
    }

}

