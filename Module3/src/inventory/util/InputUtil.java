package inventory.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner =
            new Scanner(System.in);

    public static String getColor() {

        while (true) {

            System.out.print("Enter Color: ");

            String color = scanner.nextLine();

            if (color.matches("[a-zA-Z]+")) {
                return color;
            }

            System.out.println(
                    "Invalid Color. Try Again.");
        }
    }

    public static int getSize() {

        while (true) {

            try {

                System.out.print("Enter Size: ");

                int size =
                        Integer.parseInt(
                                scanner.nextLine());

                if (size > 0) {
                    return size;
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid Size. Numbers only.");
            }
        }
    }
}