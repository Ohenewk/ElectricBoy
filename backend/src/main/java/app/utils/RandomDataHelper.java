package app.utils;

import java.util.Random;

public class RandomDataHelper {

    private static Random random = new Random();

    private static char[] getAlphabet() {
        int charAmount = 'z' - 'a' + 1;
        char[] alphabet = new char[charAmount];
        for (int i = 0; i < charAmount; i++) {
            alphabet[i] = (char) ('a' + i);
        }
        return alphabet;
    }

    public static String string(int length) {
        final char[] alphabet = getAlphabet();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            final char randomLetter = alphabet[random.nextInt(alphabet.length)];
            sb.append(randomLetter);
        }
        return sb.toString();
    }

    public static String gpsLocation() {
        return "52.37403,4.88969";
    }

    public static Random getRandom() {
        return random;
    }
}
