package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3c {
    public static void main (String[] args) throws IOException {

        int total = 0;
        
        Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\))|do\\(\\)|don't\\(\\)");   
        List<String> justNums = new ArrayList<String>();
        Pattern numPattern = Pattern.compile("[0-9]{1,3}");

        // use regex mul\([0-9]{1,3},[0-9]{1,3})\) and then add to an array
        // part two have to add in or to get do and don't
        // pop the array to add the multiplied numbers using the do and don't as flags

        try (BufferedReader reader = new BufferedReader(new FileReader("Day3/input.txt"))) {
            while(reader.ready()) {
                
                String line = reader.readLine();
                Matcher matcher = pattern.matcher(line);
                Boolean flag = true;
                List<String> filteredList = new ArrayList<String>();

                while (matcher.find()) {
                    String multSet = matcher.group();
                    filteredList.add(multSet);
                }

                for (int i = 0; i < filteredList.size(); i++) {
                    String item = filteredList.get(i);
                    if (item.equals("don't()")) {
                        flag = false;
                    }
                    else if (item.equals("do()")) flag = true;
                    else if (flag) justNums.add(item);
                }   
            }
          
        }
        List<Integer> nums = new ArrayList<Integer>();

        for (int i = 0; i < justNums.size(); i++) {
            String item = justNums.get(i);
            System.out.println(item);
            Matcher numMatcher = numPattern.matcher(item);
                while (numMatcher.find()) {
                    int num = Integer.parseInt(numMatcher.group());
                    System.out.println(num);
                    nums.add(num);
                }
        }

        for (int i = 0; i < nums.size() -1; i+=2) {
            int num1 = nums.get(i);
            int num2 = nums.get(i+1);
            int subtotal = num1 * num2;
            System.out.println("subtotal " + subtotal);
            total += subtotal;
            System.out.println("total" + " " + total);
        }
        System.out.println(total);
}
}
