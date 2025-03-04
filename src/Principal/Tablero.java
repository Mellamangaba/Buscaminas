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

    public Tablero(Grafo grafo, int filas, int columnas, int numeroMinas) {
        this.grafo = grafo;
        this.filas = filas;
        this.columnas = columnas;
        this.numeroMinas = numeroMinas;
    }
    
    private void InicializarTablero(){
        grafo.GenerarMinas(numeroMinas);
        grafo.ContarMinasAdyacentes();
    }
    
    public Celda ObtenerCelda(int fila, int columna){
        return grafo.ObtenerCelda(fila, columna);
    }
    
    public void RevelarCelda(int fila, int columna){
        Celda celda = grafo.ObtenerCelda(fila, columna);
        if (celda != null && !celda.isEsRevelada() && !celda.isEsMarcada()){
            celda.setEsRevelada(true);
            
        }
    }
    
    private void RevelarCeldasAdyacentes(int fila, int columna){
        Celda[] adyacentes = grafo.ObtenerAdyacentes(fila, columna);
        for (Celda adyacente : adyacentes){
            if (adyacente != null && !adyacente.isEsRevelada() && !adyacente.isEsMarcada()){
                adyacente.setEsRevelada(true);
                if (adyacente.getMinasAdyacentes() == 0){
                    RevelarCeldasAdyacentes(adyacente.getFila(), adyacente.getColumna());
                }
            }
        }
    }
    
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    
    
}



