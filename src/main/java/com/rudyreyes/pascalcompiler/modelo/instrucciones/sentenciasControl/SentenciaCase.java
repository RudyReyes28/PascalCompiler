/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciasControl;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones.FuncionReturn;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class SentenciaCase extends Instruccion{
    
    private Instruccion condicion;
    private boolean ejecutarDefault = true;
    private LinkedList<CasoCase> casos;
    private LinkedList<Instruccion> casoDefault;

    public SentenciaCase(Instruccion condicion, LinkedList<CasoCase> casos, LinkedList<Instruccion> casoDefault, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.casos = casos;
        this.casoDefault = casoDefault;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        ejecutarDefault = true;
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        
        
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre(tabla.getNombre()+"-match");
        arbol.agregarTablaEntorno(newTabla);
        if (this.casos != null) {
            for (CasoCase c : this.casos) {
                
                for (Instruccion ins : c.getCasos()) {
                    var condCaso = ins.interpretar(arbol, tabla);
                    if (this.condicion.tipo.getTipo() == ins.tipo.getTipo()) {
                        if (cond.equals(condCaso)) {
                            for (var i : c.getInstrucciones()) {
                                if (i instanceof FuncionReturn) {
                                var res = i.interpretar(arbol, newTabla);
                                if (res instanceof Errores) {
                                    return res;
                                }
                                return res;
                            }
                                var resultado = i.interpretar(arbol, newTabla);

                                /*if (resultado instanceof FuncionReturn) {
                                return resultado;
                            }*/
                                if (resultado instanceof Errores) {
                                    return resultado;
                                }
                            }
                            this.ejecutarDefault = false;
                            break;
                        }
                    }
                }

            }
        } else {
            if (casoDefault != null) {
                for (var i : casoDefault) {
                    if (i instanceof FuncionReturn) {
                        var res = i.interpretar(arbol, newTabla);
                        if (res instanceof Errores) {
                            return res;
                        }
                        return res;
                    }
                    var resultado = i.interpretar(arbol, newTabla);
                    /*if (resultado instanceof FuncionReturn) {
                        return resultado;
                    }*/
                    if (resultado instanceof Errores) {
                            return resultado;
                    }
                }
                this.ejecutarDefault = false;
            }
        }
        
        if (ejecutarDefault) {
            if (casoDefault != null) {
                for (var i : casoDefault) {
                    if (i instanceof FuncionReturn) {
                        var res = i.interpretar(arbol, newTabla);
                        if (res instanceof Errores) {
                            return res;
                        }
                        return res;
                    }
                    var resultado = i.interpretar(arbol, newTabla);
                    /*if (resultado instanceof FuncionReturn) {
                        return resultado;
                    }*/
                    if (resultado instanceof Errores) {
                            return resultado;
                    }
                }
            }
        }

        
        
        
        return null;
    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return null;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
