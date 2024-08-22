import java.util.Scanner;

public class InputUtils {
    public static int selectInt(String question, String[] options, Scanner scanner) {
        boolean legalInput = false;
        int parsedResponse;

        do {

            for (int i = 0; i < options.length; i++) {
                PrintUtils.println(i + 1 + ": " + options[i]);
            }

            PrintUtils.print(question + " ");
            String response = scanner.nextLine();

            try {
                parsedResponse = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                PrintUtils.println("'" + response + "' is not a valid input.");
                continue;
            }

            if (parsedResponse <= 0 || parsedResponse > options.length) {
                PrintUtils.println("'" + response + "' is not a valid input.");
                continue;
            }

            legalInput = true;
            return parsedResponse;

        } while (!legalInput);

        return 0;
    }

    public static int[] getInt(String question, int min, int max, Scanner scanner, boolean cancellable) {
        boolean legalInput = false;
        int parsedResponse;

        int[] responseArray = new int[2];

        do {

            PrintUtils.print(question + (cancellable ? " ('x' to cancel): " : " "));
            String response = scanner.nextLine();

            if (response.strip().equalsIgnoreCase("x") && cancellable) {
                responseArray[1] = 1;

                return responseArray;
            }

            try {
                parsedResponse = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                PrintUtils.println("'" + response + "' is not a valid input.");
                continue;
            }

            if (parsedResponse < min || parsedResponse > max) {
                PrintUtils.println("'" + response + "' is not a valid input.");
                continue;
            }

            legalInput = true;
            responseArray[0] = parsedResponse;
            responseArray[1] = 0;
            return responseArray;

        } while (!legalInput);
        responseArray[1] = 1;
        return responseArray;
    }

    public static String[] getTicketId(String question, Scanner scanner, boolean cancellable) {
        return getString(question, "^[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}$", scanner, cancellable);
    }

    public static String[] getString(String question, String pattern, Scanner scanner, boolean cancellable) {
        boolean legalInput = false;
        String[] response = new String[2];
        response[1] = "0";

        do {

            PrintUtils.print(question + (cancellable ? " ('x' to cancel): " : " "));
            response[0] = scanner.nextLine();

            if (response[0].strip().matches(pattern)) {
                legalInput = true;
            } else if (response[0].equalsIgnoreCase("x") && cancellable) {
                response[1] = "1";
                return response;
            } else {
                PrintUtils.println("'" + response[0] + "' is not a valid input.");
                continue;
            }

            legalInput = true;
            return response;

        } while (!legalInput);

        return response;
    }
}