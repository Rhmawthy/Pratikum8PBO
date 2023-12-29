import javafx.application.Application;
import javafx.scene.layout.*;import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class ShortestPathFinding extends Application {

    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int CELL_SIZE = 40;

    private final Rectangle[][] grid = new Rectangle[ROWS][COLUMNS];
    private final boolean[][] visited = new boolean[ROWS][COLUMNS];

    private final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 4 arah (atas, bawah, kiri, kanan)

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Rectangle cell = new Rectangle(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.BLACK);
                grid[i][j] = cell;
                root.getChildren().add(cell);
            }
        }

        primaryStage.setTitle("Shortest Path Finding");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Mulai pencarian jalur terpendek dari (0, 0) ke (ROWS-1, COLUMNS-1)
        dfs(0, 0); // Ganti dengan bfs untuk menggunakan algoritma BFS
        showPath(ROWS - 1, COLUMNS - 1);
    }

    private void dfs(int row, int col) {
        if (row < 0 || col < 0 || row >= ROWS || col >= COLUMNS || visited[row][col])
            return;

        visited[row][col] = true;
        grid[row][col].setFill(Color.YELLOW);

        try {
            Thread.sleep(100); // Waktu tunggu untuk melihat perjalanan DFS (Hanya untuk tujuan visual)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(newRow, newCol);
        }
    }

    private void showPath(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            if (currentRow == row && currentCol == col)
                break;

            for (int[] dir : directions) {
                int newRow = currentRow + dir[0];
                int newCol = currentCol + dir[1];

                if (newRow >= 0 && newCol >= 0 && newRow < ROWS && newCol < COLUMNS && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                    grid[newRow][newCol].setFill(Color.GREEN);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
