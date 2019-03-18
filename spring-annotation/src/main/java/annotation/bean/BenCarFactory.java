package annotation.bean;

public class BenCarFactory {

    /**
     * 非静态方法创建
     * @return
     */
    public BenCar createCar(){
        return  new BenCar();
    }

    /**
     * 静态方法创建
     * @return
     */
    public static BenCar staticCreateCar(){
        return  new BenCar();
    }
}
