import java.util.*;

public class Leetcode592 {

public static void main(String[] args) {
     String expression = "-1/2+1/3";
     System.out.printf("Result is: %s", fractionAddition(expression));
    

    

}
public static String fractionAddition(String expression){
   
    //Extract the fractions
    ArrayList<String> fractions = new ArrayList<>();
    int i = 0;
    int n = expression.length();

    while( i < n) {
        int start = i;
        if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
            i++;
        }
        while (i < n && expression.charAt(i) != '+' && expression.charAt(i) != '-') {
            i++;
        }
        fractions.add(expression.substring(start, i));
        System.out.println(expression.substring(start, i));
    }


    //add/subtract the fractions
    String[] firstFraction = fractions.get(0).split("/");
    int totalNumerator = Integer.parseInt(firstFraction[0]);
    int totalDenominator = Integer.parseInt(firstFraction[1]);
    
    //Add fractions one by one
    for(int j=1; j<fractions.size(); j++) {
        String[] parts = fractions.get(j).split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);

        totalNumerator = totalNumerator*denominator + numerator*totalDenominator;
        totalDenominator = totalDenominator * denominator;
    }

    //simplify by gcd
    int commonDivisor = gcd(Math.abs(totalNumerator), totalDenominator);
    totalNumerator /= commonDivisor;
    totalDenominator /= commonDivisor;

    return totalNumerator + "/" + totalDenominator;

}
public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}