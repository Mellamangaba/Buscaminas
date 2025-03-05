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
            if (celda.getMinasAdyacentes() == 0 && !celda.isEsMina()){
                RevelarCeldasAdyacentes(fila, columna);
            }
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
    
    public boolean VerificarVictoria(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                Celda celda = grafo.ObtenerCelda(i, j);
                if (!celda.isEsMina() && !celda.isEsRevelada()){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void MarcarCelda(int fila, int columna){
        Celda celda = grafo.ObtenerCelda(fila, columna);
        if (celda != null && !celda.isEsRevelada()){
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
        
}



