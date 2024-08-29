package ch.timlandolt;

import java.time.Duration;
import java.time.LocalDateTime;

public class CashDesk {
    private final TicketMachine ticketMachine;
    private final CarPark carPark;

    public CashDesk(TicketMachine ticketMachine, CarPark carPark) {
        this.ticketMachine = ticketMachine;
        this.carPark = carPark;
    }

    public float calcParkingPrice(ParkingTicket ticket, LocalDateTime currentTime) {
        long minutes = Duration.between(ticket.getPurchaseTime(), currentTime).toMinutes();
        return minutes * carPark.getPricePerMinute();
    }

    public void payTicket(String ticketId) {
        ticketMachine.getTicketFromId(ticketId).setPayed(true);
    }
}
