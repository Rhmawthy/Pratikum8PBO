package Latihan;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ajaranBapak extends JFrame {

    private JPanel basePanel = null;
    private JLabel LabelInformatika = null;
    private JTextField fieldInformatika = null;
    private JLabel labelUnsulbar = null;
    private JTextField fieldUnsulbar = null;
    private JButton buttonOk = null;
    private JButton buttonClose = null;

    public ajaranBapak() {
        unitUi();
    }

    private void unitUi() {
        setContentPane(getBasePanel());
        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private JPanel getBasePanel() {
        if (basePanel == null) {
            basePanel = new JPanel();
            basePanel.add(getLabelInformatika());
            basePanel.add(fieldInformatika());
            basePanel.add(getButtonOk());
            basePanel.add(getButtonClose());
            basePanel.add(getlabelUnsulbar());

        }
        return basePanel;
    }
    private JLabel getLabelInformatika(){
        if (LabelInformatika == null) {
            LabelInformatika = new JLabel("informatika");
                
        }
        return LabelInformatika;
    }
    private JLabel getlabelUnsulbar(){
        if (labelUnsulbar == null) {
            labelUnsulbar = new JLabel("Unsulbar");
                
        }
        return labelUnsulbar;
    }
    private JTextField  fieldInformatika(){
        if (fieldInformatika == null) {
            fieldInformatika = new JTextField();
            fieldInformatika.setPreferredSize(new Dimension(80,20));
            
        }
        return fieldInformatika;
         
    }
     private JButton  getButtonOk(){
        if (buttonOk == null) {
            buttonOk = new JButton("ok");
            
        }
        return buttonOk;
         
    }
     private JButton  getButtonClose(){
        if (buttonClose == null) {
            buttonClose = new JButton("close");
            buttonClose.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
                
            });
            
        }
        return buttonClose;
         
    }
     
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ajaranBapak omsugi = new ajaranBapak();
            }

        });
    }

}
