package me.anjoismysign.itd.converter;

import java.util.ArrayList;
import java.util.List;

public class Stringer {

    public static List<String> splitString(String string, int range) {
        List<String> x = new ArrayList<>();
        int length = string.length();
        for (int i = 0; i < length; i += range) {
            x.add(string.substring(i, Math.min(length, i + range)));
        }
        return x;
    }

    public static List<Character> splitStringInCharacters(String string){
        List<Character> x = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            x.add(string.charAt(i));
        }
        return x;
    }
}
