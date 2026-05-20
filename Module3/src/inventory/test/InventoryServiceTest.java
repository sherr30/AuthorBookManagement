package inventory.test;

import inventory.model.Product;
import inventory.model.ProductType;
import inventory.model.PumaProduct;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private InventoryService service;

    @BeforeEach
    public void setup() {

        service =
                new InventoryService();

        Product product =
                new PumaProduct(
                        1,
                        "PUMA",
                        "RED",
                        10,
                        ProductType.SHOE);

        InventoryRepository
                .getInstance()
                .addProduct(product);
    }

    @Test
    public void testFilterByColor() {

        List<Product> result =
                service.filterByColor("RED");

        assertEquals(1,
                result.size());
    }

    @Test
    public void testFilterBySize() {

        List<Product> result =
                service.filterBySize(10);

        assertEquals(1,
                result.size());
    }

    @Test
    public void testFilterByColorAndSize() {

        List<Product> result =
                service.filterByColorAndSize(
                        "RED",
                        10);

        assertEquals(1,
                result.size());
    }

    @Test
    public void testNoResult() {

        List<Product> result =
                service.filterByColor("BLUE");

        assertTrue(result.isEmpty());
    }
}