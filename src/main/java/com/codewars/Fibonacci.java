package com.codewars;

public class Fibonacci {
    public double[] tribonacci(double[] s, int n) {
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < s.length) {
                result[i] = s[i];
            } else {
                result[i] = result[i - 3] + result[i - 2] + result[i - 1];
            }
        }
        return result;
    }

}

//    public static void main(String[] args) {
//        Fibonacci fib = new Fibonacci();
//        int result = fib.tribonacci(10);
//        System.out.println(result);
//
//    }
//}



