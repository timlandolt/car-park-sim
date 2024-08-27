package ch.timlandolt;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String ticketId) {
        super("Couldn't find parking ticket with id: " + ticketId);
    }
}

