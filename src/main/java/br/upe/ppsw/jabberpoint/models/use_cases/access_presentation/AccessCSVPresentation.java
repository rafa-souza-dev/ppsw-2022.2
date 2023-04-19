package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

public class AccessCSVPresentation implements IBasicAcessor, ISaveAcessor {
    @Override
    public void loadFile(Presentation presentation, String fileName) throws IOException {
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            List<String[]> content = reader.readAll();

            String title = content.get(0)[0];

            presentation.setTitle(title);
            presentation.setShowList(new ArrayList<Slide>());

            content.remove(0);

            Slide slide = null;

            for (String[] line : content) {
                if (line.length <= 1) {
                    if (slide != null && slide.getSize() > 0) {
                        presentation.append(slide);
                    }

                    slide = new Slide();
                    slide.setTitle(line[0]);

                    continue;
                }

                if (line[1].equals("bitmap")) {
                    BitmapItem bitmapItem = new BitmapItem(Long.parseLong(line[0]), line[2]);
                    slide.append(bitmapItem);
                } else if (line[1].equals("text")) {
                    TextItem textItem = new TextItem(Long.parseLong(line[0]), line[2]);
                    slide.append(textItem);
                }
            }

            if (slide != null) {
                presentation.append(slide);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + fileName);
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFile(Presentation presentation, String fileName) throws IOException {
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
