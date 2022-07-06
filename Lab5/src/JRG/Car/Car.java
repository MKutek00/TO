package JRG.Car;

import State.CarState;

public class Car{

    private CarState state;
    private final int id;
    private final String name;

    public Car(CarState state, int id, String name) {
        this.state = state;
        this.id = id;
        this.name = name;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void write(){
        System.out.print(state.name() + " ");
    }
}
