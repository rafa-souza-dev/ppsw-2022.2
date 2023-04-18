package br.upe.ppsw.jabberpoint.models.use_cases.access_presentation;

import java.io.IOException;
import java.util.ArrayList;


import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.upe.ppsw.jabberpoint.models.BitmapItem;
import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.models.SlideItem;
import br.upe.ppsw.jabberpoint.models.TextItem;

public class AccessJSONPresentation implements IBasicAcessor {
    @Override
    public void loadFile(Presentation presentation, String fileName) throws IOException {
        JSONObject jsonObject;
		JSONParser parser = new JSONParser();

		try {
			jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

			String title = (String) jsonObject.get("title");

            presentation.setShowList(new ArrayList<Slide>());
            presentation.setTitle(title);

			JSONArray slides = (JSONArray) jsonObject.get("slides");

            for (Object slideObject : slides) {
                JSONObject jsonSlide = (JSONObject) slideObject;

                Slide slide = new Slide();
                
                String slideTitle = (String) jsonSlide.get("title");

                slide.setTitle(slideTitle);

                JSONArray showListArray = (JSONArray) jsonSlide.get("items");

                for (Object showListObj : showListArray) {
                    JSONObject jsonShowList = (JSONObject) showListObj;

                    SlideItem slideItem = null;

                    Long level = (Long) jsonShowList.get("level");

                    String text = (String) jsonShowList.get("text");

                    if (text != null) {
                        slideItem = new TextItem(level, text);
                    } else {
                        slideItem = new BitmapItem();
                    }

                    slide.append(slideItem);
                }

                presentation.append(slide);
            }
		}
    
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
}
