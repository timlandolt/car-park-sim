package ch.timlandolt;

public class Main {

    public static void main(String[] args) {
        final int floors = 5;
        final int spacesPerFloor = 60;
        float pricePerMinute = 1.452f;

        CarPark carPark = new CarPark(floors, spacesPerFloor, pricePerMinute);

        IOHandler ioHandler = new ConsoleIOHandler(carPark);

        ioHandler.run();
    }
}