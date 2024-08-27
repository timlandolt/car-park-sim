package ch.timlandolt;

public class Floor {
    private ParkingSpace[] spaces;
    private CashDesk cashDesk;

    public Floor(int spaces, TicketMachine ticketMachine, CarPark carPark) {
        this.spaces = new ParkingSpace[spaces];
        for (int i = 0; i < spaces; i++) {
            this.spaces[i] = new ParkingSpace();
        }

        this.cashDesk = new CashDesk(ticketMachine, carPark);
    }

    public CashDesk getCashDesk() {
        return cashDesk;
    }


    public ParkingSpace[] getSpaces() {
        return spaces;
    }

    public int calcFreeSpaces() {
        int freeSpaces = 0;
        for (ParkingSpace space : spaces) {
            if (!space.isOccupied()) {
                freeSpaces++;
            }
        }

        return freeSpaces;
    }
    public void occupySpace(int id) {
        spaces[id - 1].setOccupied(true);
    }

    public void freeSpace(int id) {
        spaces[id - 1].setOccupied(false);
    }

}
