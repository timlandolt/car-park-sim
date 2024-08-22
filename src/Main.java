import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        CarPark carPark = new CarPark(5, 60, 1.452f);


        PrintUtils.println("#####################################");
        PrintUtils.println("# Welcome to the Car Park Simulator #");
        PrintUtils.println("#####################################");
        PrintUtils.println("");

        while (!exitProgram) {

            String[] options = {"Get ticket", "Pay parking fee", "Exit car park", "Look at display", "Exit simulation"};
            int response = InputUtils.selectInt("What do you want to do?", options, scanner);

            switch (response) {
                case 1 -> getTicket(scanner, carPark);
                case 2 -> payParkingFee(scanner, carPark);
                case 3 -> exitCarPark(scanner, carPark);
                case 4 -> lookAtDisplay(carPark);
                case 5 -> {
                    exitProgram = true;
                }
            }
        }
        PrintUtils.println("Bye bye!");
    }

    private static void getTicket(Scanner scanner, CarPark carPark) {
        ParkingTicket ticket = carPark.getTicketMachine().generateAndActivateTicket();
        PrintUtils.printTicket(ticket);
        carPark.getEntranceBarrier().letCarPass();


        while (true) {

            int[] floorNumberResult = InputUtils.getInt("On wich floor do you want to park (1-5)?", 1, 5, scanner, false);
            int floorNumber = floorNumberResult[0];
            Floor floor = carPark.getFloor(floorNumber);
            PrintUtils.println("");
            PrintUtils.println("## Floor: " + floorNumber + " ##");
            PrintUtils.printFloor(floor);

            int spaceNumber;
            boolean legalSpaceNumber = false;

            do {
                int[] spaceNumberResult = InputUtils.getInt("On wich space do you want to park?", 1, 60, scanner, true);
                spaceNumber = spaceNumberResult[0];

                if (spaceNumberResult[1] == 1) {
                    break;
                }

                if (!floor.getSpaces()[spaceNumber - 1].isOccupied()) {
                    legalSpaceNumber = true;
                    floor.occupySpace(spaceNumber);
                    PrintUtils.println("You successfully parked into #" + spaceNumber + " on floor " + floorNumber);
                    return;
                } else {
                    PrintUtils.println("You can't park on #" + spaceNumber + "!");
                }
            } while (!legalSpaceNumber);


        }
    }

    private static void payParkingFee(Scanner scanner, CarPark carPark) {
        TicketMachine ticketMachine = carPark.getTicketMachine();

        int[] floorNumberResult = InputUtils.getInt("On wich floor do you want to pay (1-5)?", 1, 5, scanner, true);
        int floorNumber = floorNumberResult[0];

        if (floorNumberResult[1] == 1) return;

        Floor floor = carPark.getFloor(floorNumber);

        String[] ticketIdResponse = InputUtils.getTicketId("Enter your ticket id (e.g. AB10-F901)", scanner, true);
        if (ticketIdResponse[0].equals("1")) return;
        try {
            ParkingTicket ticket = ticketMachine.getTicketFromId(ticketIdResponse[0]);

            if (ticket.isPayed()) {
                PrintUtils.println("Your ticket has already been payed.");
                return;
            }
            float cost = floor.getCashDesk().getParkingPrice(ticket);
            float roundedCost = Math.round(cost * 20) / 20f;
            ticket.setPayed(true);
            PrintUtils.println("You successfully payed CHF " + roundedCost);
        } catch (TicketNotFoundException e) {
            PrintUtils.println(e.getMessage());
        }
    }

    private static void exitCarPark(Scanner scanner, CarPark carPark) {
        TicketMachine ticketMachine = carPark.getTicketMachine();

        int[] floorNumberResponse = InputUtils.getInt("On wich floor is your car parked (1-5)?", 1, 5, scanner, true);
        int floorNumber = floorNumberResponse[0];

        if (floorNumberResponse[1] == 1) return;

        Floor floor = carPark.getFloor(floorNumber);
        PrintUtils.println("");
        PrintUtils.println("## Floor: " + floorNumber + " ##");
        PrintUtils.printFloor(floor);

        int[] spaceNumberResponse;
        int spaceNumber;
        boolean legalSpaceNumber = false;
        boolean canExit = false;

        do {
            spaceNumberResponse = InputUtils.getInt("On wich space is your car parked?", 1, 60, scanner, true);
            spaceNumber = spaceNumberResponse[0];
            if (spaceNumberResponse[1] == 1) return;

            if (floor.getSpaces()[spaceNumber - 1].isOccupied()) {
                legalSpaceNumber = true;
                PrintUtils.println("You successfully parked out of #" + spaceNumber + " on floor " + floorNumber);
                canExit = true;
            } else {
                PrintUtils.println("There is no car parked on #" + spaceNumber + "!");
            }
        } while (!legalSpaceNumber);

        int exitBarrierNr = InputUtils.getInt("Chose an exit barrier (1/2)", 1, 2, scanner, false)[0];
        ExitBarrier exitBarrier = exitBarrierNr == 1 ? carPark.getExitBarrier1() : carPark.getExitBarrier2();
        String ticketId;

        boolean legalId = false;

        while (!legalId) {

            String[] ticketIdResponse = InputUtils.getTicketId("Enter your ticket id (e.g. AB10-F901):", scanner, false);
            ticketId = ticketIdResponse[0];
            if (ticketIdResponse[1].equals("1")) return;
            try {
                if (!ticketMachine.getTicketFromId(ticketId).isPayed()) {
                    PrintUtils.println("Your ticket with id " + ticketId + " was not payed!");
                    return;
                }

                if (canExit) {

                    ticketMachine.deactivateTicket(ticketId);
                    exitBarrier.letCarPass();
                    legalId = true;
                    floor.freeSpace(spaceNumber);
                }
            } catch (TicketNotFoundException e) {
                PrintUtils.println(e.getMessage());
            }
        }

    }

    private static void lookAtDisplay(CarPark carPark) {
        String availableSpaces = Integer.toString(carPark.getAvailableSpaces());
        PrintUtils.printBorderedString("Free spaces: " + availableSpaces);
    }

}