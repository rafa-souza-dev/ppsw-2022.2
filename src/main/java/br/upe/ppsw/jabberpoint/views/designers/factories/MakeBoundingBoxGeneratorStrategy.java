package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.views.designers.IBoundingBoxGeneratorStrategy;

public class MakeBoundingBoxGeneratorStrategy implements IMakeBoundingBoxGeneratorStrategy {
    private SlideItem slideItem;

    public MakeBoundingBoxGeneratorStrategy(SlideItem slideItem) {
        this.slideItem = slideItem;
    }

    public IBoundingBoxGeneratorStrategy handle() {
        IMakeBoundingBoxGeneratorStrategy makeBoundingBoxGeneratorStrategy = null;

        if (this.slideItem instanceof TextItem) {
            makeBoundingBoxGeneratorStrategy = new MakeTextItemBoundingBoxGeneratorStrategy();
        } else if (this.slideItem instanceof BitmapItem) {
            makeBoundingBoxGeneratorStrategy = new MakeBitmapItemBoundingBoxGeneratorStrategy();
        }

        return makeBoundingBoxGeneratorStrategy.handle();
    }

    public void setSlideItem(SlideItem slideItem) {
        this.slideItem = slideItem;
    }
}
