/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private TablaSimbolos tablaGlobal;
    private TablaTipos tablaTipos; 
    public  LinkedList<Errores> errores;
    public List<TablaSimbolos> tablasEntornos;
    private LinkedList<Instruccion> funciones;
    private LinkedList<Instruccion> structs;
    public int contador;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new TablaSimbolos();
        this.errores = new LinkedList<>();
        this.tablasEntornos = new ArrayList<>();
        this.funciones = new LinkedList<>();
        this.structs = new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public TablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(TablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public TablaTipos getTablaTipos() {
        return tablaTipos;
    }

    public void setTablaTipos(TablaTipos tablaTipos) {
        this.tablaTipos = tablaTipos;
    }

    
    
    

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
    
    public void agregarTablaEntorno(TablaSimbolos tabla) {
        this.tablasEntornos.add(tabla);
    }
    
    /*public List<EntornoSimbolos> getTodosLosSimbolos() {
        List<EntornoSimbolos> todosLosSimbolos = new ArrayList<>();

        for (TablaSimbolos tabla : tablasEntornos) {
            List<Simbolo> simbolos = tabla.getSimbolosTablaActual();
            EntornoSimbolos entornoSimbolos = new EntornoSimbolos(tabla.getNombre(), simbolos);
            todosLosSimbolos.add(entornoSimbolos);
        }

        // Agregar los símbolos de la tabla global
        List<Simbolo> simbolosGlobales = tablaGlobal.getSimbolosTablaActual();
        EntornoSimbolos entornoGlobal = new EntornoSimbolos(tablaGlobal.getNombre(), simbolosGlobales);
        todosLosSimbolos.add(entornoGlobal);

        return todosLosSimbolos;
    }*/

    /*public void mostrarTodosLosSimbolos() {
    List<EntornoSimbolos> todosLosSimbolos = getTodosLosSimbolos();
    for (EntornoSimbolos entornoSimbolos : todosLosSimbolos) {
        System.out.println("Entorno: " + entornoSimbolos.getNombreEntorno());
        for (Simbolo simbolo : entornoSimbolos.getSimbolos()) {
            System.out.println(simbolo.imprimirSimbolo());
        }
    }
}*/
    
    
    public void writeln(String valor){
        
        this.consola += valor+"\n";
    }
    
    /*private String secuenciasEscape(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (c == '\\' && i + 1 < valor.length()) {
                char siguienteC = valor.charAt(i + 1);
                switch (siguienteC) {
                    case 'n':
                        resultado.append('\n');
                        i++;
                        break;
                    case 't':
                        resultado.append('\t');
                        i++; 
                        break;
                    case 'r':
                        resultado.append('\r');
                        i++; 
                        break;
                    case '\\':
                        resultado.append('\\');
                        i++;
                        break;
                    case '\"':
                        resultado.append('\"');
                        i++; 
                        break;
                    case '\'':
                        resultado.append('\'');
                        i++; 
                        break;
                    default:
                        resultado.append(c); 
                        break;
                }
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }*/

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }
    
    public void setStructs(LinkedList<Instruccion> structs){
        this.structs = structs;
    }

    public void addFunciones(Instruccion funcion) {    
        this.funciones.add(funcion);
    }
    
    public void addStructs(Instruccion struct) {    
        this.structs.add(struct);
    }

    /*public Instruccion getFuncion(String id) {
        for (var i : this.funciones) {
            if (i instanceof Metodo) {
                Metodo metodo = (Metodo) i;
                if (metodo.id.equalsIgnoreCase(id)) {
                    return metodo;
                }
            }
        }
        return null;
    }*/
    
    /*public Instruccion getStruct(String id){
        for (var i : this.structs) {
            if (i instanceof DeclaracionStruct) {
                DeclaracionStruct struct = (DeclaracionStruct) i;
                if (struct.id.equalsIgnoreCase(id)) {
                    return struct;
                }
            }
        }
        
        return null;
    }*/
    
    public int getContador() {
        this.contador++;
        return this.contador;

    }
}
