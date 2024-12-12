package com.ruthie.Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Day7b {

    public static List<String[]> generateCombinationsList(String[] operators, int combinationLength) {
        List<String[]> combinations = new ArrayList<>();
        generateCombination(operators, combinationLength, new String[combinationLength], 0, combinations);
        return combinations;
    }

    public static void generateCombination(String[] operators, int combinationLength, String[] currentCombination, int idx, List<String[]> combinations) {
        if (idx == combinationLength) {
            combinations.add(currentCombination.clone());
            return;
        }
        for (String o : operators) {{
            currentCombination[idx] = o;
            generateCombination(operators, combinationLength, currentCombination, idx + 1, combinations);
        }}
    }
    
    public static void main (String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        BigDecimal total = new BigDecimal(0);
        String m = "*";
        String a = "+";
        String c = "c";

        try (BufferedReader reader = new BufferedReader(new FileReader("Day7/input.txt"))) {
            while(reader.ready()) {
                String line = reader.readLine();
                String cleaned = line.trim();
                String[] row = cleaned.split(":");
                BigDecimal targetTotal = new BigDecimal(row[0]);
                String[] row2 = row[1].trim().split(" ");
                BigDecimal[] nums = new BigDecimal[row2.length];
                for (int i = 0; i < nums.length; i++) {
                    BigDecimal num = new BigDecimal(row2[i]);
                    nums[i] = num;
                }
                //System.out.println(Arrays.toString(row2));
                // all possible combinations of * and + and c for given length - 1

                int combinationLength = nums.length - 1;

                String[] o = {m,a,c};

                List<String []> operatorSets = generateCombinationsList(o, combinationLength);


                // for (String[] l : operatorSets) System.out.println(Arrays.toString(l));
 
                for (int k = 0; k < operatorSets.size(); k++) {
                    String[] s = operatorSets.get(k);
                    //System.out.println("operators: " + Arrays.toString(s));
                    //System.out.println("nums: " + Arrays.toString(nums));

                    BigDecimal workingTotal = nums[0];

                    for (int i = 1, j = 0; i < nums.length; i++,j++) {

                        String operator = s[j];
                        BigDecimal num = nums[i];

                        if (operator.equals("*")) {
                            workingTotal = workingTotal.multiply(num);
                            //System.out.println("The oeprator is multiplication: " + workingTotal.toString());
                        }
                        else if (operator.equals("+")) { 
                            workingTotal = workingTotal.add(num);
                            //System.out.println("The oeprator is addition: " + workingTotal.toString());
                        }
                        else if (operator.equals("c")) {
                            // workingTotal converts to string
                            // num converts to string
                            // concatenate
                            // workingTotal then is converted back to big decimal
                            String stringWorkingTotal = workingTotal.toString();
                            String stringNum = num.toString();
                            String newWorkingTotal = stringWorkingTotal.concat(stringNum);
                            workingTotal = new BigDecimal(newWorkingTotal);
                            //System.out.println("The operator is concat: " + workingTotal.toString());
                        }
                    }

                    if (workingTotal.compareTo(targetTotal) == 0) {
                        total = total.add(targetTotal);
                        //System.out.println("This particular num set is correct: " + Arrays.toString(nums));
                        break;
                    }
                };
                
            } 
        }

        long endTime = System.currentTimeMillis();
        System.out.println(total);
        System.out.println("This took " + (endTime - startTime) + " milliseconds");


    }

}

