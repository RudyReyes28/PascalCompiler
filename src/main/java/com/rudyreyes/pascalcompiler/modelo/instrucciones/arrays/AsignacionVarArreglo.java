/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.arrays;

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
public class AsignacionVarArreglo extends Instruccion {
    private String id;
    private Instruccion posicion;
    private Instruccion asignacion;

    public AsignacionVarArreglo(String id, Instruccion posicion, Instruccion asignacion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.posicion = posicion;
        this.asignacion = asignacion;
    }
    
     @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        
        if (variable == null) {
            return new Errores("SEMANTICO", "El vector \" " + this.id+" \" no existe",
                    this.linea, this.columna);
        }
        
        var posicionV = this.posicion.interpretar(arbol, tabla);
        
        if(posicionV instanceof Errores){
            return posicionV;
        }
        
        if(this.posicion.tipo.getTipo() != TipoDato.INTEGER){
            return new  Errores("SEMANTICO", "La posicion vector \" " + this.id+" \" no es del tipo de dato entero",
                    this.linea, this.columna);
        }
        
        var nuevoValor = this.asignacion.interpretar(arbol, tabla);
        
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }
        
        if (variable.getTipo().getTipo() != this.asignacion.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipo de dato erroneo, no se puede asignar el valor de tipo "+this.asignacion.tipo.getTipo()+ " a la variable de tipo "+ variable.getTipo().getTipo(),
                    this.linea, this.columna);
        }
        
        //VALIDAR MUTABILIDAD
        
        if(!variable.isMutable()){
            return new Errores("SEMANTICO", "La variable \" "+id+" \" es de tipo const, no se puede asignar el valor",
                    this.linea, this.columna);
        }
        
        var arreglo = variable.getValor();
        
        if(arreglo instanceof Object[]){
            Object [] resultado = (Object []) arreglo;
            int posA = (int)posicionV - variable.getTipo().getMinimo();
            if(posA>=resultado.length || posA<0){
                return new  Errores("SEMANTICO", "La posicion  \" " + posicionV+" \" es mayor que la longitud del vector",
                    this.linea, this.columna);
            }
            
            resultado[posA] = nuevoValor;
            
            variable.setValor(resultado);
            //variable.setLinea(this.linea);
            //variable.setColumna(this.columna);
        
        }else{
            return new Errores("SEMANTICO", "La variable \" " + this.id+" \" no es un array ",
                    this.linea, this.columna);
        }
        
        
        return null;
    }
    
}
