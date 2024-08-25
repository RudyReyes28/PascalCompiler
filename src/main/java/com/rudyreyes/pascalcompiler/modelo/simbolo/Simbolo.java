/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

/**
 *
 * @author rudyo
 */
public class Simbolo {
    private boolean mutable;
    private Tipo tipo;
    private String id;
    private int linea;
    private int columna;
    private Object valor;

    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    public Simbolo(boolean mutable, Tipo tipo, String id, Object valor, int linea, int columna) {
        this.mutable = mutable;
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    //true = var
    //false = const 

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    
    public String imprimirSimbolo(){
        return ("Tipo"+this.tipo.getTipo()+" ID: " +id+" valor: "+ valor+ " Linea "+linea+" columna "+columna);
    }
}
