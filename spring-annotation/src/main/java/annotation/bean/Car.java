package annotation.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String carNum;

    private float price;

    public Car() {
        super();
    }

    public Car(String carNum, float price) {
        this.carNum = carNum;
        this.price = price;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNum='" + carNum + '\'' +
                ", price=" + price +
                '}';
    }
}
