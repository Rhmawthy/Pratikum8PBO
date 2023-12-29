package Kriptografi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiKrip extends JFrame {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CAESAR_SHIFT = 3;

    private JTextField inputTextField;
    private JTextField keyTextField;
    private JTextArea outputTextArea;
    private JButton caesarEncryptButton;
    private JButton caesarDecryptButton;
    private JButton vigenereEncryptButton;
    private JButton vigenereDecryptButton;

    public GuiKrip() {
        setTitle("Cryptography App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel inputLabel = new JLabel("Input:");
        inputTextField = new JTextField(20);
        inputPanel.add(inputLabel);
        inputPanel.add(inputTextField);

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel keyLabel = new JLabel("Key:");
        keyTextField = new JTextField(20);
        keyPanel.add(keyLabel);
        keyPanel.add(keyTextField);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel outputLabel = new JLabel("Output:");
        outputTextArea = new JTextArea(10, 20);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputPanel.add(outputLabel);
        outputPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        caesarEncryptButton = new JButton("Caesar Encrypt");
        caesarDecryptButton = new JButton("Caesar Decrypt");
        vigenereEncryptButton = new JButton("Vigenere Encrypt");
        vigenereDecryptButton = new JButton("Vigenere Decrypt");
        buttonPanel.add(caesarEncryptButton);
        buttonPanel.add(caesarDecryptButton);
        buttonPanel.add(vigenereEncryptButton);
        buttonPanel.add(vigenereDecryptButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(keyPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        caesarEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                String encryptedText = caesarEncrypt(input);
                outputTextArea.setText(encryptedText);
            }
        });

        caesarDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                String decryptedText = caesarDecrypt(input);
                outputTextArea.setText(decryptedText);
            }
        });

        vigenereEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                String key = keyTextField.getText();
                String encryptedText = vigenereEncrypt(input, key);
                outputTextArea.setText(encryptedText);
            }
        });

        vigenereDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                String key = keyTextField.getText();
                String decryptedText = vigenereDecrypt(input, key);
                outputTextArea.setText(decryptedText);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String caesarEncrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                int newIndex = (index + CAESAR_SHIFT) % ALPHABET.length();
                encryptedText.append(ALPHABET.charAt(newIndex));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    private String caesarDecrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                int newIndex = (index - CAESAR_SHIFT + ALPHABET.length()) % ALPHABET.length();
                decryptedText.append(ALPHABET.charAt(newIndex));
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    private String vigenereEncrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int textIndex = ALPHABET.indexOf(c);
                int keyChar = key.charAt(keyIndex) - 'A';
                int newIndex = (textIndex + keyChar) % ALPHABET.length();
                encryptedText.append(ALPHABET.charAt(newIndex));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    private String vigenereDecrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int textIndex = ALPHABET.indexOf(c);
                int keyChar = key.charAt(keyIndex) - 'A';
                int newIndex = (textIndex - keyChar + ALPHABET.length()) % ALPHABET.length();
                decryptedText.append(ALPHABET.charAt(newIndex));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiKrip();
            }
        });
    }
}