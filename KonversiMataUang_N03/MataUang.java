
package KonversiMataUang_N03;
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


public class MataUang extends JFrame implements ActionListener {

    private JTextField input;
    private JButton rp,dollar;
    private JLabel result;

    public MataUang() {
        setTitle("konMataUang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        input = new JTextField(10);
        panel.add(new JLabel("masukkan nilai"));
        panel.add(input);
        add(panel,BorderLayout.NORTH);
        
        JPanel buttom = new JPanel(new FlowLayout());
        rp = new JButton("Rupia");
        rp.addActionListener(this);
        buttom.add(rp);
        
        
        dollar = new JButton("Dollar");
        dollar.addActionListener(this);
        buttom.add(dollar);
        
        add(buttom,BorderLayout.CENTER);
        
        result = new JLabel("Result:");
        result.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(result,BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rp) {
            try{
                double Dollar = Double.parseDouble(input.getText());
                double rupiah = konverToRupiah(Dollar);
                result.setText("Result: "+ String.format("Rp" +"%.2f", rupiah ));
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "inavlid input");
                
            }
            
                
            
        }else if(e.getSource()== dollar){
            try{
                double rupiah = Double.parseDouble(input.getText());
                double Dollar = konverToDollar(rupiah);
                result.setText("Result : $"+ String.format("%.2f", Dollar));
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "invalid input");
            }
        }
        
    }

    private double konverToRupiah(double Dollar) {
        double kurs = 15000;
        return Dollar*kurs;
    }

    private double konverToDollar(double rupiah) {
      double kurs = 0.000067;
      return rupiah*kurs;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
             MataUang konver = new MataUang();
             
            }
            
        });
    }
    
    
}
