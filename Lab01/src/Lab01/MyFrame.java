package Lab01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Formatter;

public class MyFrame extends JFrame {
    private final static String urlString = "https://www.nbp.pl/kursy/xml/lasta.xml";
    private static final CurrencyRateTable currencyRateTable = CurrencyRateTable.getInstance();

    public MyFrame(){
        this.setBounds(300, 300, 300, 200);

        try{
            ReadXML.readXML(urlString);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        initComponents();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new Color(123, 50, 250));
        jPanel2.setBackground(Color.ORANGE);

        jLabel1.setText("Numer tabeli: " + currencyRateTable.getTableNumber());
        jLabel2.setText("Data publikacji listy walut: " + currencyRateTable.getDateOfPublication());

        jLabel3.setText("");
        jLabel4.setText("Podaj wartość waluty: ");

        jButton2.setText("Oblicz");


        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                jButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {

                        String string = jTextField1.getText();

                        for (Currency c : currencyRateTable.getCurrencyList()){
                            if (c.getCurrencyCode().equals(currency1)){
                                currencyRate1 = c.getCurrencyRate();
                                conversionFactor1 = c.getConversionFactor();
                            }
                            if (c.getCurrencyCode().equals(currency2)){
                                currencyRate2 = c.getCurrencyRate();
                                conversionFactor2 = c.getConversionFactor();
                            }
                        }

                        try {

                            Double amountOfMoney = ((currencyRate1 * conversionFactor2  / (currencyRate2 * conversionFactor1 )) * Double.parseDouble(string));
                            Formatter formatter = new Formatter();
                            formatter.format("%.4f", amountOfMoney);
                            jLabel3.setText(formatter.toString());

                        }catch (NumberFormatException exception){
//                            JOptionPane.showMessageDialog(null, "To nie jest liczba");
                            jLabel3.setText("To nie jest liczba");
                        }

                    }
                });
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });


        for (Currency c : currencyRateTable.getCurrencyList()){

            jComboBox1.addItem(new ComboItem(c.getCurrencyCode(), c.getCurrencyCode()));
            jComboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ComboItem comboItem = (ComboItem)((JComboBox)e.getSource()).getSelectedItem();
                    currency1 = comboItem.getKey();
                }
            });

            jComboBox2.addItem(new ComboItem(c.getCurrencyCode(), c.getCurrencyCode()));
            jComboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ComboItem comboItem = (ComboItem)((JComboBox)e.getSource()).getSelectedItem();

                    assert comboItem != null;
                    currency2 = comboItem.getKey();
                }
            });
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addContainerGap(49, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                        .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jComboBox1, 0, 145, Short.MAX_VALUE)
                                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );


        pack();
    }

    private double currencyRate1 = 0;
    private double currencyRate2 = 0;
    private double conversionFactor1 = 0;
    private double conversionFactor2 = 0;

    private String currency1;
    private String currency2;

    private JPanel jPanel1;
    private JPanel jPanel2;

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;

    private JButton jButton1;
    private JButton jButton2;

    private JTextField jTextField1;

    private JComboBox jComboBox1;
    private JComboBox jComboBox2;

    public void exit(){
        this.dispose();
    }
}
