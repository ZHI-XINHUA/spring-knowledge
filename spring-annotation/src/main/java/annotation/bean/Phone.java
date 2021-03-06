package annotation.bean;

public class Phone {

    private String name;

    private float price;

    private String size;

    public Phone() {
        System.out.println("phone none param constructor");
    }

    public Phone(String name, float price, String size) {
        System.out.println("phone none param constructor");
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
