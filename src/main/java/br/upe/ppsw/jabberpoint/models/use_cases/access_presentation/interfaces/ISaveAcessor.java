package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.models.Presentation;

public interface ISaveAcessor {
  void execute(Presentation presentation, String fileName) throws IOException;
}
