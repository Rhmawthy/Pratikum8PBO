package KonversiSatuanPanjangN01;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Kovers extends JFrame implements ActionListener {
    
    private JTextField input;
    private JComboBox<String> nilai, converke;
    private JButton konversi;
    private JLabel result;
    
    private String[] satuan = {"KM", "HM", "DAM", "M", "DM", "CM", "MM"};
    private double[] nilaisatuan = {1000, 100, 10, 1, 0.1, 0.01, 0.001};
    
    public Kovers() {
        setTitle("koversi panjang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        input = new JTextField(10);
        panel.add(new JLabel("masukkan nilai"));
        panel.add(input);
        
        nilai = new JComboBox<>(satuan);
        panel.add(new JLabel("pilih satuan"));
        panel.add(nilai);
        
        converke = new JComboBox<>(satuan);
        panel.add(new JLabel("convert ke"));
        panel.add(converke);
        
        konversi = new JButton("Convert");
        konversi.addActionListener(this);
        panel.add(konversi);
        
        result = new JLabel("Result : ");
        panel.add(result);
        
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== konversi) {
            try{
                double inputValue = Double.parseDouble(input.getText());
                int dari = nilai.getSelectedIndex();
                int ke = converke.getSelectedIndex();
                
                double nilaiHasil = inputValue* nilaisatuan[dari] / nilaisatuan[ke];
                result.setText("result : "+ nilaiHasil + " "+ satuan[ke] );
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"invalid plase enter the number");
                
            }
            
        }
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Kovers k = new Kovers();
                //k.setVisible(true);
            }
        });
    }
    
}
