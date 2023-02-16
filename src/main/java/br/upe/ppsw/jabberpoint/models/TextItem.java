package br.upe.ppsw.jabberpoint.models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItem extends SlideItem {

  private String text;

  private static final String EMPTYTEXT = "No Text Given";

  public TextItem(int level, String string) {
    super(level);
    text = string;
  }

  public TextItem() {
    this(0, EMPTYTEXT);
  }

  public String getText() {
    return text == null ? "" : text;
  }

  public AttributedString getAttributedString(Style style, float scale) {
    AttributedString attrStr = new AttributedString(getText());

    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

    return attrStr;
  }

  public List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
    List<TextLayout> layouts = new ArrayList<TextLayout>();

    AttributedString attrStr = getAttributedString(s, scale);
    Graphics2D g2d = (Graphics2D) g;

    FontRenderContext frc = g2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

    float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

    while (measurer.getPosition() < getText().length()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      layouts.add(layout);
    }

    return layouts;
  }

  public String toString() {
    return "TextItem[" + getLevel() + "," + getText() + "]";
  }

}
