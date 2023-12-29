package TesMaxArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaxAreaOfIsland extends JFrame {
    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 50;

    private JPanel gridPanel;
    private JButton[][] gridButtons;
    private int[][] grid;
    private boolean[][] visited;

    public MaxAreaOfIsland() {
        setTitle("Max Area of Island");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
        grid = new int[GRID_SIZE][GRID_SIZE];
        visited = new boolean[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gridButtons[row][col] = new JButton();
                gridButtons[row][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                gridButtons[row][col].setBackground(Color.WHITE);
                gridButtons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        int row = (int) button.getClientProperty("row");
                        int col = (int) button.getClientProperty("col");
                        toggleCell(row, col);
                    }
                });
                gridPanel.add(gridButtons[row][col]);
                gridButtons[row][col].putClientProperty("row", row);
                gridButtons[row][col].putClientProperty("col", col);
            }
        }

        JButton simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxArea = getMaxAreaOfIsland();
                JOptionPane.showMessageDialog(null, "Max Area of Island: " + maxArea);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(simulateButton, BorderLayout.SOUTH);
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void toggleCell(int row, int col) {
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            gridButtons[row][col].setBackground(Color.BLACK);
        } else {
            grid[row][col] = 0;
            gridButtons[row][col].setBackground(Color.WHITE);
        }
    }

    private int getMaxAreaOfIsland() {
        int maxArea = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int area = dfs(row, col);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        resetVisited();
        return maxArea;
    }

    private int dfs(int row, int col) {
        if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE || grid[row][col] != 1 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        int area = 1;
        area += dfs(row - 1, col);
        area += dfs(row + 1, col);
        area += dfs(row, col - 1);
        area += dfs(row, col + 1);
        return area;
    }

    private void resetVisited() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                visited[row][col] = false;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MaxAreaOfIsland();
            }
        });
    }
}