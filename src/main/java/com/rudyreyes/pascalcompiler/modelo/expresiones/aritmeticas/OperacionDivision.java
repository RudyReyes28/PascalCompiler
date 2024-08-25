/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.expresiones.aritmeticas;

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
public class OperacionDivision extends Instruccion{
    
    private Instruccion operando1;
    private Instruccion operando2;
    
     public OperacionDivision(Instruccion operando1, Instruccion operando2, int linea, int columna) {
        super(new Tipo(TipoDato.REAL), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null;
        
        opIzq = this.operando1.interpretar(arbol, tabla);
        if (opIzq instanceof Errores) {
            return opIzq;
        }
        opDer = this.operando2.interpretar(arbol, tabla);
        if (opDer instanceof Errores) {
            return opDer;
        }
        
        return dividir(opIzq, opDer);
    }
    
    public Object dividir(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        if (tipo2 == TipoDato.INTEGER) {
            if ((int) op2 == 0) {
                return new Errores("SEMANTICO", "Division entre cero: la operacion " + op1.toString() + " no se puede dividir con la operacion " + op2.toString(), this.linea, this.columna);
            }
        } else if (tipo2 == TipoDato.REAL) {
            if ((double) op2 == 0.0) {
                return new Errores("SEMANTICO", "Division entre cero: la operacion " + op1.toString() + " no se puede dividir con la operacion " + op2.toString(), this.linea, this.columna);
            }
        } 

        switch (tipo1) {
            case INTEGER -> {
                switch (tipo2) {
                    case INTEGER -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return ( (double)(int) op1)/ (int) op2;
                    }
                    case REAL -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (int) op1 / (double) op2;
                    }
                    
                    
                    
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea: la operacion "+op1.toString()+" no se puede dividir con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            case REAL -> {
                switch (tipo2) {
                    case INTEGER -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (double) op1 / (int) op2;
                    }
                    case REAL -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (double) op1 / (double) op2;
                    }
                    
                    
                    
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea: la operacion "+op1.toString()+" no se puede dividir con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            
            default -> {
                return new Errores("SEMANTICO", "Division erronea: la operacion "+op1.toString()+" no se puede dividir con la operacion "+op2.toString(), this.linea, this.columna);

            }
        }
    } 
    
    
}
