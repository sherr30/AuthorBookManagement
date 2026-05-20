package inventory.util;

import inventory.exception.InvalidDataException;
import inventory.model.ProductType;

public class ValidationUtil {

    public static void validateColor(String color) {

        if (!color.matches("[a-zA-Z]+")) {

            throw new InvalidDataException(
                    "Invalid Color");
        }
    }

    public static void validateSize(int size) {

        if (size <= 0) {

            throw new InvalidDataException(
                    "Invalid Size");
        }
    }

    public static void validateType(String type) {

        try {

            ProductType.valueOf(type.toUpperCase());

        } catch (Exception e) {

            throw new InvalidDataException(
                    "Type should be SHIRT or SHOE");
        }
    }
}