package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.models.Presentation;

public interface IBasicAcessor {
  String DEFAULT_EXTENSION = ".xml";
  String DEMO_NAME = "Apresentação de Demonstração";
  void loadFile(Presentation presentation, String fileName) throws IOException;
}
