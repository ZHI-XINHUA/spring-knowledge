package annotation.bean;

public class Circular {
    private int radius;

    private Circular(){
        super();
    }

    public Circular(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circular{" +
                "radius=" + radius +
                '}';
    }
}
