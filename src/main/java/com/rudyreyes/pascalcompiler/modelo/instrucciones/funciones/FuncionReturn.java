/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.expresiones.nativo.Nativo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;

/**
 *
 * @author rudyo
 */
public class FuncionReturn extends Instruccion{
    public Instruccion instruccion;

    public FuncionReturn(Instruccion instruccion, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
        this.instruccion = instruccion;
    }

    public FuncionReturn(int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instruccion = null;
    }
    
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if(this.instruccion == null){
             return new FuncionReturn( new Nativo(null, new Tipo(TipoDato.VOID), linea, columna), linea, columna);

        }
        var resultado = this.instruccion.interpretar(arbol, tabla);
        
        if(resultado instanceof Errores){
            return resultado;
        }
        this.tipo = this.instruccion.tipo;
        
        return new FuncionReturn( new Nativo(resultado, this.tipo, linea, columna), linea, columna);
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
