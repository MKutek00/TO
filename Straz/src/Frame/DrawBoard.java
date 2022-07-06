package Frame;

import Actionn.Action;
import JRG.*;
import Actionn.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class DrawBoard extends JPanel {

    private ArrayList<JRG> jrgList = new ArrayList<>();
    private ArrayList<Action> actionList = new ArrayList<>();

    private final java.util.Timer timer = new Timer();
    private final Random random = new Random();

    private final JPanel thisPanel = this;

    private Thread thread;
    private ThreadGroup threadGroupAction = new ThreadGroup("Action group");

    private Thread threadCar;
    private ThreadGroup threadGroupCars = new ThreadGroup("Cars group");

    public final static int NUMBEROFACTIONS = 10;


    public DrawBoard(){
        super.setSize(800, 800);
    }

    public void start(){
        jrgList.add(new JRG(50.05995, 19.94308, thisPanel, "1")); // JRG nr1
        jrgList.add(new JRG(50.03341, 19.93579, thisPanel, "2")); // JRG nr2
        jrgList.add(new JRG(50.07559, 19.88775, thisPanel, "3")); // JRG nr3
        jrgList.add(new JRG(50.0377, 20.00569, thisPanel, "4")); // JRG nr4
        jrgList.add(new JRG(50.09224, 19.92201, thisPanel,"5")); // JRG nr5
        jrgList.add(new JRG(50.01612, 20.0101, thisPanel, "6")); // JRG nr6
        jrgList.add(new JRG(50.09407, 19.97741, thisPanel, "7")); // JRG nr7
        jrgList.add(new JRG(50.07675, 20.03000, thisPanel, "P")); // JRG PSP
        jrgList.add(new JRG(49.96842, 19.79943, thisPanel, "S")); // JRG Skawina
        jrgList.add(new JRG(50.07324, 19.78613, thisPanel, "B")); // LSP Balice

        thread = new Thread(threadGroupAction, new ActionRunnable(actionList, thisPanel, jrgList));
        thread.start();

        for (JRG jrg : jrgList){
            for (int j = 0; j < 5; j++){
                jrg.addCar(new Car(jrg.getPixelX(), jrg.getPixelY()));
                threadCar = new Thread(threadGroupCars, new CarRunnable(jrg.getLastCar(), thisPanel, actionList, jrgList));
                threadCar.start();
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (JRG jrg : jrgList) {
            g.setColor(Color.BLACK);
            g.drawString(jrg.getText(), jrg.getPixelX() - 9, jrg.getPixelY() + 9);
            g.drawRect(jrg.getPixelX(), jrg.getPixelY(), jrg.getWidthOfRec(), jrg.getHeightOfRec());
        }

        if (actionList.size() == NUMBEROFACTIONS){
            for (Action action : actionList) {
                if (action.getState().equals("PZ")) {
                    g.setColor(Color.RED);
                    g.drawRect(action.getX(), action.getY(), 10, 10);
                } else if (action.getState().equals("MZ")) {
                    g.setColor(Color.BLUE);
                    g.drawRect(action.getX(), action.getY(), 10, 10);
                }
            }
        }


        for (JRG jrg : jrgList){
            int i = 5;
            for (Car car : jrg.getCarList()){
                if (car.isAvailable()){
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect((car.getX()-40)+ (i+=6), car.getY() - 8, 3,3);
                }
            }
        }

    }

}
