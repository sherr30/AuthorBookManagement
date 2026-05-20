package inventory.model;

public abstract class Product {

    protected int id;
    protected String brand;
    protected String color;
    protected int size;
    protected ProductType type;

    public Product(int id,
                   String brand,
                   String color,
                   int size,
                   ProductType type) {

        this.id = id;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {

        return "id=" + id +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", type=" + type;
    }
}