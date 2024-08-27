package ch.timlandolt;

import java.time.format.DateTimeFormatter;

public class PrintUtils {

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void printBorderedString(String string) {

        int length = string.length();
        println("");
        PrintUtils.print("####");
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
        println("# " + string + " #");

        PrintUtils.print("####");
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
        println("");
    }

    public static void printTicket(ParkingTicket ticket) {
        println("");
        println("##############");
        println("# " + ticket.getId() + "  #");
        println("#            #");
        println("# " + ticket.getPurchaseTime().format(timeFormatter) + "   #");
        println("# " + ticket.getPurchaseTime().format(dateFormatter) + " #");
        println("##############");
        println("");

    }

    public static void printFloor(Floor floor) {
        ParkingSpace[] spaces = floor.getSpaces();

        System.out.print("[CPS] ");
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i].isOccupied()) {
                System.out.print("---  ");
            } else {
                System.out.printf("%03d  ", i + 1);
            }

            if ((i + 1) % 10 == 0 && i != spaces.length - 1) {
                System.out.println();
                System.out.print("[CPS] ");
            }
        }

        System.out.println();
        println("");
    }

    public static void println(String message) {
        System.out.println("[CPS] " + message);
    }

    public static void print(String message) {
        System.out.print("[CPS] " + message);
    }
}
