package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ILoadAcessor;

public class LoadCSVPresentation implements ILoadAcessor {
    @Override
    public void execute(Presentation presentation, String fileName) throws IOException {
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
}
