package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

public class MakeAccessPresentation {
    private String type;

    public MakeAccessPresentation(String type) {
        this.type = type;
    }

    public IBasicAcessor handle() {
        IBasicAcessor acessor = null;

        if (type.equals("json")) {
            acessor = new AccessJSONPresentation();
        }

        if (type.equals("xml")) {
            acessor = new AcessXMLPresentation();
        }

        return acessor;
    }
}
