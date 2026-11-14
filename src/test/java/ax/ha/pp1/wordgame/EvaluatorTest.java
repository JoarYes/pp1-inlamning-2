package ax.ha.pp1.wordgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {

    // Ett exempel att utgå från. Skriv resten av testerna i del A.
    @Test
    void allLettersCorrectGivesACorrectResult() {
        GuessResult result = Evaluator.evaluate("KALLA", "KALLA");

        assertTrue(result.isCorrect());
        assertEquals("[K][A][L][L][A]", result.toString());
    }

    void oneCorrectLetterInCorrectSpotAndNotCorrectLetters()
    {
        GuessResult result = Evaluator.evaluate("XXXXA", "KRÅKA");

        assertFalse(result.isCorrect());
        assertEquals("-X--X--X--X-[A]", result.toString());
    }

    void oneCorrectLetterInWrongSpot()
    {
        GuessResult result = Evaluator.evaluate("AXXXX", "KRÅKA");

        assertFalse(result.isCorrect());
        assertEquals("(A)-X--X--X--X-", result.toString());
    }

    void twoCorrectSameLettersInCorrectSpot()
    {
        GuessResult result = Evaluator.evaluate("KXXKX", "KRÅKA");

        assertFalse(result.isCorrect());
        assertEquals("[K]-X--X-[K]-X-", result.toString());
    }

    void twoCorrectLettersInCorrectAndWrongSpot()
    {
        GuessResult result = Evaluator.evaluate("XLLXX", "KALLA");

        assertFalse(result.isCorrect());
        assertEquals("-X-(L)[L]-X--X-", result.toString());
    }

    void twoCorrectLettersInWrongSpot()
    {
        GuessResult result = Evaluator.evaluate("XLLXX", "KALLA");

        assertFalse(result.isCorrect());
        assertEquals("(L)(L)-X--X--X-", result.toString());
    }

    void lowerCaseGuess()
    {
        GuessResult result = Evaluator.evaluate("kyrka", "KRÅKA");

        assertEquals("[K]-Y-(R)[K][A]", result.toString());
    }

    void åäöIsValid()
    {
        GuessResult result = Evaluator.evaluate("XXXXÅ", "KRÅKA");
        GuessResult result2 = Evaluator.evaluate("XXXXÄ", "KRÅKA");
        GuessResult result3 = Evaluator.evaluate("XXÖXX", "FROST");

        assertNotEquals("-X--X--X-[A]", result.toString());
        assertNotEquals("-X--X--X--X-[A]", result2.toString());
        assertNotEquals("-X--X-[O]-X--X-", result3.toString());

        assertEquals("-X--X--X--X-(Å)", result.toString());
        assertEquals("-X--X--X--X--Ä-", result2.toString());
        assertEquals("-X--X--Ö--X--X-", result3.toString());
    }

    void tooLongGuess()
    {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> Evaluator.evaluate("XXXXXX", "KRÅKA"));
    }

    void illegalCharacters()
    {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> Evaluator.evaluate("XX?XX", "KRÅKA"));
        IllegalArgumentException result2 = assertThrows(IllegalArgumentException.class, () -> Evaluator.evaluate("XX1XX", "KRÅKA"));
    }

    void testValidWords()
    {
        assertFalse(Evaluator.isValidWord("NONOX"));
        assertTrue(Evaluator.isValidWord("KRÅKA"));
    }
}
