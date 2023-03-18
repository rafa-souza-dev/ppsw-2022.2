package br.upe.ppsw.jabberpoint.views;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

public class SlideDesigner {
    private Slide slide;

    public SlideDesigner(Slide slide) {
        this.slide = slide;
    }

    public void execute(Graphics g, Rectangle area, ImageObserver view) {
        float scale = this.getScale(area);
    
        int y = area.y;
    
        SlideItem slideItem = this.slide.getTitle();
        ISlideItemDesignerStrategy slideItemDesigner = null;
        BoundingBoxGenerator boundingBoxGenerator = new BoundingBoxGenerator(slideItem);
        ComponentsStyler style = ComponentsStyler.getStyle(slideItem.getLevel());

        new TextItemDesignerStrategy().draw(slideItem, area.x, y, scale, g, style, view);
    
        y += boundingBoxGenerator.handle(g, view, scale, style).height;
    
        for (int number = 0; number < this.slide.getSize(); number++) {
          slideItem = (SlideItem) this.slide.getSlideItems().elementAt(number);

          if (slideItem instanceof TextItem) {
            slideItemDesigner = new TextItemDesignerStrategy();
          } else if (slideItem instanceof BitmapItem) {
            slideItemDesigner = new IBitmapItemDesignerStrategy();
          }

          boundingBoxGenerator.setSlideItem(slideItem);

          style = ComponentsStyler.getStyle(slideItem.getLevel());

          slideItemDesigner.draw(slideItem, area.x, y, scale, g, style, view);
    
          y += boundingBoxGenerator.handle(g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Slide.WIDTH),
            ((float) area.height) / ((float) Slide.HEIGHT));
    }
}
