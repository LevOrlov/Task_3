package review.abs_class_interface;

public abstract class Machine {
    private String model;
    private int weight;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public abstract void gas();
    public abstract void brake();

}
