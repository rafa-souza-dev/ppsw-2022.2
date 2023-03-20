package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.views.designers.factories.MakeBoundingBoxGeneratorStrategy;
import br.upe.ppsw.jabberpoint.views.designers.factories.MakeSlideItemDesignerStrategy;

public class SlideDesigner {
    private Slide slide;

    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideDesigner(Slide slide) {
        this.slide = slide;
    }

    public void execute(Graphics g, Rectangle area, ImageObserver view) {
        float scale = this.getScale(area);
    
        int y = area.y;
    
        SlideItem slideItem = this.slide.getTitle();
        MakeSlideItemDesignerStrategy makeSlideItemDesignerStrategy = new MakeSlideItemDesignerStrategy(slideItem);
        MakeBoundingBoxGeneratorStrategy makeBoundingBoxGeneratorStrategy = new MakeBoundingBoxGeneratorStrategy(slideItem);
        ComponentsStyler style = ComponentsStyler.getStyle(slideItem.getLevel());

        makeSlideItemDesignerStrategy.handle().execute(slideItem, area.x, y, scale, g, style, view);
        y += makeBoundingBoxGeneratorStrategy.handle().handle(slideItem, g, view, scale, style).height;
        
        for (int number = 0; number < this.slide.getSize(); number++) {
          slideItem = (SlideItem) this.slide.getSlideItems().elementAt(number);

          makeSlideItemDesignerStrategy.setSlideItem(slideItem);
          makeBoundingBoxGeneratorStrategy.setSlideItem(slideItem);

          style = ComponentsStyler.getStyle(slideItem.getLevel());

          makeSlideItemDesignerStrategy.handle().execute(slideItem, area.x, y, scale, g, style, view);
    
          y += makeBoundingBoxGeneratorStrategy.handle().handle(slideItem, g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH),
            ((float) area.height) / ((float) HEIGHT));
    }
}
