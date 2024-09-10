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

/**
 *
 * @author rudyo
 */
public class AccesoVariable extends Instruccion{
    private String id;

    public AccesoVariable(String id, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(this.id);
        if (valor == null) {
            return new Errores("SEMANTICA", "La variable \" " + this.id+" \" no existe",
                    this.linea, this.columna);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
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
