package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.factories;

import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.SaveCSVPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.SaveJSONPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.SaveXMLPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ISaveAcessor;

public class MakeSavePresentationUseCase {
    private String type;

    public MakeSavePresentationUseCase(
        String type
    ) {
        this.type = type;
    }

    public ISaveAcessor handle() {
        ISaveAcessor saveAcessor = null;

        if (this.type.equals("xml")) {
            saveAcessor = new SaveXMLPresentation();
        } else if (this.type.equals("json")) {
            saveAcessor = new SaveJSONPresentation();
        } else if (this.type.equals("csv")) {
            saveAcessor = new SaveCSVPresentation();
        }

        return saveAcessor;
    }
}
