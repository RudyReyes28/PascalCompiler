/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciasControl;

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
public class SentenciaIf extends Instruccion{
    
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public SentenciaIf(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }
    
    /*public SentenciaIf(Instruccion condicion, Instruccion instruccion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = new LinkedList<>();
        this.instrucciones.add(instruccion);
    }*/

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // ver que cond sea booleano
        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida",
                    this.linea, this.columna);
        }
        
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre(tabla.getNombre()+"-if");
        arbol.agregarTablaEntorno(newTabla);
        if ((boolean) cond) {
            for (var i : this.instrucciones) {
                /*if (i instanceof SentenciaBreak) {
                    return i;
                }
                
                if (i instanceof SentenciaContinue) {
                    return i;
                }

                if (i instanceof FuncionReturn) {
                    var res = i.interpretar(arbol, newTabla);
                    if (res instanceof Errores) {
                        return res;
                    }
                    return res;
                }*/
                
                if(i == null){
                    continue;
                }
                
                var resultado = i.interpretar(arbol, newTabla);
                /*if (resultado instanceof SentenciaBreak) {
                    return resultado;
                }
                
                if(resultado instanceof FuncionReturn){
                    return resultado;
                }
                
                if (resultado instanceof SentenciaContinue) {
                    return resultado;
                }*/
                
                if (resultado instanceof Errores) {
                    return resultado;
                }
                /*
                    Manejo de errores
                */
            }
        }
        
        return null;
    }
    
}
