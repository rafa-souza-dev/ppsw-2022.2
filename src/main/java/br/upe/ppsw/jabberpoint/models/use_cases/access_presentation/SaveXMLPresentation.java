package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ISaveAcessor;

public class SaveXMLPresentation implements ISaveAcessor {
    @Override
    public void execute(Presentation presentation, String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        out.println("<?xml version=\"1.0\"?>");
        out.println("<presentation>");

        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");

        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
        Slide slide = presentation.getSlide(slideNumber);

        out.println("<slide>");
        out.println("<title>" + slide.getTitleText() + "</title>");

        Vector<SlideItem> slideItems = slide.getSlideItems();
        for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
            SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
            out.print("<item kind=");

            if (slideItem instanceof TextItem) {
            out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((TextItem) slideItem).getText());
            } else {
            if (slideItem instanceof BitmapItem) {
                out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                out.print(((BitmapItem) slideItem).getName());
            } else {
                System.out.println("Ignoring " + slideItem);
            }
            }

            out.println("</item>");
        }

        out.println("</slide>");
        }

        out.println("</presentation>");

        out.close();
    }
}
