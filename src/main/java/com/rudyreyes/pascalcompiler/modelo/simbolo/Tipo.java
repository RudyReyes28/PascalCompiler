/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

/**
 *
 * @author rudyo
 */
public class Tipo {
    private TipoDato tipo;

    public Tipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    public TipoDato getTipo() {
        return tipo;
    }

    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }
    
}
