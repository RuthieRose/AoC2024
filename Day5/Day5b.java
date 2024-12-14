package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// strategy is to create an array list. The page we are looking to validate is the index. all first pages require 0 at the index. 
// there will be another array list that holds all of the pages already traversed for that set.

public class Day5b {
    public static void main (String[] args) throws IOException {

        List<Integer>[] pageRequirements = new ArrayList[100];

        for (int i = 0; i < pageRequirements.length; i++) pageRequirements[i] = new ArrayList<Integer>();

        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Day5/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String[] pages = line.split("\\|");

                Integer beforePage = Integer.parseInt(pages[0]);
                Integer afterPage = Integer.parseInt(pages[1]);

                pageRequirements[afterPage].add(beforePage);
            }
        }

        //for (int i = 0; i < pageRequirements.length; i++) {
        //    System.out.println( "page " + i + " has to have " + pageRequirements[i] + " come first");
        //}

        try (BufferedReader reader = new BufferedReader(new FileReader("Day5/input2.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String[] pageSet = line.split(",");
                
                Boolean valid = true;

                for (int i = 0; i < pageSet.length; i++) {
                    int workingPage = Integer.parseInt(pageSet[i]);
                    if (pageRequirements[workingPage].size() > 0) {
                        List<Integer> workingPageRequiredPreviousPages = pageRequirements[workingPage];
                        for (int k = i+1; k < pageSet.length; k++) {
                            int pageAfter = Integer.parseInt(pageSet[k]);
                            if (workingPageRequiredPreviousPages.contains(pageAfter)) valid = false;
                        }
                        // if any page after working page is in the pageRequirements then it is not a valid update so valid = false;
                    }
                }

                if (!valid) {
                    
                    List<Integer> aux = new ArrayList<Integer>();

                    for (int i = 0; i < pageSet.length; i++) {
                        int num = Integer.parseInt(pageSet[i]);
                        aux.add(num);
                    }         
                    
                    int pointer1 = 0;
                    // try to swap the incorrect page numbers and see what happens
                    
                    while (pointer1 < aux.size()) {
                        int currentPage = aux.get(pointer1);
                        for (int k = pointer1; k < aux.size(); k++) {
                            int nextPage = aux.get(k);
                            if (pageRequirements[currentPage].size() > 0) {
                                if (pageRequirements[currentPage].contains(nextPage)) {
                                    System.out.println("Swapping " + currentPage + " " + nextPage);
                                    aux.set(pointer1,nextPage);
                                    aux.set(k,currentPage);
                                    pointer1 = pointer1 - 1;
                                    break;
                                }
                            }
                        }
                        pointer1+=1;
                    }

                    System.out.println("Start");
                    for (int i = 0; i < aux.size(); i++) System.out.println(aux.get(i));
                    System.out.println("Finish");

                    int l = aux.size();
                    int m = l/2;
                    System.out.println("Total is " + total + " adding " + aux.get(m));
                    total += aux.get(m);
                    System.out.println("Total is now " + total);
                }

/*
                if (valid) {
                    int l = pageSet.length;
                    int m = l/2;
                    total += Integer.parseInt(pageSet[m]);
                }

        
*/
            }
    System.out.println(total);
  }

}
}
