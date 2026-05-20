package inventory.model;

public class NikeProduct extends Product {

    private double price;

    public NikeProduct(int id,
                       String brand,
                       String color,
                       int size,
                       ProductType type,
                       double price) {

        super(id, brand, color, size, type);

        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return super.toString() +
                ", price=" + price;
    }
}