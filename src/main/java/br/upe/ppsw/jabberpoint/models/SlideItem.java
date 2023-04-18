package br.upe.ppsw.jabberpoint.models;

public class SlideItem {

  private Long level = (long) 0;

  public SlideItem(long lev) {
    level = lev;
  }

  public SlideItem() {
    this(0);
  }

  public long getLevel() {
    return level;
  }

}
