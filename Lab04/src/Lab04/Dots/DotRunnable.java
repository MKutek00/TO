package Lab04.Dots;

import javax.swing.*;

public class DotRunnable implements Runnable{

    Dot dot;
    private JPanel panel;

    public DotRunnable(Dot dot, JPanel panel){
        this.dot = dot;
        this.panel = panel;
    }

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()){

                if (this.dot.getRandomNumber() % 2 == 0) {
                    this.dot.moveDot(panel);
//                    this.dot.setRandomNumber();
                }else{
                    this.dot.moveDot2();
                }
                panel.repaint();
                Thread.sleep(30);

            }
        }catch (InterruptedException e){
            panel.repaint();
        }
    }
}
