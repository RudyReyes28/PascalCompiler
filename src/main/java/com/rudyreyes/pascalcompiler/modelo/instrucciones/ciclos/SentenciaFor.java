/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.ciclos;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciaTransferencia.SentenciaBreak;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciaTransferencia.SentenciaContinue;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class SentenciaFor extends Instruccion{
    
    private Instruccion asignacion;
    private Instruccion rango;
    private Instruccion acceso;
    private Instruccion incremento;
    private LinkedList<Instruccion> instrucciones;

    public SentenciaFor(Instruccion asignacion, Instruccion rango, Instruccion acceso, LinkedList<Instruccion> instrucciones, Instruccion incremento, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.asignacion = asignacion;
        this.rango = rango;
        this.acceso = acceso;
        this.instrucciones = instrucciones;
        this.incremento = incremento;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        //creamos un nuevo entorno
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre(tabla.getNombre()+"-for");
        arbol.agregarTablaEntorno(newTabla);

        // asignacion/declaracion
        var asignacion = this.asignacion.interpretar(arbol, newTabla);
        if (asignacion instanceof Errores) {
            return asignacion;
        }

        //actualizar la variable
        var ran = this.rango.interpretar(arbol, newTabla);
        if (ran instanceof Errores) {
            return ran;
        }

        if (this.rango.tipo.getTipo() != TipoDato.INTEGER) {
            return new Errores("SEMANTICO", "El rango debe de ser INTEGER",
                    this.linea, this.columna);
        }
        
        var acces = this.acceso.interpretar(arbol, newTabla);
        if (this.acceso.tipo.getTipo() != TipoDato.INTEGER) {
            return new Errores("SEMANTICO", "El rango debe de ser INTEGER",
                    this.linea, this.columna);
        }
        
        while ((int) this.acceso.interpretar(arbol, newTabla) <= (int) ran) {
            //nuevo entorno
            var newTabla2 = new TablaSimbolos(newTabla);
            newTabla2.setNombre(tabla.getNombre()+"-for");
            arbol.agregarTablaEntorno(newTabla2);

            //ejecutar instrucciones
            for (var i : this.instrucciones) {
                if (i instanceof SentenciaBreak) {
                    return null;
                }
                if (i instanceof SentenciaContinue) {
                    break;
                }
                
                /*if (i instanceof FuncionReturn) {
                    var res = i.interpretar(arbol, newTabla2);
                    if (res instanceof Errores) {
                        return res;
                    }
                    return res;
                }*/
                
                if(i == null){
                    continue;
                }
                
                var resIns = i.interpretar(arbol, newTabla2);
                if (resIns instanceof SentenciaBreak) {
                    return null;
                }
                
                if (resIns instanceof SentenciaContinue) {
                    break;
                }
                
                /*if(resIns instanceof FuncionReturn){
                    return resIns;
                }*/
                
                if (resIns instanceof Errores) {
                    return resIns;
                }
            }

            var act = this.incremento.interpretar(arbol, newTabla);
            if (act instanceof Errores) {
                return act;
            }
        }
        return null;
    }
    
}
