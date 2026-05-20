package inventory.repository;

import inventory.model.Product;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryRepository {

    private static final InventoryRepository
            instance =
            new InventoryRepository();

    private final Map<String, Product>
            inventoryMap =
            new ConcurrentHashMap<>();

    private InventoryRepository() {
    }

    public static InventoryRepository
    getInstance() {

        return instance;
    }

    public void addProduct(Product product) {

        String key =
                product.getId()
                        + "-"
                        + product.getBrand();

        inventoryMap.putIfAbsent(
                key,
                product);
    }

    public Collection<Product>
    getAllProducts() {

        return inventoryMap.values();
    }
}