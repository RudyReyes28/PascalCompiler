/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AsignacionVarRecord extends Instruccion {
    
    private String nombreS;
    private String campo;
    private Instruccion valor;

    public AsignacionVarRecord(String nombreS, String campo, Instruccion valor, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.nombreS = nombreS;
        this.campo = campo;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var struct = tabla.getVariable(nombreS);
        if (struct == null) {
            return new Errores("SEMANTICO", "El record \" " + this.nombreS+" \" no existe",
                    this.linea, this.columna);
        }
        var exp = this.valor.interpretar(arbol, tabla);
        
        if(exp instanceof Errores){
            return exp;
        }
        
        if(!struct.isMutable()){
            return new Errores("SEMANTICO", "El record \" "+nombreS+" \" es de tipo const, no se puede asignar el valor",
                    this.linea, this.columna);
        }
        
        var lista = struct.getValor();
        
        if(lista instanceof List){
            LinkedList<HashMap> acceso = (LinkedList<HashMap>) lista;
            
            for(int i =0; i< acceso.size(); i++){
                String nombreC =(String) acceso.get(i).get("id");
                if(nombreC.equalsIgnoreCase(campo)){
                    var tipoDato = acceso.get(i).get("tipo");
                    
                    if (tipoDato instanceof Tipo) {
                        
                        if (((Tipo) tipoDato).getTipo() != valor.tipo.getTipo()) {
                            return new Errores("SEMANTICO", "Los tipos de datos del record son erroneos",
                                    this.linea, this.columna);
                        }

                        acceso.get(i).put("valor", exp);

                    } else {
                        String nombreTipo = (String) tipoDato;
                        Tipo nuevoTipo = arbol.getTablaTipos().getTipo(nombreTipo);
                        
                        if (nuevoTipo == null) {
                            return new Errores("SEMANTICO", "El tipo de dato no existe", this.linea, this.columna);

                        }
                        
                        if (nuevoTipo.getTipo() != valor.tipo.getTipo()) {
                            return new Errores("SEMANTICO", "Los tipos de datos del record son erroneos",
                                    this.linea, this.columna);
                        }

                        acceso.get(i).put("valor", exp);
        

                    }

                }
            }
        }
             return null;
    }
    
}
