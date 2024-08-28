package ch.timlandolt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarParkTest {

    private CarPark carPark;
    private int floors;
    private int spacesPerFloor;

    @BeforeEach
    public void setUp() {
        floors = 10;
        spacesPerFloor = 19;
        carPark = new CarPark(10, 19, 0.1f);
    }

    @Test
    public void testCalcTotalSpaces() {
        assertEquals(new CarPark(3, 58, 0.1f).calcTotalSpaces(), 3 * 58);
        assertEquals(new CarPark(7, 83, 0.1f).calcTotalSpaces(), 7 * 83);
        assertEquals(new CarPark(10, 10, 0.1f).calcTotalSpaces(), 10 * 10);
        assertEquals(new CarPark(2, 500, 0.1f).calcTotalSpaces(), 2 * 500);
    }

    @Test
    public void testCalcAvailableSpaces() {
        assertEquals(carPark.calcAvailableSpaces(), 210);
        carPark.getFloor(1).occupySpace(1);
        assertEquals(carPark.calcAvailableSpaces(), 209);
        carPark.getFloor(1).occupySpace(1);
        assertEquals(carPark.calcAvailableSpaces(), 208);
    }

    @Test
    public void testGetFloor() {
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