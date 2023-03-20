package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.views.designers.ISlideItemDesignerStrategy;

public class MakeSlideItemDesignerStrategy implements IMakeSlideItemDesignerStrategy {
    private SlideItem slideItem;

    public MakeSlideItemDesignerStrategy(SlideItem slideItem) {
        this.slideItem = slideItem;
    }

    public ISlideItemDesignerStrategy handle() {
        IMakeSlideItemDesignerStrategy makeSlideItemDesignerStrategy = null;

        if (slideItem instanceof TextItem) {
            makeSlideItemDesignerStrategy = new MakeTextItemDesignerStrategy();
        } else if (slideItem instanceof BitmapItem) {
            makeSlideItemDesignerStrategy = new MakeBitmapItemDesignerStrategy();
        }

        return makeSlideItemDesignerStrategy.handle();
    }

    public void setSlideItem(SlideItem slideItem) {
        this.slideItem = slideItem;
    }
}
