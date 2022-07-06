package Actionn;

import JRG.JRG;
import Frame.DrawBoard;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class ActionRunnable implements Runnable{

    private final JPanel panel;
    private ArrayList<Action> actions;
    private ArrayList<JRG> jrgArrayList;

    private java.util.Timer timer = new Timer();
    private Random random = new Random();
    private boolean var = true;


    public ActionRunnable(ArrayList<Action> actions, JPanel panel, ArrayList<JRG> jrgArrayList){
        this.actions = actions;
        this.panel = panel;
        this.jrgArrayList = jrgArrayList;
    }

    @Override
    public void run() {
        synchronized (actions){
            while(true){
                if (var){
                    add();
                    var = false;
                }
                while (actions.size() == DrawBoard.NUMBEROFACTIONS){
                    try{
                        actions.wait();
                    }catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }

                actions.removeIf(Action::check);
                checkNumberOfAction();
                actions.notifyAll();
            }
        }
    }


    public void checkNumberOfAction(){
        Rectangle rectangle = panel.getBounds();
        int sum = DrawBoard.NUMBEROFACTIONS - actions.size();

        for (int i = 0; i < sum; i++){
            int randomNumber1 = (int) (Math.random()*(rectangle.getMaxX() - 50)+10);
            int randomNumber2 = (int) (Math.random()*(rectangle.getMaxY() - 50)+10);

            int count = 0;
            for (int j = 0; j < jrgArrayList.size(); j++){
                if (jrgArrayList.get(j).checkIfIsClose(randomNumber1, randomNumber2)){
                    count++;
                }
            }

            if (count == jrgArrayList.size()){
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        actions.add(new Action(randomNumber1, randomNumber2, panel));
                    }
                }, random.nextInt(10000));
                sum++;
            }
        }
    }


    public void add(){
        Rectangle rectangle = panel.getBounds();

        int i = 0;
        while (i < DrawBoard.NUMBEROFACTIONS){
            int randomNumber1 = (int) (Math.random()*(rectangle.getMaxX() - 50)+10);
            int randomNumber2 = (int) (Math.random()*(rectangle.getMaxY() - 50)+10);

            int count = 0;
            for (int j = 0; j < jrgArrayList.size(); j++){
                if (jrgArrayList.get(j).checkIfIsClose(randomNumber1, randomNumber2)){
                    count++;
                }
            }

            if (count == jrgArrayList.size()){
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        actions.add(new Action(randomNumber1, randomNumber2, panel));
                    }
                }, random.nextInt(10000));
                i++;
            }
        }
    }
}


