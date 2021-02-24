package com.crud.tasks.service;

import java.util.HashMap;
import java.util.Map;

public class Dnastrand {
    public static String makeComplement(String dna) {

        Map<Character, Character> dnaMap = new HashMap<>();

        dnaMap.put('A', 'T');
        dnaMap.put('T', 'A');
        dnaMap.put('C', 'G');
        dnaMap.put('G', 'C');

        String result = "";
        for (char letter : dna.toCharArray()) {
            result = result + dnaMap.get(letter);
        }
        return result;
    }
}
/*public class DnaStrand {
    public static String makeComplement(String dna) {
        String result = "";

        for(char c : dna.toCharArray()){
            switch(c){
                case 'A':
                    result += 'T';
                    break;
                case 'T':
                    result += 'A';
                    break;
                case 'G':
                    result += 'C';
                    break;
                case 'C':
                    result += 'G';
                    break;
            }
        }
        return result;
    }

}*/

