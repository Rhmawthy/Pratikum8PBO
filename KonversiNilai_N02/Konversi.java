package KonversiNilai_N02;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



public class Konversi extends JFrame implements ActionListener {

    private JTextField input;
    private JButton konverKeHuruf, konverKeAngka;
    private JLabel result;

    public Konversi() {
        setTitle("KonversiNilai");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());
        input = new JTextField(10);
        panel.add(new JLabel("Masukkan nilai: "));
        panel.add(input);
        add(panel, BorderLayout.NORTH);

        JPanel button = new JPanel(new FlowLayout());
        konverKeHuruf = new JButton("Nilai Huruf");
        konverKeHuruf.addActionListener(this);
        button.add(konverKeHuruf);

        konverKeAngka = new JButton("Nilai Angka");
        konverKeAngka.addActionListener(this);
        button.add(konverKeAngka);

        add(button, BorderLayout.CENTER);

        result = new JLabel("Result: ");
         result.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(result, BorderLayout.SOUTH); // Mengubah posisi resultLabel ke SOUTH


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == konverKeHuruf) {
            try {
                int nilaiAngka = Integer.parseInt(input.getText());
                String nilaiHuruf = konverKeHuruf(nilaiAngka);
                result.setText("Nilai Huruf : " + nilaiHuruf);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input ");
            }

        } else if (e.getSource() == konverKeAngka) {
            String nilaiHuruf = input.getText();
            int nilaiAngka = konverKeAngka(nilaiHuruf);
            if (nilaiAngka <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid input");

            } else {
                result.setText("Nilai Angka: " + nilaiAngka);
            }
        }
    }

    private String konverKeHuruf(int nilaiAngka) {
        if (nilaiAngka >= 90) {
            return "A";
        } else if (nilaiAngka >= 85) {
            return "A-";
        } else if (nilaiAngka >= 80) {
            return "B+";
        } else if (nilaiAngka >= 75) {
            return "B";
        } else if (nilaiAngka >= 70) {
            return "B-";
        } else if (nilaiAngka >= 65) {
            return "C";
        } else if (nilaiAngka >= 60) {
            return "D";
        } else {
            return "E";
        }
    }

    private int konverKeAngka(String nilaiHuruf) {
        switch (nilaiHuruf.toUpperCase()) {
            case "A":
                return 90;
            case "A-":
                return 85;
            case "B+":
                return 80;
            case "B":
                return 75;
            case "B-":
                return 70;
            case "C":
                return 65;
            case "D":
                return 60;
            case "E":
                return 0;
            default:
                throw new IllegalArgumentException("Invalid grade letter: " + nilaiHuruf);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Konversi nilai = new Konversi();
            }
        });
    }
}
