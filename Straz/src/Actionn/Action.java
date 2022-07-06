package Actionn;

import JRG.Car;
import State.IState;
import State.StateMZ;
import State.StatePZ;

import javax.swing.*;
import java.util.ArrayList;

public class Action {

    private IState state;
    private JPanel panel;
    private ArrayList<Car> carList;

    private int x;
    private int y;

    public Action(int x, int y, JPanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;

        int randomNumber = (int) (Math.random() * 101);
        if (randomNumber <= 30){
            state = new StatePZ();
        }else{
            state = new StateMZ();
        }
        this.panel.repaint();
    }

    public boolean checkIfIsClose(int number1, int number2){
        if (Math.abs(getX() - number1) >= 10 && Math.abs(getY() - number2) >= 10){
            return true;
        }
        return false;
    }

    public boolean check(){
        if (this.state.handle().equals("DONE")){
            return true;
        }
        return false;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getState(){
        return state.handle();
    }

    public void addCar(Car car){
        if (carList.size() < 5){
            addCar(car);
        }
    }
}
