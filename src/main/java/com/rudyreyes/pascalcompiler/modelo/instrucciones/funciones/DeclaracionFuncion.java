/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class DeclaracionFuncion extends Instruccion {
    public String nombreFuncion;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;
    public String tipoDato;

    public DeclaracionFuncion(String id, LinkedList<HashMap> parametros,String tipoDato, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.nombreFuncion = id;
        this.parametros = parametros;
        this.tipoDato = tipoDato;
        this.instrucciones = instrucciones;
        
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Tipo tipoDatoVar = arbol.getTablaTipos().getTipo(this.tipoDato);
        
        if(tipoDatoVar== null){
             return new Errores("SEMANTICO", "El tipo de dato no existe", this.linea, this.columna);

        }
        this.tipo = tipoDatoVar;
        for (var i : this.instrucciones) {
            if(i ==null ){
                continue;
            }
            /*if(i instanceof FuncionReturn){
                
                var res = i.interpretar(arbol, tabla);
                if (res instanceof Errores) {
                    return res;
                }
                if (tipo.getTipo() == i.tipo.getTipo()) {
                    return res;
                } else {
                    return new Errores("SEMANTICO", "El tipo de retorno no coincide con el tipo del metodo", linea, columna);
                }


                
            }*/
            var resultado = i.interpretar(arbol, tabla);
            if(resultado instanceof Errores){
                return resultado;
            }
            
            /*if(resultado instanceof FuncionReturn){
                    return resultado;
            }*/
            
            // return;
        }
        return null;
    }
    
}
