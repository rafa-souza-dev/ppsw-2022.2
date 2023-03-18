package br.upe.ppsw.jabberpoint.use_cases.models.presentation;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.models.Presentation;

public interface ISaveAcessor {
  void saveFile(Presentation presentation, String fileName) throws IOException;
}
