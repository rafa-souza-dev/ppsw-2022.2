package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.FileNotFoundException;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;

public class AcessDemoPresentation implements IBasicAcessor {

  public void loadFile(Presentation presentation, String unusedFilename)
      throws FileNotFoundException {

    presentation.setTitle("Apresentação de Demonstração");

    Slide slide;
    slide = new Slide();

    slide.setTitle("JabberPoint");
    slide.append(1, "Ferramenta de Apresentação de Slides");
    slide.append(2, "Copyright (c) 1996-now: Ian Darwin");
    slide.append(2, "Copyright (c) 2021-now:");
    slide.append(2, "Helaine Barreiros");
    slide.append(4, "JabberPoint execução de demonstração sem arquivos persistidos");
    slide.append(4, "exibição de apresentação com dados apenas em memória");
    slide.append(1, "Navegação:");
    slide.append(3, "Próximo slide: PgDn ou Enter");
    slide.append(3, "Slide Anterior: PgUp ou up-arrow");
    slide.append(3, "Parar: q ou Q");

    presentation.append(slide);

    slide = new Slide();
    slide.setTitle("Demonstração dos níveis e estilos de uma apresentação");
    slide.append(1, "Nível 1");
    slide.append(2, "Nível 2");
    slide.append(1, "Novamente um item de Nível 1");
    slide.append(1, "Nível 1 tem Estilo número 1");
    slide.append(2, "Nível 2 tem Estilo número 2");
    slide.append(3, "Este é um ítem de Nível 3");
    slide.append(4, "E este é um ítem de Nível 4");

    presentation.append(slide);

    slide = new Slide();
    slide.setTitle("Terceiro Slide");
    slide.append(1, "Para abrir uma nova apresentação,");
    slide.append(2, "utilize o menu File->Open.");
    slide.append(1, " ");
    slide.append(1, "Fim da Apresentação");
    slide.append(
      new BitmapItem(1, "JabberPoint.jpg")
    );

    presentation.append(slide);
  }

}
