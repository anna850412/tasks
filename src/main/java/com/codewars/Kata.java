package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Kata {
//    public static String solution(String str) {
//        String result = Arrays.stream(str.split("",1))
//                .map(n->new StringBuilder(str).reverse())
//                .collect(Collectors.joining());
//
//        return result;
//    }
    public static String reverseWords(final String original) {
        String result = Arrays.stream(original.split(" ", 1))
                .map(n -> new StringBuilder(original).reverse())
                .collect(Collectors.joining(""));

        return result;

    }
    public static void main(String[] args) {
        String iWasThere = reverseWords("And I was there watching the view");
//        String iWasThere = solution("dlrow");
        System.out.println(iWasThere);
    }
}