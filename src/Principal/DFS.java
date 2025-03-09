package Principal;

public class DFS {
    private boolean[][] visited;
    private Tablero tablero;
    
    public DFS(Tablero tablero) {
        this.tablero = tablero;
        this.visited = new boolean[tablero.getFilas()][tablero.getColumnas()];
    }
    
    public void expandirDesde(int fila, int columna) {
        // Validar que la celda está dentro del tablero
        if (fila < 0 || fila >= tablero.getFilas() || columna < 0 || columna >= tablero.getColumnas()) {
            return;
        }

        Celda celda = tablero.ObtenerCelda(fila, columna);

        // Validar que la celda no ha sido visitada y no es una mina
        if (celda == null || visited[fila][columna] || celda.isEsMina()) {
            return;
        }
        
        visited[fila][columna] = true;
        
        // Reemplazamos descubrirCelda por RevelarCelda
        tablero.RevelarCelda(fila, columna);
        
        // Explorar en profundidad en todas las direcciones
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) { // Evitar llamar recursivamente en la misma celda
                    expandirDesde(fila + dx, columna + dy);
                }
            }
        }
    }
}

