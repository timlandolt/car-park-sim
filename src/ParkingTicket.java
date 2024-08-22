import java.time.LocalDateTime;

public class ParkingTicket {
    private final String id;
    private final LocalDateTime purchaseTime;
    private boolean payed;

    public ParkingTicket() {
        this.id = TicketMachine.generateFormattedTicketId();
        this.purchaseTime = LocalDateTime.now();
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public String getId() {
        return id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
