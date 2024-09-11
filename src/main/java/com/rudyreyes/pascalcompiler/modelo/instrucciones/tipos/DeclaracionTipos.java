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
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class DeclaracionTipos extends Instruccion{
    
    private LinkedList<String> identificadores;

    public DeclaracionTipos(LinkedList<String> identificadores, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificadores = identificadores;
    }

    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        for (String identificador : identificadores) {
            Tipo t = new Tipo( identificador, this.tipo.getTipo());
            t.setNombreEstructura("nuevotipo");

            boolean creacion = arbol.getTablaTipos().setTipo(t);
            if (!creacion) {
                return new Errores("SEMANTICO", "El tipo \"" + identificador + "\" ya existe", this.linea, this.columna);
            }
        }

        

        return null;
    }
    
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
       String declaracion = "n" + arbol.getContador();
        String resultado = anterior+" ->"+declaracion+";\n";
        
        resultado += declaracion + "[label=\"Declaracion Tipo\"];\n";
        for(String identificador: this.identificadores){
            String idN = "n" + arbol.getContador();
        String igual= "n" + arbol.getContador();
        String expN = "n" + arbol.getContador();
        String fin = "n" + arbol.getContador();
        
        
        resultado += idN + "[label=\""+identificador+"\"];\n";
        resultado += igual + "[label=\":\"];\n";
        resultado += expN + "[label=\""+this.tipo.getTipo().toString()+"\"];\n";
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
