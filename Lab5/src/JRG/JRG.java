package JRG;

import Action.Action;
import JRG.Car.Car;
import Observer.IObserver;
import State.CarState;

import java.util.*;

public class JRG implements IObserver {

    private List<Car> carList = new ArrayList<>();

    private int numberOfCars;
    private final double x;
    private final double y;
    private final String name;
    private double distance;
    private final java.util.Timer timer = new Timer();
    private final Random random = new Random();
    private int strategyNumber;
    private boolean falseAlarm;
    private int time;


    public JRG(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;

        for (int i = 0; i < 5; i++){
            carList.add(new Car(CarState.FREE, i+1, this.name));
        }

        this.numberOfCars = carList.size();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isFalseAlarm() {
        return falseAlarm;
    }

    public void setFalseAlarm(boolean falseAlarm) {
        this.falseAlarm = falseAlarm;
    }

    public int getNumberOfCars() {
        return this.numberOfCars;
    }

    public String getName() {
        return this.name;
    }

    public double[] getXY(){
        return new double[]{this.x, this.y};
    }

    public List<Car> getCarList() {
        return this.carList;
    }

    public double getDistance(){
        return this.distance;
    }

    public int getStrategyNumber() {
        return strategyNumber;
    }

    public void setStrategy(int count){
        this.strategyNumber = count;

        for (Car car : carList) {
            if (car.getState().equals(CarState.FREE) && strategyNumber > 0) {
                car.setState(CarState.BUSY);
                numberOfCars--;
                strategyNumber--;
                System.out.println("Wyjechała " + car.getId() + " z " + this.name);
                if (isFalseAlarm()) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            numberOfCars++;
                            System.out.println("FA - Wróciła " + car.getId() + " do " + car.getName());
                            car.setState(CarState.FREE);
                        }
                    }, getTime());
                } else {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            numberOfCars++;
                            System.out.println("Wróciła " + car.getId() + " do " + car.getName());
                            car.setState(CarState.FREE);
                        }
                    }, getTime());
                }
            }
        }
    }


    @Override
    public void update(Action action) {
        this.distance = Math.sqrt(Math.pow((action.getX() - this.x), 2) + Math.pow((action.getY() - this.y), 2));
    }

}
