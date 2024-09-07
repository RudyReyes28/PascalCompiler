/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class TablaSimbolos {
    private TablaSimbolos tablaAnterior;
    private HashMap<String, Object> tablaActual;
    private String nombre;

    public TablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }   

    public TablaSimbolos(TablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public TablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public void setTablaAnterior(TablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public boolean setVariable(Simbolo simbolo) {
        Simbolo busqueda
                = (Simbolo) this.tablaActual.get(
                        simbolo.getId().
                                toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(),
                    simbolo);
            return true;
        }
        return false;
    }

    public Simbolo getVariable(String id) {
        for (TablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            Simbolo busqueda = (Simbolo) i.tablaActual.
                    get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }
    
    public List<Simbolo> getTodosLosSimbolos() {
        List<Simbolo> simbolos = new ArrayList<>();
        for (TablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            for (Object obj : i.tablaActual.values()) {
                if (obj instanceof Simbolo) {
                    simbolos.add((Simbolo) obj);
                } else {
                    // Maneja el caso en que obj no es una instancia de Simbolo si es necesario
                    System.err.println("Warning: Encontrado un objeto no Simbolo en la tabla de símbolos.");
                }
            }
        }
        return simbolos;
    }

    public String mostrarTodosLosSimbolos() {
        String detalleS = "";
        List<Simbolo> simbolos = getTodosLosSimbolos();
        for (Simbolo simbolo : simbolos) {
           detalleS +=simbolo.imprimirSimbolo() +"\n";
        }
        return detalleS;
    }
    
    public List<Simbolo> getSimbolosTablaActual() {
        List<Simbolo> simbolos = new ArrayList<>();
        for (Object obj : this.tablaActual.values()) {
            if (obj instanceof Simbolo) {
                simbolos.add((Simbolo) obj);
            } else {
                // Maneja el caso en que obj no es una instancia de Simbolo si es necesario
                System.out.println("Warning: Encontrado un objeto no Simbolo en la tabla de símbolos");
            }
        }
        return simbolos;
    }

    public void mostrarSimbolosTablaActual() {
        List<Simbolo> simbolos = getSimbolosTablaActual();
        for (Simbolo simbolo : simbolos) {
            System.out.println(simbolo.imprimirSimbolo());
        }
    }
    
}
