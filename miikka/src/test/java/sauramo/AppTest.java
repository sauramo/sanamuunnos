package sauramo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

  @Test
  public void testGivenExamples() {
    App app = new App();
    assertEquals("Trunald Domp", app.muunna("Donald Trump"));
    assertEquals("mäörkage vuoilnö", app.muunna("vuoirkage mäölnö"));
    assertEquals("fath, deamine, pend astilence", app.muunna("death, famine, and pestilence"));
    assertEquals("ba foor baz", app.muunna("foo bar baz"));
    assertEquals("bama foorbu", app.muunna("fooma barbu"));
    assertEquals("bomama  amomo foo", app.muunna("amama  bomomo foo"));
    assertEquals("Supercalifragilisticexpialidocious!", app.muunna("Supercalifragilisticexpialidocious!"));
    assertEquals("ra'd Ither he diere.", app.muunna("I'd rather die here."));
    assertEquals("šysar qraevgfvå eo", app.muunna("qraesar šyvgfvå eo"));
    assertEquals("ošyĸnfn eäwtxqyuthäuny omäbw eodkkafudhcfiuc", app.muunna("eäšyĸnfn owtxqyuthäuny eomäbw odkkafudhcfiuc"));
  }
}