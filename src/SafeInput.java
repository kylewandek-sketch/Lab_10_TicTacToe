import java.util.Scanner;

public class SafeInput {

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid integer value
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid double value
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low    the low value for the input range
     * @param high   the high value for the input range
     * @return a valid integer value within the specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter an integer between " + low + " and " + high);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low    the low value for the input range
     * @param high   the high value for the input range
     * @return a valid double value within the specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        boolean done = false;
        String trash = "";

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a double between " + low + " and " + high);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double not: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return true for yes, false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retVal = false;
        boolean done = false;
        String response = "";

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                retVal = true;
                done = true;
            } else if (response.equalsIgnoreCase("N")) {
                retVal = false;
                done = true;
            } else {
                System.out.println("You must enter [Y/N]: " + response);
            }
        } while (!done);

        return retVal;
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx  the regex pattern to match
     * @return a String that matches the regex pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String retString = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
            if (retString.matches(regEx)) {
                done = true;
            } else {
                System.out.println("You must enter a value that matches the pattern: " + regEx);
            }
        } while (!done);

        return retString;
    }

    /**
     * @param msg the message to display centered in the header
     */
    public static void prettyHeader(String msg) {
        int width = 60;
        int msgLen = msg.length();
        int spaces = width - 6 - msgLen;
        int leftSpaces = spaces / 2;
        int rightSpaces = spaces - leftSpaces;

        // top row of stars
        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();

        // middle row
        System.out.print("***");
        for (int i = 0; i < leftSpaces; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < rightSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        // bottom row of stars
        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
