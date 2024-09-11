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
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class DeclaracionTipoRecord extends Instruccion {
    public String id;
    public LinkedList<HashMap> listado;

    public DeclaracionTipoRecord(String id, LinkedList<HashMap> listado, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.listado = listado;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Tipo nuevoTipo = new Tipo(id, TipoDato.VOID, "record");
        boolean creacion = arbol.getTablaTipos().setTipo(nuevoTipo);
            if (!creacion) {
                return new Errores("SEMANTICO", "El tipo \"" + id + "\" ya existe", this.linea, this.columna);
            }
        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        return "";
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
