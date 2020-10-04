package sauramo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

  @Test
  public void testGivenExamples() {
    App app = new App();
    assertEquals("Trunald Domp", app.transform("Donald Trump"));
    assertEquals("mäörkage vuoilnö", app.transform("vuoirkage mäölnö"));
    assertEquals("fath, deamine, pend astilence", app.transform("death, famine, and pestilence"));
    assertEquals("ba foor baz", app.transform("foo bar baz"));
    assertEquals("bama foorbu", app.transform("fooma barbu"));
    assertEquals("bomama  amomo foo", app.transform("amama  bomomo foo"));
    assertEquals("Supercalifragilisticexpialidocious!", app.transform("Supercalifragilisticexpialidocious!"));
    assertEquals("ra'd Ither he diere.", app.transform("I'd rather die here."));
    assertEquals("šysar qraevgfvå eo", app.transform("qraesar šyvgfvå eo"));
    assertEquals("ošyĸnfn eäwtxqyuthäuny omäbw eodkkafudhcfiuc", app.transform("eäšyĸnfn owtxqyuthäuny eomäbw odkkafudhcfiuc"));
  }
}