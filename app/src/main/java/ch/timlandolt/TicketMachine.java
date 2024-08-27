package ch.timlandolt;

import java.util.ArrayList;

public class TicketMachine {
    private final ArrayList<ParkingTicket> activeTickets = new ArrayList<>();

    public static String generateFormattedTicketId() {
        int ticketId = generateTicketIdFromUnixTime();
        return formatTicketId(ticketId);
    }

    private static int generateTicketIdFromUnixTime() {
        return (int) (System.currentTimeMillis() & 0xFFFFFFFFL); // last 32 bits of the current unix time
    }

    public static String formatTicketId(int ticketId) {
        String hexString = Integer.toHexString(ticketId);
        hexString = hexString.toUpperCase();
        String padded = String.format("%08d%s", 0, hexString);
        // format ticketId as following: 14F8BA12 -> 14F8-BA12
        return padded.substring(padded.length() - 8, padded.length() - 4) + // first 4 digits
                "-" + padded.substring(padded.length() - 4); // last 4 digits
    }

    public ParkingTicket getTicketFromId(String ticketId) throws TicketNotFoundException {
        for (ParkingTicket ticket : activeTickets) {
            if (ticket.getId().equals(ticketId.toUpperCase())) {
                return ticket;
            }
        }
        throw new TicketNotFoundException(ticketId);
    }

    public void deactivateTicket(String ticketId) throws TicketNotFoundException {
        activeTickets.remove(getTicketFromId(ticketId));
    }

    public ParkingTicket generateAndActivateTicket() {
        String ticketId = formatTicketId(generateTicketIdFromUnixTime());
        ParkingTicket ticket = new ParkingTicket();
        activeTickets.add(ticket);
        return ticket;
    }

}
