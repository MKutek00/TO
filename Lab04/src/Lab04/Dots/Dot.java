package Lab04.Dots;

import Lab04.State.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Dot implements Cloneable{

    private IState state;
    private IState state2;
    private IState state3;

    Timer timer = new Timer();
    Random random = new Random();
    private int x;
    private int y;
    private final int width = 5;
    private final int height = 5;
    private int dx;
    private int dy;

    private int randomNumber;


    public Object clone() throws CloneNotSupportedException {
        Dot dot = (Dot) super.clone();
        return dot;
    }


    public Dot(int x, int y){
        this.dx = (int) (Math.random() * 5);
        this.dy = (int) (Math.random() * 5);
        if (this.dx == 0){
            this.dx = 1;
        }
        if (this.dy == 0){
            this.dy = 1;
        }
        this.x = x;
        this.y = y;

        this.randomNumber = (int) (Math.random() * 100 + 1);

        case1();

//        case2();
    }

    public void case1(){
        int number = (int) (Math.random() * 100 + 1);
        if (number % 3 == 1 || number % 3 == 0){
            state = new StateSusceptible();
            int number2 = (int) (Math.random() * 100+1);
            if (number2 % 10 == 1){
                state2 = new StateIll();
                int number3 = (int) (Math.random() * 100 + 1);
                if (number3 % 2 == 1){
                    state3 = new StateHasSymptoms();
                }else{
                    state3 = new StateHasNoSymptoms();
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        state = new StateResistantToInfection();
                        state2 = new StateHealthy();
                    }
                }, random.nextInt(10000)+10000);
            }else{
                state2 = new StateHealthy();
            }
        }else{
            state = new StateResistantToInfection();
        }
    }

    public void case2(){
        state = new StateSusceptible();
        int number2 = (int) (Math.random() * 100+1);
        if (number2 % 10 == 1){
            state2 = new StateIll();
            int number3 = (int) (Math.random() * 100 + 1);
            if (number3 % 2 == 1){
                state3 = new StateHasSymptoms();
            }else{
                state3 = new StateHasNoSymptoms();
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    state = new StateResistantToInfection();
                    state2 = new StateHealthy();
                }
            }, random.nextInt(10000)+10000);
        }else{
            state2 = new StateHealthy();
        }
    }

    public void change(){
        int number = (int) (Math.random() * 100 + 1);
        if (number % 2 == 0) {
            this.setState2(new StateIll());
            this.setState3(new StateHasNoSymptoms());
        }else{
            this.setState2(new StateIll());
            this.setState3(new StateHasSymptoms());
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                state = new StateResistantToInfection();
                state2 = new StateHealthy();
            }
        }, random.nextInt(10000)+10000);
    }

    public int getRandomNumber(){
        return randomNumber;
    }

    public void setRandomNumber(){
        this.randomNumber = (int) (Math.random() * 100 + 1);
    }

    public boolean isSpaceBetween(Dot dot){
        if (Math.abs(x - dot.x) <= 3 && Math.abs(y - dot.y) <= 3){
            return false;
        }
        return true;
    }

    public boolean checkIfDotIsInLabel(JPanel panel){
        Rectangle rectangle = panel.getBounds();

        if ((x + width) >= rectangle.getMaxX() + 1){
            return false;
        }else if ((y+height) >= rectangle.getMaxY() + 1){
            return false;
        }else if (x <= rectangle.getMinX() - 1){
            return false;
        }else if (y <= rectangle.getMinY() - 1){
            return false;
        }
        return true;
    }

    public void moveDot2(){
        if (this.getRandomNumber() % 8 == 0){
            x += dx;
            y += dy;
        }else if (this.getRandomNumber() % 8 == 1){
            x -= dx;
            y += dy;
        }else if(this.getRandomNumber() % 8 == 2){
            x += dx;
            y -= dy;
        }else if (this.getRandomNumber() % 8 == 3){
            x -= dx;
            y -= dy;
        }else if (this.getRandomNumber() % 8 == 4){
            x += dx;
        }else if (this.getRandomNumber() % 8 == 5){
            x -= dx;
        }else if (this.getRandomNumber() % 8 == 6){
            y += dy;
        }else{
            y -= dy;
        }
    }

    public void moveDot(JPanel panel){
        Rectangle rectangle = panel.getBounds();
        if (this.getRandomNumber() % 4 == 0){
            x += dx;
            y += dy;
        }else if (this.getRandomNumber() % 4 == 1){
            x -= dx;
            y += dy;
        }else if(this.getRandomNumber() % 4 == 2){
            x += dx;
            y -= dy;
        }else{
            x -= dx;
            y -= dy;
        }

        if ((y + width) >= rectangle.getMaxY()){
            y = (int) rectangle.getMaxY() - width;
            dy = -dy;
        }

        if ((x + height) >= rectangle.getMaxX()){
            x = (int) rectangle.getMaxX() - height;
            dx = -dx;
        }

        if (x<= rectangle.getMinX()){
            x = (int) rectangle.getMinX();
            dx = -dx;
        }

        if (y < rectangle.getMinY()){
            y = (int) rectangle.getMinY();
            dy = -dy;
            setRandomNumber();
        }
    }

    public String getState() {
        if (this.state == null){
            return "";
        }
        return state.handle();
    }

    public String getState2(){
        if (this.state2 == null){
            return "";
        }
        return state2.handle();
    }

    public String getState3(){
        if (this.state3 == null){
            return "";
        }
        return state3.handle();
    }

    public IState getStatee(){
        return this.state;
    }

    public IState getStatee2(){
        return this.state2;
    }

    public IState getStatee3(){
        return this.state3;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void setState2(IState state2) {
        this.state2 = state2;
    }

    public void setState3(IState state3) {
        this.state3 = state3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "state=" + state.handle() +
                ", state2=" + state2.handle() +
                ", state3=" + state3.handle() +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", dx=" + dx +
                ", dy=" + dy +
                ", randomNumber=" + randomNumber +
                '}';
    }
}
