/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.estructura;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class EstructuraVar extends Instruccion{
    LinkedList<Instruccion> instrucciones;

    public EstructuraVar(LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
         for (var i : this.instrucciones) {
            if(i ==null ){
                continue;
            }
            
            
            var resultado = i.interpretar(arbol, tabla);
            if(resultado instanceof Errores){
                arbol.agregarError(resultado);
            }
            // return;
        }
        return null;

    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String strConst = "n" + arbol.getContador();
        String instrucci = "n" + arbol.getContador();
        
        String cadena= anterior+" ->"+strConst+";\n"; 
        
        cadena += strConst + "[label=\"DECLARACION VAR\"];\n";
        cadena += instrucci + "[label=\"DECLARACIONES VARIABLES\"];\n";
        cadena += strConst + " ->" + instrucci + ";\n";
        for (var i : this.instrucciones) {
            if(i ==null ){
                continue;
            }
            
            String nodoAux = "n" + arbol.getContador();
                cadena += nodoAux + "[label=\"INSTRUCCION\"];\n";
                cadena += instrucci + "-> " + nodoAux + ";\n";
                cadena += i.generarast(arbol, nodoAux);
        }
        
        return cadena;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
