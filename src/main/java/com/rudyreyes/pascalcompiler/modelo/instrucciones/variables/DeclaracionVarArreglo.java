/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
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
public class DeclaracionVarArreglo extends Instruccion{
    private LinkedList<String> nombresTipos;
    private Instruccion indiceInicial;
    private Instruccion indiceFinal;
    private String nombreTipo; 
    
    public DeclaracionVarArreglo(LinkedList<String> nombresTipos, Instruccion indiceI, Instruccion indiceF,String nombreTipo, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
        this.nombresTipos = nombresTipos;
        this.indiceInicial = indiceI;
        this.indiceFinal = indiceF;
        this.nombreTipo = nombreTipo;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var indiceI = indiceInicial.interpretar(arbol, tabla);
        
        if(indiceI instanceof Errores){
            return indiceI;
        }
        
        
        var indiceF = indiceFinal.interpretar(arbol, tabla);
        
        if(indiceF instanceof Errores){
            return indiceF;
        }
        
        Tipo tipoDatoVar = arbol.getTablaTipos().getTipo(this.nombreTipo);
        
        if(tipoDatoVar== null){
             return new Errores("SEMANTICO", "El tipo de dato no existe", this.linea, this.columna);

        }
        this.tipo = tipoDatoVar;
        
        if((this.indiceInicial.tipo.getTipo() == TipoDato.INTEGER) && (this.indiceFinal.tipo.getTipo() == TipoDato.INTEGER)){
            for (String identificador : this.nombresTipos) {

                Tipo t = new Tipo(identificador, this.tipo.getTipo());
                t.setMinimo((int) indiceI);
                t.setMaximo((int) indiceF);
                t.setDimension((int) indiceF - (int) indiceI+1);
                t.setNombreEstructura("array");

                Object[] valores = new Object[t.getDimension()];
                Simbolo s = new Simbolo(true, t, identificador, valores, this.linea, this.columna);

                boolean creacion = tabla.setVariable(s);
                if (!creacion) {
                    return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
                }
            }

        }else{
              return new Errores("SEMANTICO", "Error en la declaracion de tipo arreglono tiene definido los limites de tipo INTEGER", this.linea, this.columna);

        }
        
        return null;
    }
}
