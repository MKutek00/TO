package Lab04.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    private final int n;
    private final int m;
    public int numberOfPopulation;

    public MyFrame(int n, int m, int numberOfPopulation){
        this.n = n;
        this.m = m;
        this.numberOfPopulation = numberOfPopulation;
        this.setSize(n, m);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents(){
        drawDots = new DrawDots(n, m, numberOfPopulation);
        buttonsPanel = new JPanel();

        this.getContentPane().add(drawDots);

        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        JButton buttonStart = (JButton) buttonsPanel.add(new JButton("Start"));

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDrawing();
            }
        });

        JButton buttonStop = (JButton) buttonsPanel.add(new JButton("Stop"));

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });


        JButton buttonLoad = new JButton("Load");

        JButton buttonSave = (JButton) buttonsPanel.add(new JButton("Save"));

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonLoad.setEnabled(true);
                save();
            }
        });


        buttonsPanel.add(buttonLoad);
        buttonLoad.setEnabled(false);

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
                buttonLoad.setEnabled(false);
            }
        });




    }

    public void save(){
        drawDots.save();
    }

    public void load(){
        drawDots.load();
    }

    public void startDrawing(){
        drawDots.start();
    }

    public void stop(){
        drawDots.stop();
    }

    private JPanel buttonsPanel = new JPanel();
    public DrawDots drawDots;
}
