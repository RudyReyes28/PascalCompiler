/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.expresiones.logicas;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;

/**
 *
 * @author rudyo
 */
public class OperadorNot extends Instruccion{
    
    private Instruccion operando1;

    public OperadorNot(Instruccion operando1, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operando1 = operando1;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object op = null;
        
        op = this.operando1.interpretar(arbol, tabla);
        if (op instanceof Errores) {
            return op;
        }
        
        
        
        return operacionNot(op);
    }
    
     public Object operacionNot(Object op1){
        var tipo1 = this.operando1.tipo.getTipo();
        
        if(tipo1 == TipoDato.BOOLEANO){
            boolean valor1 = op1 instanceof Boolean ? (Boolean) op1 : Boolean.valueOf(op1.toString().toLowerCase());
            return !valor1;
        }else if(tipo1 == TipoDato.INTEGER){
            boolean valor1 = ((int) op1 != 0);
            return !valor1;
        }else if(tipo1 == TipoDato.CARACTER){
            boolean valor1 = ((int) op1.toString().charAt(0)) != 0;
            return !valor1;
        }
        
        else{
            return new Errores("SEMANTICO", "Tipo de dato no soportado para la operacion logica (NOT) : " + tipo1, this.linea, this.columna);
        }
        
        
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
