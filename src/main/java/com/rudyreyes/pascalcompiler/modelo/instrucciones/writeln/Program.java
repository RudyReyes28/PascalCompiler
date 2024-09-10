/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.writeln;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;

/**
 *
 * @author rudyo
 */
public class Program extends Instruccion{

    private String id;
    
    public Program(String id,int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        
        this.id = id;
    
    }

    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        return null;
    }
    
    
    
    
}
