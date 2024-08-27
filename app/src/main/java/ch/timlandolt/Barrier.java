package ch.timlandolt;

public class Barrier {
    private String name;

    public Barrier(String name) {
        this.name = name;
    }

    public void letCarPass() {
        this.open();
        PrintUtils.println("...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.close();
    }

    public void open() {
        PrintUtils.println(this.name + " opened!");
    }

    public void close() {
        PrintUtils.println(this.name + " closed!");
    }
}