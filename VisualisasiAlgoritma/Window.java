package VisualisasiAlgoritma;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    public Window() {
        initUi();
    }
    
    private void initUi() {
        setContentPane(new Surface());
        setTitle("jendelaJiwa");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window jendela = new Window();
                jendela.setVisible(true);
            }
            
        });
        
    }
    
}
