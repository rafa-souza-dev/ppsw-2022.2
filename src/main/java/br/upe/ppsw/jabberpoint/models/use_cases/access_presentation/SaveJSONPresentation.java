package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.models.Presentation;

import java.util.Vector;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;
import br.upe.ppsw.jabberpoint.models.use_cases.access_presentation.interfaces.ISaveAcessor;

public class SaveJSONPresentation implements ISaveAcessor {
    @Override
    public void execute(Presentation presentation, String fileName) throws IOException {
        JSONObject json = new JSONObject();
        JSONArray slideJsonArray = new JSONArray();

        json.put("title", presentation.getTitle());

        for (int i = 0; i < presentation.getSize(); i++) {
            Slide slide = presentation.getSlide(i);
            Vector<SlideItem> items = slide.getSlideItems();

            JSONObject jsonSlide = new JSONObject();
            JSONArray jsonSlideItemArray = new JSONArray();
            JSONObject jsonSlideItemObject = null;

            for (int j = 0; j < items.size(); j++) {
                SlideItem slideItem = items.get(j);
                jsonSlideItemObject = new JSONObject();

                jsonSlideItemObject.put("level", slideItem.getLevel());

                if (slideItem instanceof TextItem) {
                    jsonSlideItemObject.put("text", ((TextItem) slideItem).getText());
                } else if (slideItem instanceof BitmapItem) {
                    jsonSlideItemObject.put("imageName", ((BitmapItem) slideItem).getName());
                }

                jsonSlideItemArray.add(jsonSlideItemObject);
            }

            jsonSlide.put("title", slide.getTitleText());
            jsonSlide.put("items", jsonSlideItemArray);

            slideJsonArray.add(jsonSlide);
        }

        json.put("slides", slideJsonArray);

        FileWriter writer = new FileWriter(fileName);

        writer.write(json.toJSONString());

        writer.close();
    }
}
