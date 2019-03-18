package annotation.bean;

public class Blue {

    public Blue(){
        System.out.println("blue...constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("blue...init...");
    }

    public void detory(){
        System.out.println("blue...detory...");
    }

    @Override
    public String toString() {
        return "Blue{" +
                "name='" + name + '\'' +
                '}';
    }
}
