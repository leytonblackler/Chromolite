interface Button {
  void drawButton();
  boolean containsCursor();
  void setPressedState(boolean pressed);
  void setSelectedState(boolean selected);
  public boolean isSelected();
  public boolean isSelectable();
  public boolean isPressed();
  public String getAction();
  void setNumberOfColoursUsed(int coloursUsed);
}