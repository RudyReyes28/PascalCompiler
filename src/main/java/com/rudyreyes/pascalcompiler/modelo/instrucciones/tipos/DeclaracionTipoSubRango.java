/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.tipos;

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
public class DeclaracionTipoSubRango extends Instruccion {
    private LinkedList<String> idRango;
    private Instruccion rangoInicial;
    private Instruccion rangoFinal;

    public DeclaracionTipoSubRango(LinkedList<String> idRango, Instruccion rangoInicial, Instruccion rangoFinal, int linea, int columna) {
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
        
        if((rangoInicial.tipo.getTipo() == TipoDato.INTEGER) && (rangoFinal.tipo.getTipo() == TipoDato.INTEGER)){
            for (String identificador : this.idRango) {

                Tipo t = new Tipo(identificador, this.rangoInicial.tipo.getTipo());
                t.setMinimo((int) rangoI);
                t.setMaximo((int) rangoF);
                t.setDimension((int) rangoF - (int) rangoI);
                t.setNombreEstructura("subrange");

                boolean creacion = arbol.getTablaTipos().setTipo(t);
                if (!creacion) {
                    return new Errores("SEMANTICO", "El tipo \"" + this.idRango + "\" ya existe", this.linea, this.columna);
                }
            }

        }else{
              return new Errores("SEMANTICO", "Error en la declaracion de tipo no tiene definido los limites de tipo INTEGER", this.linea, this.columna);

        }
        
        return null;

    }
    
    
    
}
