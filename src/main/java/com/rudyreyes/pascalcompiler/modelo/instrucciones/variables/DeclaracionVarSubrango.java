/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.expresiones.nativo.Nativo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Simbolo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class DeclaracionVarSubrango extends Instruccion{
    private LinkedList<String> idRango;
    private Instruccion rangoInicial;
    private Instruccion rangoFinal;

    public DeclaracionVarSubrango(LinkedList<String> idRango, Instruccion rangoInicial, Instruccion rangoFinal, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
        this.idRango = idRango;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var rangoI = rangoInicial.interpretar(arbol, tabla);
        
        if(rangoI instanceof Errores){
            return rangoI;
        }
        
        
        var rangoF = rangoFinal.interpretar(arbol, tabla);
        
        if(rangoF instanceof Errores){
            return rangoF;
        }
        
        if(rangoInicial.tipo.getTipo() == TipoDato.BOOLEANO || rangoInicial.tipo.getTipo() == TipoDato.VOID){
            return new Errores("SEMANTICO", "Error en la declaracion de tipo, no se permiten limites de tipo "+rangoInicial.tipo.getTipo(), this.linea, this.columna);

        }
        
        if(rangoInicial.tipo.getTipo() == rangoFinal.tipo.getTipo()){
            for (String identificador : this.idRango) {

                Tipo t = new Tipo(identificador, this.rangoInicial.tipo.getTipo());
                t.setMinimo((int) rangoI);
                t.setMaximo((int) rangoF);
                t.setDimension((int) rangoF - (int) rangoI+1);
                t.setNombreEstructura("subrange");
                
                Simbolo s = new Simbolo(true, t, identificador, valorInterpretado(rangoInicial.tipo.getTipo()).interpretar(arbol, tabla), this.linea, this.columna);

                boolean creacion = tabla.setVariable(s);
                if (!creacion) {
                    return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
                }
            }

        }else{
              return new Errores("SEMANTICO", "Error en la declaracion de tipo no tiene definido los limites del mismo tipo", this.linea, this.columna);

        }
        
        return null;

    }
    
    
    private Nativo valorInterpretado(TipoDato tipo){
        switch (tipo){
                case INTEGER:
                    return  new Nativo(0, new Tipo(TipoDato.INTEGER), linea, columna-1 );
                    
                case REAL:
                    return  new Nativo(0.0, new Tipo(TipoDato.REAL), linea, columna-1 );
                    
                case CADENA:
                    return  new Nativo("", new Tipo(TipoDato.CADENA), linea, columna-1 );
                    
                case CARACTER:
                    return  new Nativo('0', new Tipo(TipoDato.CARACTER), linea, columna-1 );
                    
                case BOOLEANO:
                   return  new Nativo(false, new Tipo(TipoDato.BOOLEANO), linea, columna-1 );
                    
                   default: return null;
        }
    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String declaracion = "n" + arbol.getContador();
        String resultado = anterior+" ->"+declaracion+";\n";
        
        resultado += declaracion + "[label=\"Declaracion var\"];\n";
        for(String identificador: this.idRango){
            String idN = "n" + arbol.getContador();
        String igual= "n" + arbol.getContador();
        String expN = "n" + arbol.getContador();
        String fin = "n" + arbol.getContador();
        
        
        resultado += idN + "[label=\""+identificador+"\"];\n";
        resultado += igual + "[label=\"=\"];\n";
        resultado += expN + "[label=\" op ... op2\"];\n";
        resultado += fin + "[label=\";\"];\n";
        
        resultado += declaracion + " ->" + idN + ";\n";
        resultado += declaracion + " ->" + igual + ";\n";
        resultado += declaracion + " ->" + expN + ";\n";
        resultado += declaracion + " ->" + fin + ";\n";
            //resultado += this.valor.generarast(arbol, expN);
        }
        return resultado;
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
