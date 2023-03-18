package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.SlideItem;

public interface IBoundingBoxGeneratorStrategy {
    Rectangle handle(
        SlideItem slideItem, Graphics g, ImageObserver observer, 
        float scale, ComponentsStyler style
    ); 
}
