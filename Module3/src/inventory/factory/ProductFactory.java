package inventory.factory;

import inventory.model.*;
import inventory.util.ValidationUtil;

public class ProductFactory {

    public static Product createPumaProduct(
            String[] data) {

        int id =
                Integer.parseInt(data[0]);

        String brand =
                data[1];

        String color =
                data[2];

        int size =
                Integer.parseInt(data[3]);

        String type =
                data[4];

        ValidationUtil.validateColor(color);
        ValidationUtil.validateSize(size);
        ValidationUtil.validateType(type);

        return new PumaProduct(
                id,
                brand,
                color,
                size,
                ProductType.valueOf(
                        type.toUpperCase())
        );
    }

    public static Product createNikeProduct(
            String[] data) {

        int id =
                Integer.parseInt(data[0]);

        String brand =
                data[1];

        String color =
                data[2];

        int size =
                Integer.parseInt(data[3]);

        String type =
                data[4];

        double price =
                Double.parseDouble(data[5]);

        ValidationUtil.validateColor(color);
        ValidationUtil.validateSize(size);
        ValidationUtil.validateType(type);

        return new NikeProduct(
                id,
                brand,
                color,
                size,
                ProductType.valueOf(
                        type.toUpperCase()),
                price
        );
    }
}