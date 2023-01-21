package br.upe.ppsw.jabberpoint.apresentacao.interfaces;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.apresentacao.Presentation;

public interface SaveAcessor extends Acessor{
  void saveFile(Presentation presentation, String fileName) throws IOException;
}
