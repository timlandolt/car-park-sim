public class CarPark {
    private final TicketMachine ticketMachine;
    private final Barrier entranceBarrier;
    private final ExitBarrier exitBarrier1;
    private final ExitBarrier exitBarrier2;
    private final SpacesDisplay display;
    private final Floor[] floors;
    private final int spacesPerFloor;
    private final float pricePerMinute;

    public CarPark(int floors, int spacesPerFloor, float pricePerMinute) {
        this.ticketMachine = new TicketMachine();
        this.entranceBarrier = new Barrier("Entrance Barrier");
        this.exitBarrier1 = new ExitBarrier("Exit Barrier 1", ticketMachine);
        this.exitBarrier2 = new ExitBarrier("Exit Barrier 2", ticketMachine);
        this.display = new SpacesDisplay();
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

    public Floor getFloor(int id) throws IndexOutOfBoundsException {
        return floors[id - 1];
    }
}
