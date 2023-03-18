package br.upe.ppsw.jabberpoint.views.designers;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.BitmapItem;

public class IBitmapItemDesignerStrategy implements ISlideItemDesignerStrategy {
    public void execute(
        SlideItem slideItem, int x, int y, float scale, 
        Graphics g, ComponentsStyler style,
        ImageObserver observer
    ) {
        int width = x + (int) (style.indent * scale);
            int height = y + (int) (style.leading * scale);
        
            g.drawImage(((BitmapItem) slideItem).getBufferedImage(), width, height, (int) (((BitmapItem) slideItem).getBufferedImage().getWidth(observer) * scale),
                (int) (((BitmapItem) slideItem).getBufferedImage().getHeight(observer) * scale), observer);
    }
}
