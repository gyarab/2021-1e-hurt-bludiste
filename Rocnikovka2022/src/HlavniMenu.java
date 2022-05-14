import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HlavniMenu {
    private static int sizeI=3;
    public static int sizeJ=3;
    public static int getSizeJ(){//pro predani velikost bludiste do dalsi tridy
        return sizeJ;
    }
    public static int getSizeI(){
        return sizeI;
    }
    private JFrame f = new JFrame("Menu");
    private JButton bok = new JButton("generovat");
    private JPanel panel = new JPanel();

    String[] optionsToChoose = {"5x5", "10x10", "15x15", "20x20", "25x25", "40x50", "Fullscreen"};
    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);


    public HlavniMenu() {
        f.setPreferredSize(new Dimension(600, 600));
        f.pack();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bok.setPreferredSize(new Dimension(120, 40));
        jComboBox.setBounds(80, 60, 140, 20);
        panel.add(bok);
        panel.add(jComboBox);
        f.getContentPane().add(panel);


        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //nastavíme velikost blusite podle mozností v JComboBoxu
                JComboBox cb = (JComboBox)ae.getSource();
                String size = (String)cb.getSelectedItem();
                if(size == "5x5"){
                    sizeI = 5;
                    sizeJ = 5;
                }
                else if(size == "15x15"){
                    sizeI = 15;
                    sizeJ = 15;
                }
                else if(size == "10x10"){
                    sizeI = 10;
                    sizeJ = 10;
                }
                else if(size == "20x20"){
                    sizeI = 20;
                    sizeJ = 20;
                }
                else if(size == "25x25"){
                    sizeI = 25;
                    sizeJ = 25;
                }
                else if(size == "40x50"){
                    sizeI = 40;//vyska
                    sizeJ = 50;//sirka
                }
                else if(size == "Fullscreen"){
                    sizeI = 42;//vyska
                    sizeJ = 78;//sirka
                }
            }
        });

        //action listener pro tlacitko "generovat"
        bok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                f.dispose();
                new SecondFrame();
            }
        });
        f.setSize(500,500);
        f.setVisible(true);
    }

    private void onExit() throws IOException {
        f.dispose();
        new HlavniMenu();
    }
}