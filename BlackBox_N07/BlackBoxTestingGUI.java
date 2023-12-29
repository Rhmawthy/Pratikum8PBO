package BlackBox_N07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BlackBoxTestingGUI extends JFrame {
    private JTextArea outputTextArea;
    private JTextField filePathTextField;

    public BlackBoxTestingGUI() {
        setTitle("Black Box Testing");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseFile();
            }
        });

        JButton testButton = new JButton("Run Test");
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runTest();
            }
        });

        filePathTextField = new JTextField();
        filePathTextField.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        panel.add(browseButton, BorderLayout.NORTH);
        panel.add(filePathTextField, BorderLayout.CENTER);
        panel.add(testButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void runTest() {
        String filePath = filePathTextField.getText();

        if (filePath.isEmpty()) {
            outputTextArea.append("Tidak ada file yang dipilih.\n");
            return;
        }

        // Mengambil input dari file input.txt
        String input = readFile("E:\\DASAR JAVA\\Pratikum_VIII\\src\\No_7\\input.txt");

        // Menjalankan program Java matriks dengan input dari file input.txt
        String output = runMatrixProgram(filePath, input);

        // Membandingkan output dengan isi file output.txt
        String expectedOutput = readFile("E:\\DASAR JAVA\\Pratikum_VIII\\src\\No_7\\output.txt");

        if (output.equals(expectedOutput)) {
            outputTextArea.append("Valid: Output sesuai dengan file output.txt\n");
        } else {
            outputTextArea.append("Tidak Valid: Output tidak sesuai dengan file output.txt\n");
        }
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private String runMatrixProgram(String filePath, String input) {
        // Simulasi program Java matriks dengan input dari file input.txt
        // Ubah kode berikut sesuai dengan program matriks Anda

        // Contoh program matriks

        // Menggunakan Scanner untuk input
        Scanner inputScanner = new Scanner(input);
        int rows = inputScanner.nextInt();
        int cols = inputScanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = inputScanner.nextInt();
            }
        }

        // Method print() dan println() untuk menghasilkan output
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Menggunakan StringWriter untuk mengambil output
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                printWriter.print(matrix[i][j] + " ");
            }
            printWriter.println();
        }

        printWriter.close();

        return writer.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BlackBoxTestingGUI().setVisible(true);
            }
        });
    }
}
 
