package Principal;

import java.util.*;

public class BFS {
    private boolean[][] visited;
    private Tablero tablero;
    
    public BFS(Tablero tablero) {
        this.tablero = tablero;
        this.visited = new boolean[tablero.getFilas()][tablero.getColumnas()];
    }
    
    public void expandirDesde(int fila, int columna) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{fila, columna});
        visited[fila][columna] = true;
        
        while (!queue.isEmpty()) {
            int[] celdaActual = queue.poll();
            int x = celdaActual[0], y = celdaActual[1];

            // Reemplazamos descubrirCelda por RevelarCelda
            tablero.RevelarCelda(x, y);

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx, ny = y + dy;
                    
                    // Validar que la celda está dentro del tablero
                    if (nx >= 0 && nx < tablero.getFilas() && ny >= 0 && ny < tablero.getColumnas()) {
                        Celda nuevaCelda = tablero.ObtenerCelda(nx, ny);
                        
                        // Validar que la celda no ha sido visitada y no es una mina
                        if (!visited[nx][ny] && nuevaCelda != null && !nuevaCelda.isEsMina()) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
}
