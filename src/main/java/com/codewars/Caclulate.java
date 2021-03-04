package com.codewars;

public class Caclulate {
    public static String bmi(double weight, double height) {
        double result = weight/(height*height);

        if(result<=18.8){
            System.out.println("Underweight");
        } else if (result <=25) {
            System.out.println("Normal");
        } else if (result<=30){
            System.out.println("Overweight");
        } else if (result>30){
            System.out.println("Obese");
        } return new String();
    }
    public static void main (String [] args){
        Caclulate caclulate = new Caclulate();
        System.out.println(Caclulate.bmi(80,1.80));
    }
}
