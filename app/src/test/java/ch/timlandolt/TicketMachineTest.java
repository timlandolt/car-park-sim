package ch.timlandolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketMachineTest {

    TicketMachine ticketMachine;

    @BeforeEach
    void setUp() {
        ticketMachine = new TicketMachine();
    }

    @Test
    void testGenerateFormattedTicketId() {
        ParkingTicket ticket = ticketMachine.generateAndActivateTicket();
        assertTrue(ticket.getId().matches("[0-9A-F]{4}-[0-9A-F]{4}"));
    }

    @Test
    void testFormatTicketId() {
        assertEquals("1234-ABCD", TicketMachine.formatTicketId(0x1234abcd));
        assertEquals("412A-BF15", TicketMachine.formatTicketId(0x412ABF15));
    }

    @Test
    void testGetTicketFromId() {
        ParkingTicket ticket = ticketMachine.generateAndActivateTicket();
        assertEquals(ticket, ticketMachine.getTicketFromId(ticket.getId()));
    }

    @Test
    void testGetTicketFromId_InvalidId_NoSuchTicket() {
        assertThrows(TicketNotFoundException.class, () -> {
            ticketMachine.getTicketFromId("XXXX-XXXX");
        });
    }


}