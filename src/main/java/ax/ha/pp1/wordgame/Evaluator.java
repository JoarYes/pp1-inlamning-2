package ax.ha.pp1.wordgame;

import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

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
        throw new UnsupportedOperationException("Inte implementerad än");
    }

    /**
     * Jämför gissningen med svaret. Versaler och gemener räknas lika.
     * Kastar IllegalArgumentException om gissningen eller svaret inte är ett giltigt ord.
     * String[] k = guess.split("");
     *
     *         IO.println(Arrays.toString(k));
     *
     *         GuessResult result = new GuessResult("GUESS", new int[] {2, 2, 2, 2, 2});
     *         return result;
     */
    public static GuessResult evaluate(String guess, String answer) {
        throw new UnsupportedOperationException("Inte implementerad än");

    }
}
