/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

import java.util.List;

/**
 *
 * @author rudyo
 */
public class EntornoSimbolos {
     private String nombreEntorno;
    private List<Simbolo> simbolos;

    public EntornoSimbolos(String nombreEntorno, List<Simbolo> simbolos) {
        this.nombreEntorno = nombreEntorno;
        this.simbolos = simbolos;
    }

    public String getNombreEntorno() {
        return nombreEntorno;
    }

    public List<Simbolo> getSimbolos() {
        return simbolos;
    }
    
}
