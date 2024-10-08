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
public class OperacionModulo extends Instruccion{
    
    private Instruccion operando1;
    private Instruccion operando2;
    
    public OperacionModulo(Instruccion operando1, Instruccion operando2, int linea, int columna) {
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
        
        return modulo(opIzq, opDer);
    }
    
    public Object modulo(Object op1, Object op2){
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
                        this.tipo.setTipo(TipoDato.INTEGER);
                        return ( (int) op1) % (int) op2;
                    }
                   
                    default -> {
                        return new Errores("SEMANTICO", "Modulo erroneo: la operacion "+op1.toString()+" no se puede dividir con la operacion "+op2.toString(), this.linea, this.columna);
                    }
                }
            }
            
            
            default -> {
                return new Errores("SEMANTICO", "Modulo erroneo: la operacion "+op1.toString()+" no se puede dividir con la operacion "+op2.toString(), this.linea, this.columna);

            }
        }
    } 
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String nodoExp1 = "n" + arbol.getContador();
        String nodoOp = "n" + arbol.getContador();
        String nodoExp2 = "n" + arbol.getContador();

        String resultado = anterior + " -> " + nodoExp1 + ";\n";
        resultado += anterior + " ->" + nodoOp + ";\n";
        resultado += anterior + " ->" + nodoExp2 + ";\n";

        resultado += nodoExp1 + "[label=\"EXP\"];\n";
        resultado += nodoOp + "[label=\"MOD\"];\n";
        resultado += nodoExp2 + "[label=\"EXP\"];\n";
        resultado += this.operando1.generarast(arbol, nodoExp1);
        resultado += this.operando2.generarast(arbol, nodoExp2);
        return resultado;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return "";
    }
    
}
