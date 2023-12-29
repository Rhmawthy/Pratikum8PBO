package Latihan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class hhh extends JFrame {

    private JLabel nama;
    private JButton ok;
    private JTextField form;

    public hhh() {
        setTitle("jframe");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);

        JLabel label = new JLabel();
        label.add(new JLabel("unsulbar"));
        add(nama);

        JButton buttom = new JButton();

        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                hhh h = new hhh();
            }

        });

    }

}
