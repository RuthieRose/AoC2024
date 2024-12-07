package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day7 {
    
    public static void main (String[] args) throws IOException {

        BigDecimal total = new BigDecimal(0);
        String m = "*";
        String a = "+";

        try (BufferedReader reader = new BufferedReader(new FileReader("Day7/sample.txt"))) {
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

                // all possible combinations of * and + for given length - 1

                List<String[]> operatorSets = new ArrayList<String[]>();

                int possibleCombinations = (int) Math.pow(2, nums.length-1);

                for (int i = 0; i < possibleCombinations; i++) {
                    String[] operatorCombination = new String[nums.length-1];
                    for (int j = 0; j < nums.length-1; j++) {
                        operatorCombination[j] = ((i >> j) & 1) == 0 ? m : a;
                    }
                    operatorSets.add(operatorCombination);
                }

                for (int k = 0; k < operatorSets.size(); k++) {
                    String[] s = operatorSets.get(k);
                    System.out.println("operators: " + Arrays.toString(s));
                    System.out.println("nums: " + Arrays.toString(nums));

                    BigDecimal workingTotal = nums[0];

                    for (int i = 1, j = 0; i < nums.length; i++,j++) {

                        String operator = s[j];
                        BigDecimal num = nums[i];

                        if (operator.equals("*")) {
                            workingTotal = workingTotal.multiply(num);
                            System.out.println("The oeprator is multiplication: " + workingTotal.toString());
                        }
                        else if (operator.equals("+")) { 
                            workingTotal = workingTotal.add(num);
                            System.out.println("The oeprator is addition: " + workingTotal.toString());
                        }
                    }

                    if (workingTotal.compareTo(targetTotal) == 0) {
                        total = total.add(targetTotal);
                        System.out.println("This particular num set is correct: " + Arrays.toString(nums));
                        break;
                    }
                };
                
            }
        }
        System.out.println(total);
    }

}

