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
    private String nombre;
    private TipoDato tipo;
    private String padre;
    private int dimension;
    private int minimo;
    private int maximo;
    private String ambito;

    public Tipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    public Tipo(String nombre, TipoDato tipo, String padre, int dimension, int minimo, int maximo, String ambito) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.padre = padre;
        this.dimension = dimension;
        this.minimo = minimo;
        this.maximo = maximo;
        this.ambito = ambito;
    }

    public Tipo(String nombre, TipoDato tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    

    public TipoDato getTipo() {
        return tipo;
    }

    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String imprimirTipo() {
        return "Tipo{" + "nombre=" + nombre + ", tipo=" + tipo + ", padre=" + padre + ", dimension=" + dimension + ", minimo=" + minimo + ", maximo=" + maximo + ", ambito=" + ambito + '}';
    }
    
    
    
}
