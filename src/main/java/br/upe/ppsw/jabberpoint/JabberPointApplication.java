package br.upe.ppsw.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.upe.ppsw.jabberpoint.controllers.PresentationController;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.LoadDemoPresentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.LoadXMLPresentation;
import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.views.designers.ComponentsStyler;

@SpringBootApplication
public class JabberPointApplication implements CommandLineRunner {

  protected static final String IOERR = "IO Error: ";
  protected static final String JABERR = "Jabberpoint Error ";
  protected static final String JABVERSION = "Jabberpoint 1.6 -";

  public static void main(String[] argv) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(JabberPointApplication.class);
    builder.headless(false);
    builder.web(WebApplicationType.NONE);
    builder.run(argv);
  }

  @Override
  public void run(String... args) throws Exception {
    ComponentsStyler.createStyles();

    Presentation presentation = new Presentation();

    PresentationController presentationController = PresentationController.getInstance();

    presentationController.setPresentation(presentation);

    new SlideViewerFrame(JABVERSION, presentation);

    try {
      if (args.length <= 1) {
        new LoadDemoPresentation().execute(presentation, "");
      } else {
        new LoadXMLPresentation().execute(presentation, args[0]);
      }

      presentationController.setSlideNumber(0);

    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
    }
  }

}
