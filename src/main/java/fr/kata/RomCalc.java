package fr.kata;

public class RomCalc {

    //    I : 1
    //    V : 5
    //    X : 10
    //    L : 50
    //    C : 100
    //    D : 500
    //    M : 1000

    private char[] romanValues = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    private final String[] romanSingles =      {"VV", "LL", "DD"};
    private final String[] romanSinglesEquiv = {"X",  "C",  "M"};

    private final String[] romanSubstractives =      {"IV",   "IX",    "XL",   "XC",    "CD",   "CM"};
    private final String[] romanSubstractivesEquiv = {"IIII", "VIIII", "XXXX", "LXXXX", "CCCC", "DCCCC"};

    public String add(String a, String b) {

        System.out.println("\nInput : " + a + " + " + b);

        a = replaceSubstractives(a);
        b = replaceSubstractives(b);
        System.out.println("\nSubstractives replaced: " + a + " + " + b);

        String result = regroupNumbers(a, b);
        System.out.println("Regroupement = " + result);

        result = replaceSubstractivesRevert(result);
        System.out.println("Substractives restored = " + result);

        result = replaceForbiddenDoubles(result);
        System.out.println("Explanded res = " + result);

        return result;
    }

    private String replaceForbiddenDoubles(String roman) {

        roman = replaceNumerals(roman, "IVI", "V");
        roman = replaceNumerals(roman, "IXI", "X");
        roman = replaceNumerals(roman, "XCX", "C");
        roman = replaceNumerals(roman, "CDC", "D");

        for (int i = 0; i < romanSingles.length; i++) {
            String romanSingle = romanSingles[i];
            roman = replaceNumerals(roman, romanSingles[i], romanSinglesEquiv[i]);
        }

        return roman;
    }

    private String replaceSubstractives(String a) {
        for (int i = 0; i < romanSubstractives.length; i++) {
            if(a.contains(romanSubstractives[i])){
                a = a.replaceAll(romanSubstractives[i], romanSubstractivesEquiv[i]);
            }
        }
        return a;
    }

    private String replaceSubstractivesRevert(String a) {
        for (int i = 0; i < romanSubstractivesEquiv.length; i++) {
            if(a.contains(romanSubstractivesEquiv[i])){
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

    private String insertNewCharAtOrderedPosition(String input, char c) {
        int charIndex = getRomanPredecessor(input, c);
        String res = insertCharAtPosition(input, c, charIndex);
        return res;
    }

    private int getRomanPredecessor(String input, char c) {
        int i;
        for (i = 0; i < romanValues.length; i++) {
            if (romanValues[i] == c) {
                break;
            }
        }

        int charIndex = input.indexOf(romanValues[i - 1]);
        while (input.indexOf(romanValues[i - 1]) == -1) {
            if (i == 1) break;
            --i;
            charIndex = input.indexOf(romanValues[i - 1]);
        }
        return charIndex;
    }

    private boolean isCharPresentInString(int charPosition) {
        return charPosition != -1;
    }

    private String insertCharAtPosition(String input, char c, int charIndex) {
        input = input.substring(0, charIndex) + c + input.substring(charIndex, input.length());
        return input;
    }

    private String replaceNumerals(String orderedResult, String patternToFind, String patternToReplace) {
        if (orderedResult.contains(patternToFind)) {
            orderedResult = orderedResult.replace(patternToFind, patternToReplace);
            orderedResult = replaceNumerals(orderedResult, patternToFind, patternToReplace);
        }
        return orderedResult;
    }
}
