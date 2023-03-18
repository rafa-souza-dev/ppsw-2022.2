package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.BitmapItem;

public class BitmapBoundBoxGeneratorStrategy implements IBoundingBoxGeneratorStrategy {
    public Rectangle handle(
        SlideItem slideItem, Graphics g, ImageObserver observer, 
        float scale, ComponentsStyler style
    ) {
        return new Rectangle((int) (style.indent * scale), 0,
            (int) (((BitmapItem) slideItem).getBufferedImage().getWidth(observer) * scale),
            ((int) (style.leading * scale)) + (int) (((BitmapItem) slideItem).getBufferedImage().getHeight(observer) * scale));
    }
}
