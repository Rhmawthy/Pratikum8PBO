package CRUD_N04;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CRUD extends JFrame implements ActionListener {

    private JTable tabel;
    private DefaultTableModel tabelmodel;
    private JButton Binsert, Bupdate, Bdelete, Breset, Bopen, Bsave;
    private JTextField nim, nama, sks;

    public CRUD() {
        setTitle("CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabelmodel = new DefaultTableModel();
        tabelmodel.addColumn("NIM");
        tabelmodel.addColumn("Name");
        tabelmodel.addColumn("SKS");
        tabel = new JTable(tabelmodel);

        JScrollPane scroll = new JScrollPane(tabel);
        add(scroll, BorderLayout.CENTER);

        JPanel input = new JPanel(new GridLayout(4, 2));
        input.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        nim = new JTextField();
        input.add(new JLabel("NIM : "));
        input.add(nim);
        //nim = new JTextField();

        nama = new JTextField();
        input.add(new JLabel("NAMA : "));
        nama = new JTextField();
        input.add(nama);

        sks = new JTextField();
        input.add(new JLabel("SKS : "));
        sks = new JTextField();
        input.add(sks);

        add(input, BorderLayout.NORTH);

        JPanel buttom = new JPanel(new FlowLayout());
        Binsert = new JButton("Insert");
        Binsert.addActionListener(this);
        buttom.add(Binsert);

        Bupdate = new JButton("Update");
        Bupdate.addActionListener(this);
        buttom.add(Bupdate);
        //buttom.add(Binsert);

        Bdelete = new JButton("Delete");
        Bdelete.addActionListener(this);
        buttom.add(Bdelete);

        Breset = new JButton("Reset");
        Breset.addActionListener(this);
        buttom.add(Breset);

        Bopen = new JButton("Open");
        Bopen.addActionListener(this);
        buttom.add(Bopen);

        Bsave = new JButton("Save");
        Bsave.addActionListener(this);
        buttom.add(Bsave);

        add(buttom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Binsert) {
            addRow();

        } else if (e.getSource() == Bupdate) {
            updateRow();

        } else if (e.getSource() == Bdelete) {
            deleteRow();
            
        }else if(e.getSource() == Breset){
            resetFields();
            
        }else if (e.getSource() == Bopen) {
            openFromFile();
            
        }else if(e.getSource()== Bsave){
            saveToFile();
        }

    }

    private void addRow() {
        String NIM = nim.getText();
        String NAMA = nama.getText();
        String SKS = sks.getText();

        Vector<String> row = new Vector<>();
        row.add(NIM);
        row.add(NAMA);
        row.add(SKS);

        tabelmodel.addRow(row);
        resetFields();

    }

    private void updateRow() {
        int selectedRow = tabel.getSelectedRow();
        if (selectedRow != -1) {
            String NIM = nim.getText();
            String NAMA = nama.getText();
            String SKS = sks.getText();

            tabelmodel.setValueAt(nim, selectedRow, 0);
            tabelmodel.setValueAt(nama, selectedRow, 1);
            tabelmodel.setValueAt(sks, selectedRow, 2);
            resetFields();

        } else {
            JOptionPane.showMessageDialog(this, "pilih baris untuk update");
        }
    }

    private void deleteRow() {
        int selectedRow = tabel.getSelectedRow();
        if (selectedRow != -1)  {
            tabelmodel.removeRow(selectedRow);
            resetFields();
            
        }else{
            JOptionPane.showMessageDialog(this, "pilih baris yang akan di hapus");
        }

    }
    private void openFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    tabelmodel.addRow(data);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   
    }
    private void saveToFile() {
      JFileChooser fileChooser = new JFileChooser();
      int value = fileChooser.showSaveDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            try{
               File file = fileChooser.getSelectedFile();
                BufferedWriter baca = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < tabel.getRowCount(); i++) {
                    for (int j = 0; j < tabel.getColumnCount(); j++) {
                        baca.write(tabel.getValueAt(i,j).toString());
                        if (j < tabel.getColumnCount()-1) {
                            baca.write(",");
                            
                        }
                        
                    }
                    baca.newLine();
                    
                }
                baca.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            
        }
   
    }

    private void resetFields() {
        nim.setText("");
        nama.setText("");
        sks.setText("");
        tabel.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CRUD crud = new CRUD();
            }

        });
    }



}
