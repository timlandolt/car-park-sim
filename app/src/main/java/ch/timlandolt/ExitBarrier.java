package ch.timlandolt;

public class ExitBarrier extends Barrier{

    private TicketMachine ticketMachine;
    ExitBarrier(String name, TicketMachine ticketMachine) {
        super(name);
        this.ticketMachine = ticketMachine;
    }

    boolean checkTicket(String ticketId) {
        return ticketMachine.getTicketFromId(ticketId).isPayed();
    }
}
