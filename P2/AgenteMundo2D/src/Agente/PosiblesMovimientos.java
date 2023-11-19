/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Agente;

import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author diego
 */
public enum PosiblesMovimientos {
    ARRIBA(-1, 0),
    ABAJO(1, 0),
    IZQUIERDA(0, -1),
    DERECHA(0, 1);

    private final int x;
    private final int y;

    PosiblesMovimientos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
    
    public SimpleEntry<Integer,Integer> sumar(SimpleEntry<Integer,Integer> pos) {
        return new SimpleEntry<>(this.x + pos.getKey(), this.y + pos.getValue());
    }
}
