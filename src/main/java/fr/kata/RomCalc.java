package fr.kata;

public class RomCalc {

    //    I : 1
    //    V : 5
    //    X : 10
    //    L : 50
    //    C : 100
    //    D : 500
    //    M : 1000

    private final char[] romanValues = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    private final String[] romanSingles = {"VV", "LL", "DD"};
    private final String[] romanSinglesEquiv = {"X", "C", "M"};

    private final String[] romanSubstractives = {"CM", "CD", "XC", "XL", "IX", "IV"};
    private final String[] romanSubstractivesEquiv = {"DCCCC", "CCCC", "LXXXX", "XXXX", "VIIII", "IIII"};

    public String add(String a, String b) {

        System.out.println("\nInput : " + a + " + " + b);

        validateRomanNumber(a);
        validateRomanNumber(b);

        a = replaceSubstractives(a);
        b = replaceSubstractives(b);
        System.out.println("Substractives replacement = " + a + " + " + b);

        String result = regroupNumbers(a, b);
        System.out.println("Regroupement =                " + result);

        result = factorisation(result);
        System.out.println("Factorisation =               " + result);

        result = replaceSubstractivesRevert(result);
        System.out.println("Substractives restoration =   " + result);


        return result;
    }

    private String factorisation(String input) {
        for (int i = 0; i < romanValues.length - 1; i++) {
            String currRomanString = String.valueOf(romanValues[i]);
            String pattern = currRomanString + currRomanString + currRomanString + currRomanString + currRomanString;
            char replacement = romanValues[i + 1];
            if (input.contains(pattern)) {
                int patternIndex = input.indexOf(pattern);
                input = input.replaceFirst(pattern, "");
                input = insertCharAtPosition(input, replacement, patternIndex);
            }
        }
        return input;
    }

    private void validateRomanNumber(String a) {
        for (char c : a.toCharArray()) {
            boolean isCurrentCharRomanNumber = false;
            for (char romanValue : romanValues) {
                if (c == romanValue) {
                    isCurrentCharRomanNumber = true;
                }
            }
            if (!isCurrentCharRomanNumber) {
                throw new IllegalArgumentException("Input should be one of the following values");
            }
        }
    }

    private String replaceForbiddenDoubles(String roman) {

        roman = mergeSubstractives(roman);

        for (int i = 0; i < romanSingles.length; i++) {
            roman = replaceNumerals(roman, romanSingles[i], romanSinglesEquiv[i]);
        }

        System.out.println("Replace forbidden doubles =   " + roman);

        return roman;
    }

    private String mergeSubstractives(String roman) {
        roman = replaceNumerals(roman, "IVI", "V");
        roman = replaceNumerals(roman, "IXI", "X");
        roman = replaceNumerals(roman, "XLX", "L");
        roman = replaceNumerals(roman, "XCX", "C");
        roman = replaceNumerals(roman, "CDC", "D");
        roman = replaceNumerals(roman, "CMC", "M");
        return roman;
    }

    private String replaceSubstractives(String a) {
        for (int i = 0; i < romanSubstractives.length; i++) {
            if (a.contains(romanSubstractives[i])) {
                a = a.replaceAll(romanSubstractives[i], romanSubstractivesEquiv[i]);
            }
        }
        return a;
    }

    private String replaceSubstractivesRevert(String a) {
        for (int i = 0; i < romanSubstractivesEquiv.length; i++) {
            if (a.contains(romanSubstractivesEquiv[i])) {
                a = a.replaceAll(romanSubstractivesEquiv[i], romanSubstractives[i]);
            }
        }
        return a;
    }

    private String regroupNumbers(String a, String b) {
        String input = a;
        for (char c : b.toCharArray()) {
            int charPosition = input.indexOf(c);
            if (isCharPresentInString(charPosition)) {
                input = insertCharAtPosition(input, c, charPosition);
            } else {
                input = insertNewCharAtOrderedPosition(input, c);
            }
        }
        return input;
    }

    private boolean isCharPresentInString(int charPosition) {
        return charPosition != -1;
    }

    private String insertCharAtPosition(String input, char charToInsert, int charIndex) {
        String res = input.substring(0, charIndex) + charToInsert + input.substring(charIndex, input.length());
        res = replaceForbiddenDoubles(res);
        return res;
    }

    private String insertNewCharAtOrderedPosition(String input, char charToInsert) {
        int charIndex = findPositionOfPredecessor(input, charToInsert);
        return insertCharAtPosition(input, charToInsert, charIndex);
    }

    private int findPositionOfPredecessor(String input, char c) {
        int i = 0;
        while (romanValues[i] != c) {
            i++;
        }

        int charIndex = input.indexOf(romanValues[i - 1]);
        while (input.indexOf(romanValues[i - 1]) == -1) {
            if (i == 1) break;
            --i;
            charIndex = input.indexOf(romanValues[i - 1]);
        }
        if (charIndex == -1) {
            charIndex = input.length();
        }

        return charIndex;
    }

    private String replaceNumerals(String orderedResult, String patternToFind, String patternToReplace) {
        if (orderedResult.contains(patternToFind)) {
            orderedResult = orderedResult.replace(patternToFind, patternToReplace);
            orderedResult = replaceNumerals(orderedResult, patternToFind, patternToReplace);
        }
        return orderedResult;
    }
}
