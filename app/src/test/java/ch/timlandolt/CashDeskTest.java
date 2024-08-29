package ch.timlandolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashDeskTest {

    private CashDesk cashDesk;
    private ParkingTicket ticket;
    private float parkingPricePerMinute;

    @BeforeEach
    void setUp() {
        parkingPricePerMinute = 0.3f;

        CarPark carPark = new CarPark(1, 10, parkingPricePerMinute);
        TicketMachine ticketMachine = carPark.getTicketMachine();

        cashDesk = carPark.getFloor(1).getCashDesk();

        ticket = ticketMachine.generateAndActivateTicket();
    }

    @Test
    void testCalcParkingPrice_30Minutes() {
        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(30);
        float price = cashDesk.calcParkingPrice(ticket, currentTime);
        assertEquals(parkingPricePerMinute * 30, price, 0.001f);
    }

    @Test
    void testCalcParkingPrice_2Hours() {
        LocalDateTime currentTime = LocalDateTime.now().plusHours(2);
        float price = cashDesk.calcParkingPrice(ticket, currentTime);
        assertEquals(parkingPricePerMinute * 120, price, 0.001f);
    }

}
