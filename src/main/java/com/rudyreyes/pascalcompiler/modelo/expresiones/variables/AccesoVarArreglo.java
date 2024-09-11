/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.expresiones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AccesoVarArreglo extends Instruccion{
    
     private String identificador;
    private Instruccion posicion;

    public AccesoVarArreglo(String identificador, Instruccion posicion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.identificador = identificador;
        this.posicion = posicion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var vector = tabla.getVariable(identificador);
        
        if (vector == null) {
            return new Errores("SEMANTICO", "El array \" " + this.identificador+" \" no existe",
                    this.linea, this.columna);
        }
        var p = this.posicion.interpretar(arbol, tabla);
        
        if(p instanceof Errores){
            return p;
        }
        
        if(this.posicion.tipo.getTipo() != TipoDato.INTEGER){
            return new  Errores("SEMANTICO", "La posicion vector \" " + this.identificador+" \" no es del tipo de dato INTEGER",
                    this.linea, this.columna);
        }
        
        var valor = vector.getValor();
        
        if(valor instanceof Object[]){
            Object [] resultado = (Object []) valor;
            int posA = (int)p - vector.getTipo().getMinimo();
            if(posA>=resultado.length || posA<0){
                return new  Errores("SEMANTICO", "La posicion  \" " + p+" \" es mayor que la longitud del vector",
                    this.linea, this.columna);
            }
            this.tipo.setTipo(vector.getTipo().getTipo());
            if(resultado[posA] == null){
                return new  Errores("SEMANTICO", "La posicion  \" " + p+" \" es mayor que la longitud del vector",
                    this.linea, this.columna);
            }
            return resultado[posA];
        }
        
        
        return new  Errores("SEMANTICO", "La variable \" " + this.identificador+" \" no es un vector o una lista",
                    this.linea, this.columna);
    }
    
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String stAsig = "n" + arbol.getContador();
        String idV = "n" + arbol.getContador();
        String par1 = "n" + arbol.getContador();
        String exp = "n" + arbol.getContador();
        String par2 = "n" + arbol.getContador();
        
        String resultado = anterior+" ->"+stAsig+";\n"; 
        
        resultado += stAsig + "[label=\"ACCESO ARRAY\"];\n";
        resultado += idV + "[label=\""+this.identificador+"\"];\n";
        resultado += par1 + "[label=\"[\"];\n";
        resultado += exp + "[label=\"POS\"];\n";
        resultado += par2 + "[label=\"]\"];\n";
        
        resultado += stAsig + " ->" + idV + ";\n";
        resultado += stAsig + " ->" + par1 + ";\n";
        resultado += stAsig + " ->" + exp + ";\n";
        resultado += stAsig + " ->" + par2 + ";\n";
        
        return resultado += this.posicion.generarast(arbol, exp);
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
