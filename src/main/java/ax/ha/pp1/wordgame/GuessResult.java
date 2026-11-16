package ax.ha.pp1.wordgame;

import java.util.Arrays;

/**
 * Resultatet av en gissning: gissningens bokstäver och en markering per bokstav.
 * Klassen ska vara oföränderlig.
 *
 * Kodskelettet kompilerar men är inte implementerat. Skriv testerna i del A först,
 * och implementera sedan klassen i del B. Signaturerna får inte ändras.
 */
public final class GuessResult {
    public static final int ABSENT = 0;     // finns inte (fler gånger) i ordet
    public static final int MISPLACED = 1;  // finns, men på en annan plats
    public static final int CORRECT = 2;    // rätt bokstav på rätt plats

    private final char[] guess;
    private final int[] marks;

    /**
     * Skapar ett resultat. Kastar IllegalArgumentException om längderna skiljer sig
     * eller om en markering är ogiltig.
     */
    public GuessResult(String guess, int[] marks) {

        if (marks.length == 5) {
            for (int i = 0; i < marks.length; i++)
            {
                if (marks[i] != 1 && marks[i] != 2 && marks[i] != 0)
                {
                    throw new IllegalArgumentException("Invalid parameters in marks");
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Marks must be of length 5");
        }

        this.guess = guess.toCharArray();
        this.marks = Arrays.copyOf(marks, marks.length);
    }

    /** Gissningen med versaler, t.ex. "ALLAS". */
    public String getGuess() {
        return "" + guess[0] + guess[1] + guess[2] + guess[3] + guess[4];
    }

    /** Antal bokstäver. */
    public int length() {
        return guess.length;
    }

    /** Bokstaven på plats index (0–4). */
    public char letterAt(int index) {
        return guess[index];
    }

    /** Markeringen på plats index: ABSENT, MISPLACED eller CORRECT. */
    public int markAt(int index) {
        return marks[index];
    }

    /** Alla markeringar, t.ex. {1, 1, 2, 1, 0}. */
    public int[] getMarks() {
        return Arrays.copyOf(marks, marks.length);
    }

    /** Sant om alla bokstäver är CORRECT. */
    public boolean isCorrect() {
        Boolean returnBool = true;
        for (int i = 0; i < guess.length; i++)
        {
            if (marks[i] != 2)
            {
                returnBool = false;
            }
        }

        return returnBool;
    }

    /** Till exempel (A)(L)[L](A)-S- för gissningen ALLAS mot svaret KALLA. */
    @Override
    public String toString() {
        String guessString = "";
        for (int i=0; i < guess.length; i++)
        {
            if (marks[i] == 0)
            {
                guessString += "-" + guess[i] + "-";
            }
            else if (marks[i] == 1)
            {
                guessString += "(" + guess[i] + ")";
            }
            else if (marks[i] == 2)
            {
                guessString += "[" + guess[i] + "]";
            }
        }

        return guessString;
    }
}
