package Frame;

import javax.swing.*;

public class MyFrame extends JFrame {

    private final DrawBoard drawBoard = new DrawBoard();

    public MyFrame(){
        this.setSize(800, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    public void initComponents(){
        this.getContentPane().add(drawBoard);

        drawBoard.start();
    }

}
