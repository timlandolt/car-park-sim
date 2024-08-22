public class SpacesDisplay {
    private int freeSpaces;
    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
    }

    private void updateDisplay() {
        PrintUtils.println("Display: " + freeSpaces + " spaces left");
    }
}
