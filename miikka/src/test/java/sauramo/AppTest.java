package sauramo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGivenExamples() {
		App app = new App();
		assertEquals(app.muunna("Donald Trump"), "Trunald Domp");
		assertEquals(app.muunna("vuoirkage mäölnö"), "mäörkage vuoilnö");
		assertEquals(app.muunna("death, famine, and pestilence"), "fath, deamine, pend astilence");
		assertEquals(app.muunna("foo bar baz"), "ba foor baz");
		assertEquals(app.muunna("fooma barbu"), "bama foorbu");
		assertEquals(app.muunna("amama  bomomo foo"), "bomama  amomo foo");
		assertEquals(app.muunna("Supercalifragilisticexpialidocious!"), "Supercalifragilisticexpialidocious!");
    assertEquals(app.muunna("I'd rather die here."), "ra'd Ither he diere.");
    assertEquals(app.muunna("qraesar šyvgfvå eo"), "šysar qraevgfvå eo");
    assertEquals(app.muunna("eäšyĸnfn owtxqyuthäuny eomäbw odkkafudhcfiuc"), "ošyĸnfn eäwtxqyuthäuny omäbw eodkkafudhcfiuc");    
	}
}