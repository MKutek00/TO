package JRG;

import Actionn.Action;
import Frame.DrawBoard;

import javax.swing.*;
import java.util.ArrayList;

public class CarRunnable implements Runnable{

    private Car car;
    private JPanel panel;
    private ArrayList<Action> actions;
    private ArrayList<JRG> jrgList;

    public CarRunnable(Car car, JPanel panel, ArrayList<Action> actions, ArrayList<JRG> jrgList){
        this.car = car;
        this.panel = panel;
        this.actions = actions;
        this.jrgList = jrgList;
    }

    @Override
    public void run() {
        synchronized (actions){
            while(true){
                while (actions.size() > DrawBoard.NUMBEROFACTIONS/2){
                    try{
                        actions.wait();
                    }catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }

                int wayX = Math.abs(actions.get(0).getX() - jrgList.get(0).getPixelX());
                int wayY = Math.abs(actions.get(0).getY() - jrgList.get(0).getPixelY());


                for (Action action : actions) {
                    for (JRG jrg : jrgList) {
                        if (Math.abs(action.getX() - jrg.getPixelX()) < wayX) {
                            wayX = Math.abs(action.getX() - jrg.getPixelX());
                        }
                        if (Math.abs(action.getY() - jrg.getPixelY()) < wayY) {
                            wayY = Math.abs(action.getY() - jrg.getPixelY());
                        }
                    }
                }

                System.out.println(wayX + ", " + wayY + " - ");
                this.panel.repaint();

                actions.notifyAll();
            }
        }
    }

}
