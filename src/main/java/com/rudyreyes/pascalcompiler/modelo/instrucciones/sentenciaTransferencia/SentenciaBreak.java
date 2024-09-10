/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciaTransferencia;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;

/**
 *
 * @author rudyo
 */
public class SentenciaBreak extends Instruccion {
    public SentenciaBreak(int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        return null;
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
