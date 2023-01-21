package br.upe.ppsw.jabberpoint.apresentacao.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.apresentacao.Presentation;

public interface LoadAcessor extends Acessor {
  void loadFile(Presentation presentation, String fileName) throws IOException;
}
