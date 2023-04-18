package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Color;
import java.awt.Font;

public class ComponentsStyler {

  private static ComponentsStyler[] styles;

  private static final String FONTNAME = "Helvetica";
  int indent;
  Color color;
  Font font;
  int fontSize;
  int leading;

  public static void createStyles() {
    styles = new ComponentsStyler[5];
    styles[0] = new ComponentsStyler(0, Color.red, 48, 20); // nível 0
    styles[1] = new ComponentsStyler(20, Color.blue, 40, 10); // nível 1
    styles[2] = new ComponentsStyler(50, Color.black, 36, 10); // nível 2
    styles[3] = new ComponentsStyler(70, Color.black, 30, 10); // nivel 3
    styles[4] = new ComponentsStyler(90, Color.black, 24, 10); // nível 4
  }

  public static ComponentsStyler getStyle(long level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[(int) level];
  }

  public ComponentsStyler(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    font = new Font(FONTNAME, Font.BOLD, fontSize = points);
    this.leading = leading;
  }

  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }
  
}
