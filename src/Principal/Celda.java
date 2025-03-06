/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

public class Celda {

    private String id;
    private int fila;
    private int columna;
    private boolean esMina;
    private boolean esMarcada;
    private boolean esRevelada;
    private int minasAdyacentes;
    private Celda[] adyacentes;

    public Celda(String id, int fila, int columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.esMina = false;
        this.esMarcada = false;
        this.esRevelada = false;
        this.minasAdyacentes = 0;
        this.adyacentes = new Celda[8];
    }

    public String getId() {
        return id;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isEsMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    public boolean isEsMarcada() {
        return esMarcada;
    }

    public void setEsMarcada(boolean esMarcada) {
        this.esMarcada = esMarcada;
    }

    public boolean isEsRevelada() {
        return esRevelada;
    }

    public void setEsRevelada(boolean esRevelada) {
        this.esRevelada = esRevelada;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public Celda[] getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(Celda[] adyacentes) {
        this.adyacentes = adyacentes;
    }

    public void AgregarAdyacente(Celda celda) {
        for (int i = 0; i < adyacentes.length; i++) {
            if (adyacentes[i] == null) {
                adyacentes[i] = celda;
                break;
            }
        }
    }
}
