
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterRecognitionGUI extends JFrame {

    private final double Alpha = 1;
    private double[] w = {0, 0, 0}; // bobot awal w0, w1, w2
    private JTextArea outputTextArea;

    private int[][] x = {
        {1, 1, 1},
        {1, 1, -1},
        {1, -1, 1},
        {1, -1, -1}
    };

    private int[] t = {1, -1, -1, -1};

    public CharacterRecognitionGUI() {
        setTitle("Pengenalan Karakter menggunakan ANN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        outputTextArea = new JTextArea(20, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton trainButton = new JButton("Latih Jaringan");
        JButton testButton = new JButton("Uji Jaringan");
        JButton exitButton = new JButton("Keluar");

        trainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainNeuralNetwork();
            }
        });

        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testNeuralNetwork();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(trainButton);
        buttonPanel.add(testButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void trainNeuralNetwork() {
        for (int epoch = 1; epoch <= 1000; epoch++) { // Jumlah iterasi pelatihan (1000 iterasi)
            outputTextArea.append("EPOCH = " + epoch + "\n");
            int errors = 0;
            for (int j = 0; j < x.length; j++) {
                double net = 0;
                for (int i = 0; i < x[j].length; i++) {
                    int xi = x[j][i];
                    double wi = w[i];
                    net = net + (xi * wi);
                }

                int y = (net > 0) ? 1 : -1;

                int target = t[j];
                if (target != y) {
                    errors++;
                    for (int i = 0; i < x[j].length; i++) {
                        double xi = x[j][i];
                        double delta = Alpha * (target - y) * xi;
                        w[i] = w[i] + delta;
                    }
                }
            }

            outputTextArea.append("Kesalahan pada epok " + epoch + " = " + errors + "\n");

            if (errors == 0) {
                outputTextArea.append("Pelatihan selesai dalam " + epoch + " epok.\n");
                break; // Berhenti jika tidak ada kesalahan pada epoch tertentu
            }
        }
    }

    private void testNeuralNetwork() {
        String input = JOptionPane.showInputDialog(null, "Masukkan nilai karakter (hanya 1 digit): ");
        if (input != null && !input.isEmpty()) {
            try {
                int xTest[] = new int[3];
                xTest[0] = -1;
                xTest[1] = Integer.parseInt(input);
                xTest[2] = 0; // Ketiga adalah 0 karena hanya satu digit yang dimasukkan

                double net = 0;
                for (int i = 0; i < xTest.length; i++) {
                    int xi = xTest[i];
                    double wi = w[i];
                    net = net + (xi * wi);
                }

                int y = 0;
                if (net > 0) {
                    y = 1;
                } else if (net == 0) {
                    y = 0;
                } else if (net < 0) {
                    y = -1;
                }

                outputTextArea.append("Hasil Pengenalan Karakter = " + y + "\n");
                outputTextArea.append("-----------------------------\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Masukkan nilai bilangan bulat yang valid!");
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CharacterRecognitionGUI();
            }
        });
    }
}
