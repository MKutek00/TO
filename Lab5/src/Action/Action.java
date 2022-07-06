package Action;

import Observer.IObserver;
import Observer.ISubject;
import State.ActionState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Action implements ISubject {

    private List<IObserver> observers;

    private final double Y1 = 50.154564013341734;
    private final double Y2 = 49.95855025648944;
    private final double X1 = 19.688292482742394;
    private final double X2 = 20.02470275868903;

    private double x;
    private double y;
    private ActionState state;
    private boolean falseAlarm;
    private int time;
    private final Random random = new Random();

    public Action() {
        this.x = (Math.random() * (X2 - X1) + X1);
        this.y = (Math.random() * (Y1 - Y2) + Y2);

        observers = new ArrayList<>();

        int randomNumber =(int) (Math.random() * 100);
        if (randomNumber < 30){
            this.state = ActionState.STATEPZ;
        }else{
            this.state = ActionState.STATEMZ;
        }

        int randomNumber2 = (int) (Math.random() * 100);
        if (randomNumber2 <= 5){
            this.falseAlarm = true;
        }else{
            this.falseAlarm = false;
        }

        if (isFalseAlarm()){
            this.time = random.nextInt(6000);
        }else{
            this.time = random.nextInt(26000)+5000;
        }
    }

    public int getTime() {
        return time;
    }

    public boolean isFalseAlarm() {
        return falseAlarm;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getXY(){
        return new double[]{this.x, this.y};
    }

    public ActionState getState() {
        return state;
    }

    public void setState(ActionState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Akcja na współrzędnych x: " + x
                + ", y: " + y
                + " status akcji: " +  state.name();

    }

    @Override
    public void registerObserver(IObserver observer) {
        if(observer != null){
            this.observers.add(observer);
        }
    }

    @Override
    public void notifyObservers() {
        Iterator<IObserver> it = observers.iterator();
        while(it.hasNext()){
            IObserver observer = it.next();
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        if(observer != null){
            this.observers.remove(observer);
        }
    }
}
