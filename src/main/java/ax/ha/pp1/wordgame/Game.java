package ax.ha.pp1.wordgame;

import java.util.Arrays;

/**
 * En spelomgång: ett hemligt ord och högst sex gissningar.
 * Klassen vet ingenting om dialogrutor.
 *
 * Kodskelettet kompilerar men är inte implementerat, och det har inga fält:
 * vilka fält klassen behöver bestämmer du själv i del B. Signaturerna får inte ändras.
 */
public class Game {
    public static final int MAX_GUESSES = 6;
    private int guessesUsed = 0;
    private final String answer;
    private GuessResult[] guesses = {new GuessResult("XXXXX", new int[5])};

    /** Startar en omgång. Svaret hämtas med source.next(). */
    public Game(WordSource source) {
        answer = source.next();
    }
    /**
     * Gör en gissning och returnerar resultatet. Kastar IllegalArgumentException om
     * ordet är ogiltigt (gissningen räknas då inte), och IllegalStateException om
     * spelet redan är slut.
     */
    public GuessResult guess(String word) {
        if (isWon() || isOver()) throw new IllegalStateException("Game is over");

        guesses = Arrays.copyOf(guesses, guesses.length + 1);
        guesses[guesses.length - 1] = Evaluator.evaluate(word, answer);
        guessesUsed++;
        return Evaluator.evaluate(word, answer);
    }

    /** Sant om senaste gissningen var rätt. */
    public boolean isWon() {
        return guesses[guesses.length - 1].getGuess().equals(answer);
    }

    /** Sant om spelet är slut: vunnet eller sex gissningar gjorda. */
    public boolean isOver() {
        return guessesUsed >= MAX_GUESSES || isWon();
    }

    /** Antal gjorda gissningar. */
    public int getGuessCount() {
        return guessesUsed;
    }

    /** Antal gissningar kvar. */
    public int getGuessesLeft() {
        return MAX_GUESSES - guessesUsed;
    }

    /**
     * Svaret. Kastar IllegalStateException om spelet inte är slut, så att svaret
     * inte kan avslöjas för tidigt.
     */
    public String getAnswer() {
        if (isWon() || isOver())
        {
            return answer;
        }
        else
        {
            throw new IllegalStateException("Game is not over yet");
        }
    }

    /** Spelbrädet: en rad per gissning i formen från GuessResult, med radbrytning efter varje rad. */
    @Override
    public String toString() {
        String returnString = "";

        for (int i = 1; i < guesses.length; i++)
        {
            returnString = new String(returnString + guesses[i].toString() + "\n");
        }

        return returnString;
    }
}
