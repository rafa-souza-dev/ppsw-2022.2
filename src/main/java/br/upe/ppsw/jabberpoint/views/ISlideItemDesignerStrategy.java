package br.upe.ppsw.jabberpoint.views;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.SlideItem;

public interface ISlideItemDesignerStrategy {
    void execute(SlideItem slideItem, int x, int y, float scale, Graphics g, ComponentsStyler style,
    ImageObserver observer);
}
