package br.upe.ppsw.jabberpoint.controllers;

import java.util.ArrayList;

import br.upe.ppsw.jabberpoint.models.Presentation;
import br.upe.ppsw.jabberpoint.models.Slide;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

public class PresentationController {
    private Presentation presentation;
    private SlideViewerComponent slideViewerComponent;
    private int currentSlideNumber = 0;
    private static PresentationController instance;

    private PresentationController() {}

    public static PresentationController getInstance() {
        if (instance == null) {
            instance = new PresentationController();
        }

        return instance;
    }

    public int getSlideNumber() {
        return currentSlideNumber;
    }
    
    public void setSlideNumber(int number) {
        currentSlideNumber = number;
        if (slideViewerComponent != null) {
            slideViewerComponent.update(presentation, getCurrentSlide());
        }
    }

    public Slide getCurrentSlide() {
        return this.presentation.getSlide(currentSlideNumber);
    }

    public void prevSlide() {
        if (currentSlideNumber > 0) {
          setSlideNumber(currentSlideNumber - 1);
        }
      }
    
    public void nextSlide() {
        if (currentSlideNumber < (this.presentation.getShowList().size() - 1)) {
            setSlideNumber(currentSlideNumber + 1);
        }
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
        clear();
    }

    public void setSlideViewerComponent(
        SlideViewerComponent slideViewerComponent
    ) {
        this.slideViewerComponent = slideViewerComponent;
        clear();
    }

    public void clear() {
        this.presentation.setShowList(new ArrayList<Slide>());
        setSlideNumber(-1);
    }

}
