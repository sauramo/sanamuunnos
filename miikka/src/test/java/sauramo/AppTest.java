package sauramo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
	// For each pair of words, swap the beginnings of the words, up to and
	// including
	// the first vowel (and any consecutive vowels) of the word.
	// Example: "fooma barbu" becomes "bama foorbu".
	// If there is an odd word at the end of the string, leave that as is.
	// Example: "hello" becomes "hello".
	// Example: "foo bar baz" becomes "ba foor baz".
	// Words are separated by spaces; please preserve exact spacing.
	// Example: "amama bomomo foo" becomes "bomama amomo foo"
	// You can treat punctuation as part of words.
	// Example: "I'd rather die here." becomes "ra'd Ither he diere."
	// Vowels include (at least) those in the Finnish alphabet (a, e, i, o, u,
	// y, å,
	// ä, ö).
	// Example: "vuoirkage mäölnö" becomes "mäörkage vuoilnö".
	// You can treat as a consonant any character that is not space or a vowel.
	// "Donald Trump" "Trunald Domp"
	// "Supercalifragilisticexpialidocious!"
	// "Supercalifragilisticexpialidocious!"
	// "death, famine, and pestilence" "fath, deamine, pend astilence"
	@Test
	public void testGivenExamples() {
		App app = new App();
		assertEquals(app.muunna("Donald Trump"), "Trunald Domp");
		assertEquals(app.muunna("vuoirkage mäölnö"), "mäörkage vuoilnö");
		assertEquals(app.muunna("death, famine, and pestilence"), "fath, deamine, pend astilence");
		assertEquals(app.muunna("foo bar baz"), "ba foor baz");
		assertEquals(app.muunna("fooma barbu"), "bama foorbu");
		assertEquals(app.muunna("amama bomomo foo"), "bomama amomo foo");
		assertEquals(app.muunna("Supercalifragilisticexpialidocious!"), "Supercalifragilisticexpialidocious!");
    assertEquals(app.muunna("I'd rather die here."), "ra'd Ither he diere.");
	}
}
