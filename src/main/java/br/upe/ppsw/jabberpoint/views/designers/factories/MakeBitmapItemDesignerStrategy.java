package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.views.designers.BitmapItemDesignerStrategy;
import br.upe.ppsw.jabberpoint.views.designers.ISlideItemDesignerStrategy;

public class MakeBitmapItemDesignerStrategy implements IMakeSlideItemDesignerStrategy {
    public ISlideItemDesignerStrategy handle() {
        return new BitmapItemDesignerStrategy();
    }
}
