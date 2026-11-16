package ax.ha.pp1.wordgame;

import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.Objects;

/**
 * Spelreglerna: kontrollerar ord och jämför en gissning med svaret.
 *
 * Kodskelettet kompilerar men är inte implementerat. Skriv testerna i del A först,
 * och implementera sedan metoderna i del B. Signaturerna får inte ändras.
 */
public class Evaluator {
    public static final int WORD_LENGTH = 5;

    /** Sant om ordet har fem bokstäver ur alfabetet A–Ö, versaler eller gemener. */
    public static boolean isValidWord(String word) {
        String[] splitWord = word.toUpperCase().split("");
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Å", "Ä", "Ö"};

        if (splitWord.length != WORD_LENGTH) return false;

        for (String i : splitWord)
        {
            int l = 0;
            boolean exists = false;
            while (l < alphabet.length)
            {
                if (i.equals(alphabet[l]))
                {
                    exists = true;
                    break;
                }

                l++;
            }

            if (!exists) return false;

        }
        return true;
    }

    /**
     * Jämför gissningen med svaret. Versaler och gemener räknas lika.
     * Kastar IllegalArgumentException om gissningen eller svaret inte är ett giltigt ord.
     */
    public static GuessResult evaluate(String guess, String answer) {
        if (!Evaluator.isValidWord(guess)) throw new IllegalArgumentException("Invalid guess");
        guess = guess.toUpperCase();
        String[] splitGuess = guess.split("");
        String[] splitAnswer = answer.split("");

        int[] marks = new int[WORD_LENGTH];
        boolean[] checkedLetters = new boolean[WORD_LENGTH];

        for (int i = 0; i < WORD_LENGTH; i++)
        {
            if (splitGuess[i].equals(splitAnswer[i]))
            {
                marks[i] = 2;
                checkedLetters[i] = true;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (marks[i] == 0)
            {
                for (int k = 0; k < WORD_LENGTH; k++)
                {
                    if (!checkedLetters[k] && splitGuess[i].equals(splitAnswer[k]))
                    {
                        marks[i] = 1;
                        checkedLetters[k] = true;
                        break;
                    }
                }
            }
        }

        return new GuessResult(guess, marks);
    }
}
