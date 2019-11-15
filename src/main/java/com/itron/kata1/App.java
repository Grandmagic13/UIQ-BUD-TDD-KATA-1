package com.itron.kata1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    public static final String NEGATIVES_NOT_ALLOWED = "negatives not allowed";

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static int add(String numbers) {

        if (numbers.isEmpty())
            return 0;

        String delimiter = ",|\\n";
        if (hasDelimiter(numbers)) {
            delimiter = getSanitizedDelimiter(numbers);
            numbers = numbers.substring(4);
        }

        return getSum(numbers, delimiter);
    }

    private static String getSanitizedDelimiter(String numbers) {
        char delimiter = numbers.charAt(2);
        if (delimiter != '[') {
            return Pattern.quote((String.valueOf(delimiter)));
        } else {
            int end = numbers.indexOf(']');
            if(end == -1){
                return Pattern.quote((String.valueOf(delimiter)));
            }
            return Pattern.quote(numbers.substring(3, end));
        }
    }

    private static int getSum(String numbers, String delimiter) {
        ArrayList<Integer> negatives = new ArrayList<>();
        Integer sum = Arrays.stream(numbers.split(delimiter))
                .map((String number) -> {
                    Integer tmp = Integer.valueOf(number);
                    if (tmp < 0)
                        negatives.add(tmp);
                    return tmp;
                })
                .filter((Integer num) -> num <= 1000)
                .reduce(0, Integer::sum);
        if (negatives.size() > 0)
            throw new IllegalArgumentException(NEGATIVES_NOT_ALLOWED + ": " + String.join(", ", negatives.toString()));
        return sum;
    }

    private static boolean hasDelimiter(String numbers) {
        return numbers.length() > 3 && numbers.substring(0, 2).equals("//");
    }
}
