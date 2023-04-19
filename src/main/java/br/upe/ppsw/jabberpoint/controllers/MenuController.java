package br.upe.ppsw.jabberpoint.controllers;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.factories.MakeLoadPresentationUseCase;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.factories.MakeSavePresentationUseCase;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ILoadAcessor;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ISaveAcessor;
import br.upe.ppsw.jabberpoint.views.DrawAboutBoxDialog;

public class MenuController extends MenuBar {

  private static final long serialVersionUID = 227L;

  private Frame parent;
  private Presentation presentation;

  protected static final String ABOUT = "Sobre";
  protected static final String FILE = "Arquivo";
  protected static final String EXIT = "Sair";
  protected static final String GOTO = "Pular para";
  protected static final String HELP = "Ajuda";
  // protected static final String NEW = "Novo";
  protected static final String NEXT = "Próximo";
  protected static final String OPEN = "Abrir";
  protected static final String PAGENR = "Npumero do Slide?";
  protected static final String PREV = "Anteior";
  protected static final String SAVE = "Salvar";
  protected static final String VIEW = "Visualizar";

  protected static final String TESTFILE = "classpath:test.xml";
  protected static final String SAVEFILE = "classpath:dump.xml";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Erro ao carregar";
  protected static final String SAVEERR = "Erro ao salvar";

  public MenuController(Frame frame, Presentation pres) {
    PresentationController presentationController = PresentationController.getInstance();

    parent = frame;
    presentation = pres;

    MenuItem menuItem;

    Menu fileMenu = new Menu(FILE);
    fileMenu.add(menuItem = mkMenuItem(OPEN));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.showOpenDialog(null);

        File selectedFile = fileChooser.getSelectedFile();

        if (selectedFile != null) {
          String filename = selectedFile.getName();
          String filePath = selectedFile.getAbsolutePath();
          String fileType = "";

          int dotIndex = filename.lastIndexOf('.');

          if (dotIndex > 0) {
            fileType = filename.substring(dotIndex + 1);
          }
          
          presentationController.clear();

          try {
            MakeLoadPresentationUseCase makeLoadPresentationUseCase = new MakeLoadPresentationUseCase(fileType);
            ILoadAcessor loadAcessor = makeLoadPresentationUseCase.handle();

            loadAcessor.execute(presentation, filePath);
            presentationController.setSlideNumber(0);
          } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
          } catch (NullPointerException exc) {
            JOptionPane.showMessageDialog(parent, "É preciso selecionar um arquivo JSON ou XML.", "Tipo de arquivo inválido.", JOptionPane.ERROR_MESSAGE);
          }

          parent.repaint();
        }
      }
    });

    // fileMenu.add(menuItem = mkMenuItem(NEW));

    // menuItem.addActionListener(new ActionListener() {
    //   public void actionPerformed(ActionEvent actionEvent) {
    //     presentationController.clear();
    //     parent.repaint();
    //   }
    // });

    fileMenu.add(menuItem = mkMenuItem(SAVE));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object[] options = { "json", "xml", "csv" };

        Object selectedOption = JOptionPane.showInputDialog(null, "Escolha o tipo do Arquivo", "Selecionar Opção", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione uma pasta");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);

        String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();

        String fileName = JOptionPane.showInputDialog("Digite o nome do arquivo:", "Exemplo: arquivo_de_teste");

        String filePath = selectedPath + "/" + fileName + "." + selectedOption;

        try {
          MakeSavePresentationUseCase makeSavePresentationUseCase = new MakeSavePresentationUseCase((String) selectedOption);
          ISaveAcessor saveAcessor = makeSavePresentationUseCase.handle();

          saveAcessor.execute(presentation, filePath);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    fileMenu.addSeparator();

    fileMenu.add(menuItem = mkMenuItem(EXIT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.exit(0);
      }
    });

    add(fileMenu);

    Menu viewMenu = new Menu(VIEW);
    viewMenu.add(menuItem = mkMenuItem(NEXT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.nextSlide();
      }
    });

    viewMenu.add(menuItem = mkMenuItem(PREV));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.prevSlide();
      }
    });

    viewMenu.add(menuItem = mkMenuItem(GOTO));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentationController.setSlideNumber(pageNumber - 1);
      }
    });

    add(viewMenu);

    Menu helpMenu = new Menu(HELP);
    helpMenu.add(menuItem = mkMenuItem(ABOUT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        DrawAboutBoxDialog.execute(parent);
      }
    });

    setHelpMenu(helpMenu);
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
