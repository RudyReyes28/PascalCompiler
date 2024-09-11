/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.writeln;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class Writeln extends Instruccion {
    
    private LinkedList<Instruccion> instrucciones;

    public Writeln(LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        String concatenacion = "";
        for (Instruccion expresion : instrucciones) {
            var resultado = expresion.interpretar(arbol, tabla);
            if (resultado instanceof Errores) {
                return resultado;
            }
            concatenacion += resultado.toString()+" ";
        }
        
        
        
        
        arbol.writeln(concatenacion);
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String nodoPP = "n" + arbol.getContador();
        String nodoP = "n" + arbol.getContador();
        String nodoP1 = "n" + arbol.getContador();
        String nodoExp = "n" + arbol.getContador();
        String nodoP2 = "n" + arbol.getContador();
        String nodoPC = "n" + arbol.getContador();

        String resultado = nodoPP + "[label=\"WRITELN\"];\n";
        resultado += anterior + " -> " + nodoPP + ";\n";

        resultado += nodoP + "[label=\"WRITELN\"];\n";
        resultado += nodoP1 + "[label=\"(\"];\n";
        resultado += nodoExp + "[label=\"EXPRESION\"];\n";
        resultado += nodoP2 + "[label=\")\"];\n";
        resultado += nodoPC + "[label=\";\"];\n";

        resultado += nodoPP + " -> " + nodoP + ";\n";
        resultado += nodoPP + " -> " + nodoP1 + ";\n";
        resultado += nodoPP + " -> " + nodoExp + ";\n";
        resultado += nodoPP + " -> " + nodoP2 + ";\n";
        resultado += nodoPP + " -> " + nodoPC + ";\n";

        //resultado += this.expresion.generarast(arbol, nodoExp);

        return resultado;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
    
}
