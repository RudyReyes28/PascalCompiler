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
public class DeclaracionTipoArreglo extends Instruccion{
    private LinkedList<String> nombresTipos;
    private Instruccion indiceInicial;
    private Instruccion indiceFinal;

    public DeclaracionTipoArreglo(LinkedList<String> nombresTipos, Instruccion indiceI, Instruccion indiceF, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.nombresTipos = nombresTipos;
        this.indiceInicial = indiceI;
        this.indiceFinal = indiceF;
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
        
        if((this.indiceInicial.tipo.getTipo() == TipoDato.INTEGER) && (this.indiceFinal.tipo.getTipo() == TipoDato.INTEGER)){
            for (String identificador : this.nombresTipos) {

                Tipo t = new Tipo(identificador, this.tipo.getTipo());
                t.setMinimo((int) indiceI);
                t.setMaximo((int) indiceF);
                t.setDimension((int) indiceF - (int) indiceI+1);
                t.setNombreEstructura("array");

                boolean creacion = arbol.getTablaTipos().setTipo(t);
                if (!creacion) {
                    return new Errores("SEMANTICO", "El tipo \"" + identificador + "\" ya existe", this.linea, this.columna);
                }
            }

        }else{
              return new Errores("SEMANTICO", "Error en la declaracion de tipo arreglono tiene definido los limites de tipo INTEGER", this.linea, this.columna);

        }
        
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
