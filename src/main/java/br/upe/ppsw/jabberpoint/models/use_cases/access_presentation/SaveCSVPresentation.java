package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;
import java.io.FileWriter;

import com.opencsv.CSVWriter;

import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ISaveAcessor;

public class SaveCSVPresentation implements ISaveAcessor {
    @Override
    public void execute(Presentation presentation, String fileName) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName))) {

            String[] title = {presentation.getTitle()};

            csvWriter.writeNext(title);

            for (int i = 0; i < presentation.getSize(); i++) {
                Slide slide = presentation.getSlide(i);
                String[] slideTitle = {slide.getTitleText()};

                csvWriter.writeNext(slideTitle);

                for (int j = 0; j < slide.getSize(); j++) {
                    SlideItem slideItem = slide.getSlideItem(j);
                    String slideItemLevel = String.valueOf(slideItem.getLevel());
                    String[] slideItemLine = { slideItemLevel, "", "" };

                    if (slideItem instanceof TextItem) {
                        slideItemLine[1] = "text";
                        slideItemLine[2] = ((TextItem) slideItem).getText();
                    } else if (slideItem instanceof BitmapItem) {
                        slideItemLine[1] = "bitmap";
                        slideItemLine[2] = ((BitmapItem) slideItem).getName();
                    }

                    csvWriter.writeNext(slideItemLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
