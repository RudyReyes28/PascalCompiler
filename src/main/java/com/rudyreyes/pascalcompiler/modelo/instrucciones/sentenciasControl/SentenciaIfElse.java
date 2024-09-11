/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciasControl;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones.FuncionReturn;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciaTransferencia.SentenciaBreak;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.sentenciaTransferencia.SentenciaContinue;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class SentenciaIfElse extends Instruccion {
    
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;
    private LinkedList<Instruccion> instruccionesElse;

    public SentenciaIfElse(Instruccion condicion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> instruccionesElse, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // ver que cond sea booleano
        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida",
                    this.linea, this.columna);
        }
        
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre(tabla.getNombre()+"-if:else");
        arbol.agregarTablaEntorno(newTabla);
        if ((boolean) cond) {
            for (var i : this.instrucciones) {
                if (i instanceof SentenciaBreak) {
                    return i;
                }
                
                if (i instanceof SentenciaContinue) {
                    return i;
                }
                if (i instanceof FuncionReturn) {
                    var res = i.interpretar(arbol, newTabla);
                    if (res instanceof Errores) {
                        return res;
                    }
                    return res;
                }
                
                if(i == null){
                    continue;
                }
                
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof SentenciaBreak) {
                    return resultado;
                }
                
                if (resultado instanceof SentenciaContinue) {
                    return resultado;
                }
                
                if(resultado instanceof FuncionReturn){
                    return resultado;
                }
                
                if (resultado instanceof Errores) {
                    return resultado;
                }
            }
        }else{
            for (var i : this.instruccionesElse) {
                if (i instanceof SentenciaBreak) {
                    return i;
                }
                
                if (i instanceof SentenciaContinue) {
                    return i;
                }
                
                if (i instanceof FuncionReturn) {
                    var res = i.interpretar(arbol, newTabla);
                    if (res instanceof Errores) {
                        return res;
                    }
                    return res;
                }
                
                if(i == null){
                    continue;
                }
                
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof SentenciaBreak) {
                    return resultado;
                }
                
                if (resultado instanceof SentenciaContinue) {
                    return resultado;
                }
                
                if(resultado instanceof FuncionReturn){
                    return resultado;
                }
                
                if (resultado instanceof Errores) {
                    return resultado;
                }
            }
        }
        
        return null;
    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String stIf = "n" + arbol.getContador();
        String ifN = "n" + arbol.getContador();
        String exp = "n" + arbol.getContador();
        String par2 = "n" + arbol.getContador();
        String llave1 = "n" + arbol.getContador();
        String inst = "n" + arbol.getContador();
        String llave2 = "n" + arbol.getContador();
        String elseN = "n" + arbol.getContador();
        String llave1E = "n" + arbol.getContador();
        String instE = "n" + arbol.getContador();
        String llave2E = "n" + arbol.getContador();
        
        String resultado = anterior+" ->"+stIf+";\n"; 
        resultado += stIf + "[label=\"SentenciaIf-else\"];\n";
        resultado += ifN + "[label=\"if\"];\n";
        resultado += exp + "[label=\"EXP\"];\n";
        resultado += par2 + "[label=\"then\"];\n";
        resultado += llave1 + "[label=\"BEGIN \"];\n";
        resultado += inst + "[label=\"INSTRUCCIONES\"];\n";
        resultado += llave2 + "[label=\"END\"];\n";
        resultado += elseN + "[label=\"else\"];\n";
        resultado += llave1E + "[label=\"BEGIN\"];\n";
        resultado += instE + "[label=\"INSTRUCCIONES\"];\n";
        resultado += llave2E + "[label=\"END\"];\n";
        
        resultado += stIf + " ->" + ifN + ";\n";
        resultado += stIf + " ->" + exp + ";\n";
        resultado += stIf + " ->" + par2 + ";\n";
        resultado += stIf + " ->" + llave1 + ";\n";
        resultado += stIf + " ->" + inst + ";\n";
        resultado += stIf + " ->" + llave2 + ";\n";
        resultado += stIf + " ->" + elseN + ";\n";
        resultado += stIf + " ->" + llave1E + ";\n";
        resultado += stIf + " ->" + instE + ";\n";
        resultado += stIf + " ->" + llave2E + ";\n";
        resultado += this.condicion.generarast(arbol, exp);
        
        for (var i : this.instrucciones) {
            if(i ==null ){
                continue;
            }
            
            String nodoAux = "n" + arbol.getContador();
                resultado += nodoAux + "[label=\"INSTRUCCION\"];\n";
                resultado += inst + "-> " + nodoAux + ";\n";
                resultado += i.generarast(arbol, nodoAux);
        }
        
        for (var i : this.instruccionesElse) {
            if(i ==null ){
                continue;
            }
            
            String nodoAux = "n" + arbol.getContador();
                resultado += nodoAux + "[label=\"INSTRUCCION\"];\n";
                resultado += instE + "-> " + nodoAux + ";\n";
                resultado += i.generarast(arbol, nodoAux);
        }
        
        return resultado;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
