/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones.FuncionReturn;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;

/**
 *
 * @author rudyo
 */
public class AsignacionVariable extends Instruccion{
    private String id;
    private Instruccion exp;
    
    
    public AsignacionVariable(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var funcion = arbol.getFuncion(id);
        if(funcion != null){
            FuncionReturn miFun = new FuncionReturn(exp, linea, columna);
             var resR = miFun.interpretar(arbol, tabla);
             if(resR instanceof Errores){
                 return resR;
                }
             return resR;
        }
        
        var variable = tabla.getVariable(id);
        
        //VARIBLE EXISTE
        if (variable == null) {
            return new Errores("SEMANTICO", "La variable \" " + variable+" \" no existe",
                    this.linea, this.columna);
        }
        
        //OBTENER VALOR
        var nuevoValor = this.exp.interpretar(arbol, tabla);
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }
        
        //VALIDAR TIPOS
        if(variable.getTipo().getTipo() == TipoDato.BOOLEANO &&  this.exp.tipo.getTipo() == TipoDato.INTEGER){
            
        }else if (variable.getTipo().getTipo() != this.exp.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipo de dato erroneo, no se puede asignar el valor de tipo "+this.exp.tipo.getTipo()+ " a la variable de tipo "+ variable.getTipo().getTipo(),
                    this.linea, this.columna);
        }
        
        //VALIDAR MUTABILIDAD
        
        if(!variable.isMutable()){
            return new Errores("SEMANTICO", "La variable \" "+id+" \" es de tipo const, no se puede asignar el valor",
                    this.linea, this.columna);
        }
        
        if(variable.getTipo().getNombreEstructura().equalsIgnoreCase("array")){
            return new Errores("SEMANTICO", "La variable \" "+id+" \" es de tipo array, no se puede asignar el valor",
                    this.linea, this.columna);
        }
        
        variable.setValor(nuevoValor);
        //variable.setLinea(this.linea);
        //variable.setColumna(this.columna);
        
        return null;
    }
}
