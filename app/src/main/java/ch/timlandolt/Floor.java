package ch.timlandolt;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Floor {
    private final ParkingSpace[] spaces;
    private final CashDesk cashDesk;

    public Floor(int spacesCount, TicketMachine ticketMachine, CarPark carPark) {
        this.spaces = new ParkingSpace[spacesCount];

        IntStream.range(0, spacesCount).forEach(i -> {
            this.spaces[i] = new ParkingSpace();
        });

        this.cashDesk = new CashDesk(ticketMachine, carPark);
    }

    public CashDesk getCashDesk() {
        return cashDesk;
    }


    public ParkingSpace[] getSpaces() {
        return spaces;
    }

    public int calcFreeSpaces() {
        int freeSpaces = (int) Arrays.stream(spaces).filter(space -> !space.isOccupied()).count();

        return freeSpaces;
    }

    public void occupySpace(int id) {
        spaces[id - 1].setOccupied(true);
    }

    public void freeSpace(int id) {
        spaces[id - 1].setOccupied(false);
    }

}
