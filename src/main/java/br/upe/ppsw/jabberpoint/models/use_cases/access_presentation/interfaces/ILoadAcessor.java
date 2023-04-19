package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.models.Presentation;

public interface ILoadAcessor {
  String DEFAULT_EXTENSION = ".xml";
  String DEMO_NAME = "Apresentação de Demonstração";
  void execute(Presentation presentation, String fileName) throws IOException;
}
