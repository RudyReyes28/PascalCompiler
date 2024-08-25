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
public class OperacionResta extends Instruccion{
    
    private Instruccion operando1;
    private Instruccion operando2;

    public OperacionResta(Instruccion operando1, Instruccion operando2, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
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
        
        return suma(opIzq, opDer);

    }
    
    public Object suma(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case INTEGER -> {
                switch (tipo2) {
                    case INTEGER -> {
                        this.tipo.setTipo(TipoDato.INTEGER);
                        return (int) op1 - (int) op2;
                    }
                    case REAL -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (int) op1 - (double) op2;
                    }
                    
                    case BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.INTEGER);
                        boolean valor2 = op2 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());
                        return (int) op1 - (valor2 ? 1 : 0);
                    }
                   
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea: la operacion "+op1.toString()+" no se puede sumar con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            case REAL -> {
                switch (tipo2) {
                    case INTEGER -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (double) op1 - (int) op2;
                    }
                    case REAL -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        return (double) op1 - (double) op2;
                    }
                    case BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        boolean valor2 = op2 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());
                        return (double) op1 - (valor2 ? 1 : 0);
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea: la operacion "+op1.toString()+" no se puede sumar con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            
            case BOOLEANO -> {
                switch (tipo2) {
                    case INTEGER -> {
                        this.tipo.setTipo(TipoDato.INTEGER);
                        boolean valor1 = op1 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());

                        return (valor1 ? 1 : 0) - (int) op2;
                    }
                    case REAL -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        boolean valor1 = op1 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());

                        return (valor1 ? 1 : 0) - (double) op2;
                    }
                    
                    case BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.REAL);
                        boolean valor1 = op1 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());
                        boolean valor2 = op2 instanceof Boolean ? (Boolean) op2 : Boolean.valueOf(op2.toString().toLowerCase());
                        return (valor1 ? 1 : 0) - (valor2 ? 1 : 0);
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea: la operacion "+op1.toString()+" no se puede restar con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            
            
            default -> {
                return new Errores("SEMANTICO", "Resta erronea: la operacion "+op1.toString()+" no se puede sumar con la operacion "+op2.toString(), this.linea, this.columna);

            }
        }
    }
    
}
