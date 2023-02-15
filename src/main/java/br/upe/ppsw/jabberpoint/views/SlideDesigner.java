package br.upe.ppsw.jabberpoint.views;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.Style;

public class SlideDesigner {
    private Slide slide;

    public SlideDesigner(Slide slide) {
        this.slide = slide;
    }

    public void execute(Graphics g, Rectangle area, ImageObserver view) {
        float scale = this.getScale(area);
    
        int y = area.y;
    
        SlideItem slideItem = this.slide.getTitle();
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
    
        y += slideItem.getBoundingBox(g, view, scale, style).height;
    
        for (int number = 0; number < this.slide.getSize(); number++) {
          slideItem = (SlideItem) this.slide.getSlideItems().elementAt(number);
    
          style = Style.getStyle(slideItem.getLevel());
          slideItem.draw(area.x, y, scale, g, style, view);
    
          y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Slide.WIDTH),
            ((float) area.height) / ((float) Slide.HEIGHT));
    }
}
