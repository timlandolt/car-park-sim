public class CarPark {
    private TicketMachine ticketMachine;
    private Barrier entranceBarrier;
    private ExitBarrier exitBarrier1;
    private ExitBarrier exitBarrier2;
    private SpacesDisplay display;
    private Floor[] floors;
    private int spacesPerFloor;
    private float pricePerMinute;

    public CarPark(TicketMachine ticketMachine, Barrier entranceBarrier, ExitBarrier exitBarrier1, ExitBarrier exitBarrier2, SpacesDisplay display, int floors, int spacesPerFloor, float pricePerMinute) {
        this.ticketMachine = ticketMachine;
        this.entranceBarrier = entranceBarrier;
        this.exitBarrier1 = exitBarrier1;
        this.exitBarrier2 = exitBarrier2;
        this.display = display;
        this.floors = new Floor[floors];
        this.spacesPerFloor = spacesPerFloor;
        this.pricePerMinute = pricePerMinute;

        for (int i = 0; i < floors; i++) {
            this.floors[i] = new Floor(spacesPerFloor, new TicketMachine(), this);
        }

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

    public int getTotalSpaces() {
        return floors.length * spacesPerFloor;
    }

    public int getAvailableSpaces() {
        int availableSpaces = 0;

        for (Floor floor : floors) {
            availableSpaces += floor.getFreeSpacesCount();
        }

        return availableSpaces;
    }

//    public void occupySpace() throws NoParkingSpaceException {
//        if (this.availableSpaces > 0) {
//            this.availableSpaces--;
//        } else {
//            throw new NoParkingSpaceException();
//        }
//    }

    public Floor getFloor(int id) throws IndexOutOfBoundsException {
        return floors[id - 1];
    }
}
