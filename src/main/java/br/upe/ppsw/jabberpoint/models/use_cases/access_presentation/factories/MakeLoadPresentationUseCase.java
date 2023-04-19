package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.factories;

import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.LoadCSVPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.LoadJSONPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.LoadXMLPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ILoadAcessor;

public class MakeLoadPresentationUseCase {
    private String type;

    public MakeLoadPresentationUseCase(
        String type
    ) {
        this.type = type;
    }

    public ILoadAcessor handle() {
        ILoadAcessor loadAcessor = null;

        if (this.type.equals("xml")) {
            loadAcessor = new LoadXMLPresentation();
        } else if (this.type.equals("json")) {
            loadAcessor = new LoadJSONPresentation();
        } else if (this.type.equals("csv")) {
            loadAcessor = new LoadCSVPresentation();
        }

        return loadAcessor;
    }
}
