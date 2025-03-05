/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.Random;

/**
 *
 * @author Daniel
 */
public class Grafo {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    public Grafo(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Celda[filas][columnas];
        InicializarCeldas();
        ConstruirListaAdyacencias();
    }
    
    private void InicializarCeldas(){
        for (int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                String id = GenerarId(i,j);
                celdas[i][j] = new Celda(id, i, j);
            }
        }
    }
    
    private String GenerarId(int fila, int columna){
        char letra = (char) ('A' + fila);
        int numero = columna + 1;
        return letra + Integer.toString(numero);
    }
    
    public Celda ObtenerCelda(int fila, int columna){
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas){
            return celdas [fila][columna];
        }
        return null;
    }
    
    public Celda[] ObtenerAdyacentes(int fila, int columna){
        Celda[] adyacentes = new Celda[8];
        int index = 0;
        
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                Celda celda = ObtenerCelda(nuevaFila, nuevaColumna);
                if (celda != null){
                    adyacentes[index++] = celda;
                }
            }
        }
        
        Celda[] resultado = new Celda[index];
        for (int i = 0; i < index; i++){
            resultado[i] = adyacentes[i];
        }
        return resultado;
    }
    
    private void ConstruirListaAdyacencias(){
        for (int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                Celda celda = celdas[i][j];
                Celda[] adyacentes = ObtenerAdyacentes(i, j);
                for (Celda adyacente : adyacentes){
                    if (adyacente != null){
                        celda.AgregarAdyacente(adyacente);
                    }
                }
            }
        }
    }
    
    public void GenerarMinas(int numeroMinas){
        Random random = new Random();
        int minasColocadas = 0;
        
        while (minasColocadas < numeroMinas){
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            Celda celda = celdas[fila][columna];
            if (!celda.isEsMina()){
                celda.setEsMina(true);
                minasColocadas++;
            }
        }
    }
    
    public void ContarMinasAdyacentes(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                Celda celda = celdas[i][j];
                if (!celda.isEsMina()){
                    int minasAdyacentes = 0;
                    for (Celda adyacente : celda.getAdyacentes()){
                        if (adyacente != null && adyacente.isEsMina()){
                            minasAdyacentes++;
                        }
                    }
                    celda.setMinasAdyacentes(minasAdyacentes);
                }
            }
        }
    }        
}