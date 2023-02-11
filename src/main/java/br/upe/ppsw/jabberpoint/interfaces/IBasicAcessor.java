package br.upe.ppsw.jabberpoint.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.model.Presentation;

public interface IBasicAcessor {
  String DEFAULT_EXTENSION = ".xml";
  void loadFile(Presentation presentation, String fileName) throws IOException;
}
