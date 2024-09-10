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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AccesoVarRecord extends Instruccion{
    
     private String nombreStruct;
    private String campo;

    public AccesoVarRecord(String nombreStruct, String campo, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.nombreStruct = nombreStruct;
        this.campo = campo;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var struct = tabla.getVariable(nombreStruct);
        if (struct == null) {
            return new Errores("SEMANTICO", "El record \" " + this.nombreStruct+" \" no existe",
                    this.linea, this.columna);
        }
        
        var valor = struct.getValor();
        
        if(valor instanceof List){
            LinkedList<HashMap> acceso = (LinkedList<HashMap>) valor;
            
            for(int i =0; i< acceso.size(); i++){
                String nombreC =(String) acceso.get(i).get("id");
                if(nombreC.equalsIgnoreCase(campo)){
                    
                    var valorC = acceso.get(i).get("valor");
                    var tipoDato = acceso.get(i).get("tipo");
                    
                    if (tipoDato instanceof Tipo) {
                        this.tipo = (Tipo)tipoDato;
                       
                    } else {
                        String nombreTipo = (String) tipoDato;
                        Tipo nuevoTipo = arbol.getTablaTipos().getTipo(nombreTipo);
                        if (nuevoTipo == null) {
                            return new Errores("SEMANTICO", "El tipo de dato: "+nombreTipo+" no existe " , this.linea, this.columna);

                        }
                        this.tipo = (Tipo)nuevoTipo;
                    }
                    
                    return valorC;
                }
            }
        }
             return new Errores("SEMANTICO", "La variable \" " + this.nombreStruct+" \" no es un record",
                    this.linea, this.columna);
        
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
