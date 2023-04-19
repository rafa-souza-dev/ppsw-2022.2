package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ILoadAcessor;

public class LoadXMLPresentation implements ILoadAcessor {
    protected static final String DEFAULT_API_TO_USE = "dom";

    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    @Override
    public void execute(Presentation presentation, String fileName) throws IOException {
        int slideNumber, itemNumber, max = 0, maxItems = 0;

        try {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = builder.parse(new File(fileName));

        Element doc = document.getDocumentElement();
        presentation.setTitle(getTitle(doc, SHOWTITLE));

        NodeList slides = doc.getElementsByTagName(SLIDE);
        max = slides.getLength();

        for (slideNumber = 0; slideNumber < max; slideNumber++) {
            Element xmlSlide = (Element) slides.item(slideNumber);

            Slide slide = new Slide();
            slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
            presentation.append(slide);

            NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
            maxItems = slideItems.getLength();

            for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
            Element item = (Element) slideItems.item(itemNumber);
            loadSlideItem(slide, item);
            }
        }

        } catch (IOException iox) {
        System.err.println(iox.toString());
        } catch (SAXException sax) {
        System.err.println(sax.getMessage());
        } catch (ParserConfigurationException pcx) {
        System.err.println(PCE);
        }
    }

    protected void loadSlideItem(Slide slide, Element item) {
        int level = 1;
    
        NamedNodeMap attributes = item.getAttributes();
    
        String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
    
        if (leveltext != null) {
          try {
            level = Integer.parseInt(leveltext);
          } catch (NumberFormatException x) {
            System.err.println(NFE);
          }
        }
    
        String type = attributes.getNamedItem(KIND).getTextContent();
        if (TEXT.equals(type)) {
          slide.append(new TextItem(level, item.getTextContent()));
        } else {
          if (IMAGE.equals(type)) {
            slide.append(new BitmapItem(level, item.getTextContent()));
          } else {
            System.err.println(UNKNOWNTYPE);
          }
        }
    }

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }
}
