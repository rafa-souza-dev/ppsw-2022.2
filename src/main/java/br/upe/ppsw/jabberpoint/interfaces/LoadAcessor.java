package br.upe.ppsw.jabberpoint.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.domain.Presentation;

public interface LoadAcessor extends Acessor {
  void loadFile(Presentation presentation, String fileName) throws IOException;
}
