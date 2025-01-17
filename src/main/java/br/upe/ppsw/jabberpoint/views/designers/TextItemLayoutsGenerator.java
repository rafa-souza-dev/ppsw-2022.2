package br.upe.ppsw.jabberpoint.views.designers;

import br.upe.ppsw.jabberpoint.models.TextItem;

import java.awt.font.TextLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItemLayoutsGenerator {
    private TextItem textItem;

    public TextItemLayoutsGenerator(TextItem textItem) {
        this.textItem = textItem;
    }

    public List<TextLayout> handle(Graphics g, ComponentsStyler s, float scale) {
        List<TextLayout> layouts = new ArrayList<TextLayout>();

        AttributedString attrStr = this.getAttributedString(s, scale);
        Graphics2D g2d = (Graphics2D) g;

        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

        float wrappingWidth = (SlideDesigner.WIDTH - s.indent) * scale;

        while (measurer.getPosition() < this.textItem.getText().length()) {
        TextLayout layout = measurer.nextLayout(wrappingWidth);
        layouts.add(layout);
        }

        return layouts;
    }

    private AttributedString getAttributedString(ComponentsStyler style, float scale) {
        AttributedString attrStr = new AttributedString(this.textItem.getText());
    
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.textItem.getText().length());
    
        return attrStr;
    }
}
