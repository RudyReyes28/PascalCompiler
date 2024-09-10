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
public class OperacionNegacionU extends Instruccion{
    
    private Instruccion operandoUnico;

    public OperacionNegacionU(Instruccion operandoUnico, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
        this.operandoUnico = operandoUnico;
    
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object unico = null;
        unico = this.operandoUnico.interpretar(arbol, tabla);
        if (unico instanceof Errores) {
              return unico;
        }
        
        return negacion(unico);
    }
    
    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case INTEGER -> {
                this.tipo.setTipo(TipoDato.INTEGER);
                return (int) op1 * -1;
            }
            case REAL -> {
                this.tipo.setTipo(TipoDato.REAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea: la operacion "+op1.toString()+" no se puede negar", this.linea, this.columna);
            }
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
