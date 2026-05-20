package inventory.service;

import inventory.model.Product;
import inventory.repository.InventoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {

    public List<Product> filterByColor(
            String color) {

        return InventoryRepository
                .getInstance()
                .getAllProducts()
                .stream()
                .filter(product ->
                        product.getColor()
                                .equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Product> filterBySize(
            int size) {

        return InventoryRepository
                .getInstance()
                .getAllProducts()
                .stream()
                .filter(product ->
                        product.getSize() == size)
                .collect(Collectors.toList());
    }

    public List<Product> filterByColorAndSize(
            String color,
            int size) {

        return InventoryRepository
                .getInstance()
                .getAllProducts()
                .stream()
                .filter(product ->
                        product.getColor()
                                .equalsIgnoreCase(color)
                                &&
                                product.getSize() == size)
                .collect(Collectors.toList());
    }

    public List<Product> filterByBrandAndColor(
            String brand,
            String color) {

        return InventoryRepository
                .getInstance()
                .getAllProducts()
                .stream()
                .filter(product ->
                        product.getBrand()
                                .equalsIgnoreCase(brand)
                                &&
                                product.getColor()
                                        .equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Product> filterByBrandAndSize(
            String brand,
            int size) {

        return InventoryRepository
                .getInstance()
                .getAllProducts()
                .stream()
                .filter(product ->
                        product.getBrand()
                                .equalsIgnoreCase(brand)
                                &&
                                product.getSize() == size)
                .collect(Collectors.toList());
    }
}