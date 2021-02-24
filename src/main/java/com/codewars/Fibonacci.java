package com.codewars;

import java.util.Arrays;

public class Fibonacci {
    public double[] tribonacci(double s, int n) {
        double[] tribonacci1 = tribonacci(1, n - 2);
        double[] tribonacci2 = tribonacci(2, n - 1);
        if (n < 3) {
            return tribonacci(1, 1);
        }
        return tribonacci2 = Arrays.copyOf(tribonacci1, tribonacci1.length);
    }
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        double[] result = fib.tribonacci(6, 6);
        System.out.println(result);

    }
}
//public class Fibonacci {
//    public int tribonacci(int n) {
//        if (n < 3)
//            return 1;
//        return tribonacci(n - 2) + tribonacci(n - 1);
//    }
//int result = fib.tribonacci(6);



