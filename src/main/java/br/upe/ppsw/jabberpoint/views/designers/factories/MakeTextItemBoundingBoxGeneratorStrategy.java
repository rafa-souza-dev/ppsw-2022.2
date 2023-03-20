package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.views.designers.IBoundingBoxGeneratorStrategy;
import br.upe.ppsw.jabberpoint.views.designers.TextItemBoundingBoxGeneratorStrategy;

public class MakeTextItemBoundingBoxGeneratorStrategy implements IMakeBoundingBoxGeneratorStrategy {
    public IBoundingBoxGeneratorStrategy handle() {
        return new TextItemBoundingBoxGeneratorStrategy();
    }
}
