package br.upe.ppsw.jabberpoint.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.model.Presentation;

public interface SaveAcessor extends Acessor{
  void saveFile(Presentation presentation, String fileName) throws IOException;
}
