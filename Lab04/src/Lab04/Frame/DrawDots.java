package Lab04.Frame;


import Lab04.Dots.Dot;
import Lab04.Dots.DotRunnable;
import Lab04.Memento.Memento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawDots extends JPanel {

    private ArrayList<Dot> dotList;
    Memento memento;

    private final int height;
    private final int width;
    private final int numberOfPopulation;
    private ArrayList<Dot> listOfDots = new ArrayList<>();
    private ThreadGroup threadGroup = new ThreadGroup("Dot Group");
    private Thread thread;
    private JPanel thisPanel = this;


    public DrawDots(int width, int height, int numberOfPopulation){
        super.setSize(width, height);
        this.numberOfPopulation = numberOfPopulation;
        this.height = height;
        this.width = width;
    }


    public void start(){
        for (int i = 0; i < numberOfPopulation; i++){
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            listOfDots.add(new Dot(x, y));
            thread = new Thread(threadGroup, new DotRunnable(listOfDots.get(listOfDots.size()-1), thisPanel));
            thread.start();
        }
    }

    public void save(){
        dotList = new ArrayList<>();
        memento = new Memento(listOfDots.get(0).getStatee(), listOfDots.get(0).getStatee2(), listOfDots.get(0).getStatee3());

        for (int i = 0; i < listOfDots.size(); i++){
            try{
                memento = new Memento(listOfDots.get(i).getStatee(), listOfDots.get(i).getStatee2(), listOfDots.get(i).getStatee3());
                Dot dot = (Dot) listOfDots.get(i).clone();
                if (memento.getState() != null){
                    dot.setState(memento.getState());
                }
                if (memento.getState2() != null){
                    dot.setState2(memento.getState2());
                }
                if (memento.getState3() != null){
                    dot.setState3(memento.getState3());
                }
                dotList.add(dot);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void load(){
        listOfDots.clear();
        threadGroup.interrupt();
        for (int i = 0; i < dotList.size(); i++){
            listOfDots.add(dotList.get(i));
            thread = new Thread(threadGroup, new DotRunnable(listOfDots.get(listOfDots.size()-1), thisPanel));
            thread.start();
        }

    }

    public void stop(){
        threadGroup.interrupt();
        listOfDots.clear();
    }

    public void addNewDot(){
        int sum =  numberOfPopulation - listOfDots.size();
        for (int i = 0; i < sum; i++){
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            listOfDots.add(new Dot(x, y));
            thread = new Thread(threadGroup, new DotRunnable(listOfDots.get(listOfDots.size()-1), thisPanel));
            thread.start();
        }
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i = 0; i < listOfDots.size(); i++){

            if (listOfDots.get(i).getState().equals("resistantToInfection")){
                g.setColor(Color.BLUE);
                g.drawRect(listOfDots.get(i).getX(), listOfDots.get(i).getY(), listOfDots.get(i).getWidth(), listOfDots.get(i).getHeight());
            }else{
                if (listOfDots.get(i).getState2().equals("ill")){
                    if (listOfDots.get(i).getState3().equals("hasSymptoms")){
                        g.setColor(Color.RED);
                        g.drawRect(listOfDots.get(i).getX(), listOfDots.get(i).getY(), listOfDots.get(i).getWidth(), listOfDots.get(i).getHeight());
                    }else{
                        g.setColor(Color.ORANGE);
                        g.drawRect(listOfDots.get(i).getX(), listOfDots.get(i).getY(), listOfDots.get(i).getWidth(), listOfDots.get(i).getHeight());
                    }
                }else{
                    g.setColor(Color.BLACK);
                    g.drawRect(listOfDots.get(i).getX(), listOfDots.get(i).getY(), listOfDots.get(i).getWidth(), listOfDots.get(i).getHeight());
                    for (int j = 0; j < listOfDots.size(); j++) {
                        if (!listOfDots.get(j).getState2().equals("") && i != j){
                            if (!listOfDots.get(i).isSpaceBetween(listOfDots.get(j)) && listOfDots.get(j).getState2().equals("ill")){
                                if (!listOfDots.get(j).getState3().equals("")){
                                    if (listOfDots.get(j).getState3().equals("hasSymptoms") ){
                                        listOfDots.get(i).change();
                                    }else if (listOfDots.get(j).getState3().equals("hasNoSymptoms")){
                                        listOfDots.get(i).change();
                                    }
                                }

                            }
                        }
                    }
                }
            }


            if (!listOfDots.get(i).checkIfDotIsInLabel(thisPanel)){
                listOfDots.remove(listOfDots.get(i));
                addNewDot();
            }
        }
    }
}
