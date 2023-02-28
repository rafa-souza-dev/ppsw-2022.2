package br.upe.ppsw.jabberpoint.views;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;

import java.util.List;
import java.util.Iterator;

public class SlideItemDesigner {
    private SlideItem slideItem;

    public SlideItemDesigner(SlideItem slideItem) {
        this.slideItem = slideItem;
    }

    public void execute(
        int x, int y, float scale, Graphics g, ComponentsStyler style,
        ImageObserver observer
    ) {
        if (this.slideItem instanceof BitmapItem) {
            int width = x + (int) (style.indent * scale);
            int height = y + (int) (style.leading * scale);
        
            g.drawImage(((BitmapItem) this.slideItem).getBufferedImage(), width, height, (int) (((BitmapItem) this.slideItem).getBufferedImage().getWidth(observer) * scale),
                (int) (((BitmapItem) this.slideItem).getBufferedImage().getHeight(observer) * scale), observer);        
        }

        if (this.slideItem instanceof TextItem) {
            if (((TextItem) this.slideItem).getText() == null || 
            ((TextItem) this.slideItem).getText().length() == 0) return;
          
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

    public void setSlideItem(SlideItem slideItem) {
        this.slideItem = slideItem;
    }
}
