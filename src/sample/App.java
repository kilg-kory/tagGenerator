package sample;


import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.util.*;

public class App {
    private static final String DELIMITER = ", ";

    public String concatResult(String string, boolean isRussian, boolean isOnlyDigits, boolean isLastPartHyphen,
                               boolean isLastPartWhitespace, boolean isDirtyLetters) {

        //ГОВНОЗАПЛАТА
        String str = string.toLowerCase();

        HashSet<String> stringHashSet = new HashSet<>();
        stringHashSet.add(str);

        if (isRussian) {
            String addPart = Translit.translit(str);
            if (addPart.length() > 0) {
                stringHashSet.add(addPart);
            }
        }

        if (isOnlyDigits) {
            String addPart = onlyDigits(str);
            if (addPart.length() > 0) {
                stringHashSet.add(addPart);
            }
        }

        if (isLastPartHyphen) {
            String addPart = lastByHyphen(str);
            if (addPart.length() > 0 && !addPart.equals(str)) {
                stringHashSet.add(addPart);
            }
        }

        if (isLastPartWhitespace) {
            String addPart = lastByWhitespace(str);
            if (addPart.length() > 0 && !addPart.equals(str)) {
                stringHashSet.add(addPart);
            }
        }


        if (isLastPartHyphen && isRussian) {
            String addPart = lastByHyphen(Translit.translit(str));
            if (addPart.length() > 0) {
                stringHashSet.add(addPart);
            }
        }


        if (isLastPartWhitespace && isRussian) {
            String addPart = lastByWhitespace(Translit.translit(str));
            if (addPart.length() > 0) {
                stringHashSet.add(addPart);
            }
        }


        if (isDirtyLetters) {
             dirtyLetters(str, stringHashSet);
        }


        StringJoiner joiner = new StringJoiner(DELIMITER);
        stringHashSet.forEach(joiner::add);

        return joiner.toString();
    }

    private String lastByWhitespace(String str) {
        return Iterables.getLast(Splitter.on(CharMatcher.whitespace()).split(str));
    }

    private String lastByHyphen(String str) {
        return Iterables.getLast(Splitter.on("-").split(str));
    }

    private String onlyDigits(String str) {
        return CharMatcher.inRange('0', '9').retainFrom(str);
    }

    // dirty - mix with letters in different language. Like a c k x y e o
    private Set<String> dirtyLetters(String str,Set<String> stringSet) {
        HashMap<Character, Character> dirtyLettersMap = new HashMap<>();
        dirtyLettersMap.put('c', 'С'); // eng c - рус с
        dirtyLettersMap.put('a', 'А'); // eng a - рус а
        dirtyLettersMap.put('k', 'К'); // eng k - рус к
        dirtyLettersMap.put('o', 'О'); // eng o - рус о
        dirtyLettersMap.put('m', 'М'); // eng m - рус м
        dirtyLettersMap.put('e', 'Е'); // eng e - рус е
        dirtyLettersMap.put('x', 'Х'); // eng x - рус х
        dirtyLettersMap.put('y', 'У'); // eng y - рус у
        dirtyLettersMap.put('p', 'Р'); // eng p - рус р
        dirtyLettersMap.put('t', 'т'); // eng t - рус т





        for (int i = 0; i < str.length(); i++) {
            if (dirtyLettersMap.containsKey(str.charAt(i))) {
                char[] chared = str.toCharArray();
                chared[i] = dirtyLettersMap.get(str.charAt(i));
                stringSet.add(new String(chared));
            }
        }

        String mixString = str;

        for (Character s : dirtyLettersMap.keySet()) {
            if (mixString.contains(s.toString())) {
                mixString = mixString.replace(s.toString(), dirtyLettersMap.get(s).toString());
            }
        }

        if (mixString.length() > 0) {
            stringSet.add(mixString);
        }


        return stringSet;
    }
}
