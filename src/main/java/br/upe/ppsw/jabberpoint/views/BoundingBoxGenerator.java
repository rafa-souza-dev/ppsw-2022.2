package br.upe.ppsw.jabberpoint.views;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.Style;
import br.upe.ppsw.jabberpoint.models.TextItem;

import java.util.List;
import java.util.Iterator;

public class BoundingBoxGenerator {
    private SlideItem slideItem;

    public BoundingBoxGenerator(SlideItem slideItem) {
        this.slideItem = slideItem;
    }

    public Rectangle handler(Graphics g, ImageObserver observer, float scale, Style style) {
        if (slideItem instanceof BitmapItem) {
            return new Rectangle((int) (style.getIndent() * scale), 0,
            (int) (((BitmapItem) this.slideItem).getBufferedImage().getWidth(observer) * scale),
            ((int) (style.getLeading() * scale)) + (int) (((BitmapItem) this.slideItem).getBufferedImage().getHeight(observer) * scale));
        }

        if (slideItem instanceof TextItem) {
            List<TextLayout> layouts = ((TextItem) this.slideItem).getLayouts(g, style, scale);

            int xsize = 0, ysize = (int) (style.getLeading() * scale);

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

            return new Rectangle((int) (style.getIndent() * scale), 0, xsize, ysize);
        }

        return new Rectangle();
    }

    public void setSlideItem(SlideItem slideItem) {
        this.slideItem = slideItem;
    }
}
