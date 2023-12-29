package Latihan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ujian extends JFrame {

    private JPanel panel = null;
    private JTextField input = null;
    private JTextField output = null;
    private JButton konver = null;
    private JComboBox<String> JcomboBoxR = null;
    private JComboBox<String> JcomboBoxS = null;
    private String[] label = new String[]{
        "KM", "HM", "DAM", "M", "DM", "CM", "MM"
    };

    public ujian() {
        unitUi();

    }

    private void unitUi() {
        setContentPane(getPanel());
        setTitle("konver");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getJComboBoxR());
            panel.add(getinput());
            panel.add(getKonver());
            panel.add(getJComboBoxS());
            panel.add(getOutput());
            panel.setBackground(Color.BLUE);

        }
        return panel;
    }

    private JComboBox<String> getJComboBoxR() {
        if (JcomboBoxR == null) {
            JcomboBoxR = new JComboBox<>(label);
        }
        return JcomboBoxR;
    }

    private JComboBox<String> getJComboBoxS() {
        if (JcomboBoxS == null) {
            JcomboBoxS = new JComboBox<>(label);

        }
        return JcomboBoxS;
    }

    private JTextField getinput() {
        if (input == null) {
            input = new JTextField();
            input.setPreferredSize(new Dimension(80, 20));
            input.setBackground(Color.red);

        }
        return input;
    }

    private JButton getKonver() {
        if (konver == null) {
            konver = new JButton("konver-->");
            konver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                   String sKM = km.getText();
//                   double KM = Double.parseDouble(sKM);
//                   double CM = KM*100_000;
//                   String scM = String.valueOf(CM);
//                   cm.setText(scM);
                    //cm.setText(String.valueOf(Double.parseDouble(km.getText()) * 100_00));

                    //cm.setText(String.valueOf(Double.parseDouble(100_000))); 
                    int indexIn = JcomboBoxR.getSelectedIndex();
                    int indexOut = JcomboBoxS.getSelectedIndex();

                    String siInput = input.getText();
                    double in = Double.parseDouble(siInput);
                    double out = convert(indexIn, indexOut, in);
                    output.setText(""+String.format("%.06f", out));
                   
                }

            });

        }
        return konver;
    }

    private double convert(int dari, int ke, double value) {
        double km = value/Math.pow(10, dari);
        double result = km*Math.pow(10, ke);
       
        return result;

    }

    private JTextField getOutput() {
        if (output == null) {
            output = new JTextField();
            output.setPreferredSize(new Dimension(80, 20));

        }
        return output;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ujian u = new ujian();
            }
        });
    }

}
