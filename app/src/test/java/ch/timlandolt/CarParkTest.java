package ch.timlandolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarParkTest {

    private CarPark carPark;
    private int floors;
    private int spacesPerFloor;

    @BeforeEach
    void setUp() {
        floors = 10;
        spacesPerFloor = 19;
        carPark = new CarPark(10, 19, 0.1f);
    }

    @Test
    void testCalcTotalSpaces() {
        assertEquals(3 * 58, new CarPark(3, 58, 0.1f).calcTotalSpaces());
        assertEquals(7 * 83, new CarPark(7, 83, 0.1f).calcTotalSpaces());
        assertEquals(10 * 10, new CarPark(10, 10, 0.1f).calcTotalSpaces());
        assertEquals(2 * 500, new CarPark(2, 500, 0.1f).calcTotalSpaces());
    }

    @Test
    void testCalcAvailableSpaces() {
        assertEquals(190, carPark.calcAvailableSpaces());
        carPark.getFloor(1).occupySpace(1);
        assertEquals(189, carPark.calcAvailableSpaces());
        carPark.getFloor(1).occupySpace(2);
        assertEquals(188, carPark.calcAvailableSpaces());
    }

    @Test
    void testGetFloor() {
        assertNotNull(carPark.getFloor(1));
    }

    @Test
    void testGetFloor_InvalidId_TooLow() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            carPark.getFloor(0);
        });
    }

    @Test
    void testGetFloor_InvalidId_TooHigh() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            carPark.getFloor(floors + 1);
        });
    }
}