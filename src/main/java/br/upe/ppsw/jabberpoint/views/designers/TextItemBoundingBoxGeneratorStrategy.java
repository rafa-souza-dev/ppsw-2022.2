package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

import java.util.List;
import java.util.Iterator;

public class TextItemBoundingBoxGeneratorStrategy implements IBoundingBoxGeneratorStrategy {
    public Rectangle handle(
        SlideItem slideItem, Graphics g, ImageObserver observer, 
        float scale, ComponentsStyler style
    ) {
        TextItemLayoutsGenerator textItemLayoutsGenerator = new TextItemLayoutsGenerator((TextItem) slideItem);
            List<TextLayout> layouts = textItemLayoutsGenerator.handle(g, style, scale);

            int xsize = 0, ysize = (int) (style.leading * scale);

            Iterator<TextLayout> iterator = layouts.iterator();

            while (iterator.hasNext()) {
                TextLayout layout = iterator.next();
                Rectangle2D bounds = layout.getBounds();

                if (bounds.getWidth() > xsize) {
                    xsize = (int) bounds.getWidth();
                }

                if (bounds.getHeight() > 0) {
                    ysize += bounds.getHeight();
                }
                ysize += layout.getLeading() + layout.getDescent();
            }

            return new Rectangle((int) (style.indent * scale), 0, xsize, ysize);
    }
}
