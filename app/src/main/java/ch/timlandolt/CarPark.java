package ch.timlandolt;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CarPark {
    private final TicketMachine ticketMachine;
    private final Barrier entranceBarrier;
    private final ExitBarrier exitBarrier1;
    private final ExitBarrier exitBarrier2;
    private final SpacesDisplay display;
    private final Floor[] floors;
    private final int spacesPerFloor;
    private final float pricePerMinute;

    public CarPark(int floorCount, int spacesPerFloor, float pricePerMinute) {
        this.ticketMachine = new TicketMachine();
        this.entranceBarrier = new Barrier("Entrance Barrier");
        this.exitBarrier1 = new ExitBarrier("Exit Barrier 1", ticketMachine);
        this.exitBarrier2 = new ExitBarrier("Exit Barrier 2", ticketMachine);
        this.display = new SpacesDisplay();
        this.floors = new Floor[floorCount];
        this.spacesPerFloor = spacesPerFloor;
        this.pricePerMinute = pricePerMinute;

        IntStream.range(0, floorCount).forEach(i -> {
            this.floors[i] = new Floor(spacesPerFloor, new TicketMachine(), this);
        });

    }

    public float getPricePerMinute() {
        return pricePerMinute;
    }

    public TicketMachine getTicketMachine() {
        return ticketMachine;
    }

    public Barrier getEntranceBarrier() {
        return entranceBarrier;
    }

    public ExitBarrier getExitBarrier1() {
        return exitBarrier1;
    }

    public ExitBarrier getExitBarrier2() {
        return exitBarrier2;
    }

    public SpacesDisplay getDisplay() {
        return display;
    }

    public int getSpacesPerFloor() {
        return spacesPerFloor;
    }

    public int getFloorCount() {
        return floors.length;
    }

    public int calcTotalSpaces() {
        return floors.length * spacesPerFloor;
    }

    public int calcAvailableSpaces() {
        int availableSpaces = Arrays.stream(floors).mapToInt(Floor::calcFreeSpaces).sum();

        return availableSpaces;
    }

    public Floor getFloor(int id) throws IndexOutOfBoundsException {
        return floors[id - 1];
    }
}
