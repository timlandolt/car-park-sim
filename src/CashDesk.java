import java.time.Duration;
import java.time.LocalDateTime;

public class CashDesk {
    private TicketMachine ticketMachine;
    private CarPark carPark;

    public CashDesk(TicketMachine ticketMachine, CarPark carPark) {
        this.ticketMachine = ticketMachine;
        this.carPark = carPark;
    }

    public float getParkingPrice(ParkingTicket ticket) {
        LocalDateTime currentTime = LocalDateTime.now();
        long minutes = Duration.between(ticket.getPurchaseTime(), currentTime).toMinutes();

        return minutes * carPark.getPricePerMinute();
    }

    public void payTicket(String ticketId) {
        ticketMachine.getTicketFromId(ticketId).setPayed(true);
    }
}
