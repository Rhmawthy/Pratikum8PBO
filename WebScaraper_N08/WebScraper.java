package WebScaraper_N08;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebScraper extends JFrame {
    private JTextField urlField;
    private JButton scrapeButton;
    private JTextArea resultArea;

    public WebScraper() {
        setLayout(new FlowLayout());

        urlField = new JTextField(20);
        add(urlField);

        scrapeButton = new JButton("Scrape");
        add(scrapeButton);

        resultArea = new JTextArea(20, 30);
        add(new JScrollPane(resultArea));

        event e = new event();
        scrapeButton.addActionListener(e);
    }

    public class event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String url = urlField.getText();
            try {
                Document doc = Jsoup.connect(url).get();
                String text = doc.body().text();
                Map<String, Integer> frequencyMap = calculateFrequency(text);
                displayFrequency(frequencyMap);
            } catch (IOException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        }
    }

    private Map<String, Integer> calculateFrequency(String text) {
        String[] words = text.split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }

    private void displayFrequency(Map<String, Integer> frequencyMap) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        resultArea.setText(result.toString());
    }

    public static void main(String args[]) {
        WebScraper gui = new WebScraper();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(400, 400);
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        gui.setTitle("Web Scraper");
    }
}