package br.upe.ppsw.jabberpoint.views;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.font.TextLayout;
import java.awt.Point;
import java.awt.Graphics2D;

import java.util.List;
import java.util.Iterator;

import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

public class TextItemDesignerStrategy implements ISlideItemDesignerStrategy {
    public void draw(
        SlideItem slideItem, int x, int y, float scale, 
        Graphics g, ComponentsStyler style,
        ImageObserver observer
    ) {
        if (((TextItem) slideItem).getText() == null || 
            ((TextItem) slideItem).getText().length() == 0) return;
          
            TextItemLayoutsGenerator textItemLayoutsGenerator = new TextItemLayoutsGenerator((TextItem) slideItem);
            List<TextLayout> layouts = textItemLayoutsGenerator.handle(g, style, scale);
            Point pen = new Point(x + (int) (style.indent * scale), y + (int) (style.leading * scale));
        
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(style.color);
        
            Iterator<TextLayout> it = layouts.iterator();
          
            while (it.hasNext()) {
                TextLayout layout = it.next();
          
                pen.y += layout.getAscent();
                layout.draw(g2d, pen.x, pen.y);
          
                pen.y += layout.getDescent();
        }
    }
}
