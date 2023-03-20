package br.upe.ppsw.jabberpoint.views.designers.factories;

import br.upe.ppsw.jabberpoint.views.designers.BitmapBoundBoxGeneratorStrategy;
import br.upe.ppsw.jabberpoint.views.designers.IBoundingBoxGeneratorStrategy;

public class MakeBitmapItemBoundingBoxGeneratorStrategy implements IMakeBoundingBoxGeneratorStrategy {
    public IBoundingBoxGeneratorStrategy handle() {
        return new BitmapBoundBoxGeneratorStrategy();
    }
}
