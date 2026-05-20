package inventory;

import inventory.model.Product;
import inventory.service.InventoryService;
import inventory.service.SchedulerService;
import inventory.util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {

        SchedulerService schedulerService =
                new SchedulerService();

        schedulerService.startThreads();

        InventoryService service =
                new InventoryService();

        Scanner scanner =
                new Scanner(System.in);

        while (true) {

            try {

                System.out.println("""

                        ===== INVENTORY MENU =====

                        1. Filter By Color
                        2. Filter By Size
                        3. Filter By Color And Size
                        4. Filter By Brand And Color
                        5. Filter By Brand And Size

                        Type exit to quit
                        """);

                System.out.print(
                        "Enter Choice: ");

                String choice =
                        scanner.nextLine();

                if (choice.equalsIgnoreCase(
                        "exit")) {

                    System.out.println(
                            "Application Closed");

                    break;
                }

                List<Product> result = null;

                switch (choice) {

                    case "1":

                        String color =
                                InputUtil.getColor();

                        result =
                                service.filterByColor(
                                        color);

                        break;

                    case "2":

                        int size =
                                InputUtil.getSize();

                        result =
                                service.filterBySize(
                                        size);

                        break;

                    case "3":

                        String c =
                                InputUtil.getColor();

                        int s =
                                InputUtil.getSize();

                        result =
                                service.filterByColorAndSize(
                                        c,
                                        s);

                        break;

                    case "4":

                        System.out.print(
                                "Enter Brand: ");

                        String brand =
                                scanner.nextLine();

                        String colorInput =
                                InputUtil.getColor();

                        result =
                                service.filterByBrandAndColor(
                                        brand,
                                        colorInput);

                        break;

                    case "5":

                        System.out.print(
                                "Enter Brand: ");

                        String brandInput =
                                scanner.nextLine();

                        int sizeInput =
                                InputUtil.getSize();

                        result =
                                service.filterByBrandAndSize(
                                        brandInput,
                                        sizeInput);

                        break;

                    default:

                        System.out.println(
                                "Invalid Choice");

                        continue;
                }

                if (result.isEmpty()) {

                    System.out.println(
                            "No Records Found");

                } else {

                    System.out.println(
                            "\n===== RESULTS =====");

                    result.forEach(
                            System.out::println);
                }

            } catch (Exception e) {

                System.out.println(
                        "Error : "
                                + e.getMessage());
            }
        }
    }
}