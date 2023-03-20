package br.upe.ppsw.jabberpoint.models;

import java.util.Vector;

public class Slide {

  protected TextItem title;
  protected Vector<SlideItem> items;

  public Slide() {
    items = new Vector<SlideItem>();
  }

  public void append(SlideItem anItem) {
    items.addElement(anItem);
  }

  public TextItem getTitle() {
    return title;
  }

  public String getTitleText() {
    return title.getText();
  }

  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

  public SlideItem getSlideItem(int number) {
    return (SlideItem) items.elementAt(number);
  }

  public Vector<SlideItem> getSlideItems() {
    return items;
  }

  public int getSize() {
    return items.size();
  }

}
