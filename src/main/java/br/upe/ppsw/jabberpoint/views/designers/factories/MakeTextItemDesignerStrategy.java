package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.views.designers.ISlideItemDesignerStrategy;
import br.upe.ppsw.jabberpoint.views.designers.TextItemDesignerStrategy;

public class MakeTextItemDesignerStrategy implements IMakeSlideItemDesignerStrategy {
    public ISlideItemDesignerStrategy handle() {
        return new TextItemDesignerStrategy();
    }
}
