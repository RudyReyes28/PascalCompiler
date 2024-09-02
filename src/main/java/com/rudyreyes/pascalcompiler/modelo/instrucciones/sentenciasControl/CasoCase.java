/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciasControl;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class CasoCase {
    LinkedList<Instruccion> casos;
    LinkedList<Instruccion> instrucciones;

    public CasoCase(LinkedList<Instruccion> casos, LinkedList<Instruccion> instrucciones) {
        this.casos = casos;
        this.instrucciones = instrucciones;
    }

    public LinkedList<Instruccion> getCasos() {
        return casos;
    }

    public void setCasos(LinkedList<Instruccion> casos) {
        this.casos = casos;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
}
