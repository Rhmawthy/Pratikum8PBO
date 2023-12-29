package Kriptografi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kriptografi extends JFrame {
    private JTextArea inputTextArea, outputTextArea;
    private JTextField keyField;
    private JButton caesarButton, vigenereButton;

    public Kriptografi() {
        setTitle("Crypto App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        keyField = new JTextField();
        caesarButton = new JButton("Caesar Cipher");
        vigenereButton = new JButton("Vigenere Cipher");

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.add(new JLabel("Input Text:"));
        topPanel.add(inputScrollPane);
        topPanel.add(new JLabel("Output Text:"));
        topPanel.add(outputScrollPane);
        topPanel.add(new JLabel("Key:"));
        topPanel.add(keyField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(caesarButton);
        buttonPanel.add(vigenereButton);

        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        caesarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                int key = Integer.parseInt(keyField.getText());
                String encryptedText = caesarCipher(inputText, key);
                outputTextArea.setText(encryptedText);
            }
        });

        vigenereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String key = keyField.getText();
                String encryptedText = vigenereCipher(inputText, key);
                outputTextArea.setText(encryptedText);
            }
        });
    }

    private String caesarCipher(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                result.append((char) (((character - base + key) % 26 + 26) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    private String vigenereCipher(String text, String key) {
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int shift = key.charAt(keyIndex) - base;
                result.append((char) (((character - base + shift) % 26 + 26) % 26 + base));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(character);
            }
        }
        return result.toString();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Kriptografi().setVisible(true);
            }
        });
    }
}


