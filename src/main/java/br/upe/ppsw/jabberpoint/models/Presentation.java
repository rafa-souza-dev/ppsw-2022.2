package br.upe.ppsw.jabberpoint.models;

import java.util.ArrayList;

public class Presentation {

  private String title;
  private ArrayList<Slide> showList = null;

  public Presentation() {}

  public int getSize() {
    return showList.size();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String nt) {
    title = nt;
  }

  public void append(Slide slide) {
    showList.add(slide);
  }

  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return (Slide) showList.get(number);
  }

  public ArrayList<Slide> getShowList() {
    return this.showList;
  }

  public void setShowList(ArrayList<Slide> showList) {
    this.showList = showList;
  }

  public void exit(int n) {
    System.exit(n);
  }
}
