package br.upe.ppsw.jabberpoint.views.designers;

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
        IBoundingBoxGeneratorStrategy boundingBoxGenerator = null;
        ComponentsStyler style = ComponentsStyler.getStyle(slideItem.getLevel());

        new TextItemDesignerStrategy().execute(slideItem, area.x, y, scale, g, style, view);
        y += new TextItemBoundingBoxGeneratorStrategy().handle(slideItem, g, view, scale, style).height;
        
        for (int number = 0; number < this.slide.getSize(); number++) {
          slideItem = (SlideItem) this.slide.getSlideItems().elementAt(number);

          if (slideItem instanceof TextItem) {
            slideItemDesigner = new TextItemDesignerStrategy();
            boundingBoxGenerator = new TextItemBoundingBoxGeneratorStrategy();
          } else if (slideItem instanceof BitmapItem) {
            slideItemDesigner = new IBitmapItemDesignerStrategy();
            boundingBoxGenerator = new BitmapBoundBoxGeneratorStrategy();
          }

          style = ComponentsStyler.getStyle(slideItem.getLevel());

          slideItemDesigner.execute(slideItem, area.x, y, scale, g, style, view);
    
          y += boundingBoxGenerator.handle(slideItem, g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Slide.WIDTH),
            ((float) area.height) / ((float) Slide.HEIGHT));
    }
}
