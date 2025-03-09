/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;


public class Tablero {

    private Grafo grafo;
    private int filas;
    private int columnas;
    private int numeroMinas;

    public Tablero(int filas, int columnas, int numeroMinas) {
        this.grafo = new Grafo(filas, columnas);
        this.filas = filas;
        this.columnas = columnas;
        this.numeroMinas = numeroMinas;
        InicializarTablero();
    }

    private void InicializarTablero() {
        grafo.GenerarMinas(numeroMinas);
        grafo.ContarMinasAdyacentes();
    }

    public Celda ObtenerCelda(int fila, int columna) {
        return grafo.ObtenerCelda(fila, columna);
    }

    public void RevelarCeldaProfundidad(int fila, int columna) {
        Celda celda = grafo.ObtenerCelda(fila, columna);

        if (celda != null && !celda.isEsRevelada() && !celda.isEsMarcada()) {
            celda.setEsRevelada(true);
            if (celda.getMinasAdyacentes() == 0 && !celda.isEsMina()) {
                BusquedaProfundidad(fila, columna);
            }
        }
    }    
    
    public void RevelarCeldaAmplitud(int fila, int columna) {
        Celda celda = grafo.ObtenerCelda(fila, columna);

        if (celda != null && !celda.isEsRevelada() && !celda.isEsMarcada()) {
            celda.setEsRevelada(true);
            if (celda.getMinasAdyacentes() == 0 && !celda.isEsMina()) {
                BusquedaAmplitud(fila, columna);
            }
        }
    }
    
    private void BusquedaProfundidad(int fila, int columna) {
        Celda[] adyacentes = grafo.ObtenerAdyacentes(fila, columna);

        for (Celda adyacente : adyacentes) {
            if (adyacente != null && !adyacente.isEsRevelada() && !adyacente.isEsMarcada()) {
                adyacente.setEsRevelada(true);

                if (adyacente.getMinasAdyacentes() == 0 && !adyacente.isEsMina()) {
                    BusquedaProfundidad(adyacente.getFila(), adyacente.getColumna());
                }
            }
        }
    }    
    
    private void BusquedaAmplitud(int fila, int columna){
        int capacidadCola = filas + columnas;
        Celda[] cola = new Celda[capacidadCola];
        int frente = 0;
        int fin = 0;
        
        Celda inicial = grafo.ObtenerCelda(fila, columna);
        cola[fin++] = inicial;
        
        while (frente < fin){
            Celda actual = cola[frente++];                        
                
            actual.setEsRevelada(true);
            
            if (actual.getMinasAdyacentes() == 0 && !actual.isEsMina()){
                Celda[] adyacentes = grafo.ObtenerAdyacentes(actual.getFila(), actual.getColumna());
                
                for (Celda adyacente : adyacentes){
                    if (adyacente != null && !adyacente.isEsRevelada() && !adyacente.isEsMarcada()){
                        if (fin < capacidadCola){
                            cola[fin++] = adyacente;
                        }
                    }
                }
            }
        }
    }
    
    public boolean VerificarVictoria() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = grafo.ObtenerCelda(i, j);
                if (!celda.isEsMina() && !celda.isEsRevelada()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void MarcarCelda(int fila, int columna) {
        Celda celda = grafo.ObtenerCelda(fila, columna);
        if (celda != null && !celda.isEsRevelada()) {
            celda.setEsMarcada(!celda.isEsMarcada());
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getNumeroMinas() {
        return numeroMinas;
    }

    public Grafo getGrafo() {
        return grafo;
    }

}
