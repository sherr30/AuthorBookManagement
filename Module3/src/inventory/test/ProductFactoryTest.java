package inventory.test;

import inventory.factory.ProductFactory;
import inventory.model.Product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

    @Test
    public void testCreatePumaProduct() {

        String[] data = {
                "1",
                "PUMA",
                "RED",
                "10",
                "SHOE"
        };

        Product product =
                ProductFactory
                        .createPumaProduct(data);

        assertNotNull(product);
    }

    @Test
    public void testCreateNikeProduct() {

        String[] data = {
                "1",
                "NIKE",
                "BLACK",
                "9",
                "SHIRT",
                "1000"
        };

        Product product =
                ProductFactory
                        .createNikeProduct(data);

        assertNotNull(product);
    }
}