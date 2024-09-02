/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.simbolo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class TablaTipos {
    private HashMap<String, Object> tablaActual;
    private String nombre;
    
    public TablaTipos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
        
        cargarTiposPropios();
    }   
    
    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean setTipo(Tipo tipo) {
        Tipo busqueda
                = (Tipo) this.tablaActual.get(
                        tipo.getNombre().
                                toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(tipo.getNombre().toLowerCase(),
                    tipo);
            return true;
        }
        return false;
    }

    public Tipo getTipo(String id) {
        
            Tipo busqueda = (Tipo) tablaActual.
                    get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        return null;
    }
    public List<Tipo> getSimbolosTablaActual() {
        List<Tipo> tipos = new ArrayList<>();
        for (Object obj : this.tablaActual.values()) {
            if (obj instanceof Tipo) {
                tipos.add((Tipo) obj);
            } else {
                // Maneja el caso en que obj no es una instancia de Simbolo si es necesario
                System.out.println("Warning: Encontrado un objeto no Simbolo en la tabla de símbolos");
            }
        }
        return tipos;
    }
    public String mostrarSimbolosTablaActual() {
        String lista = "";
        List<Tipo> tipos = getSimbolosTablaActual();
        for (Tipo tipo : tipos) {
            lista += tipo.imprimirTipo()+"\n";
        }
        
        return lista;
    }

    private void cargarTiposPropios() {
        Tipo tipoInteger = new Tipo("integer", TipoDato.INTEGER, "integer");
        Tipo tipoReal = new Tipo("real", TipoDato.REAL, "real");
        Tipo tipoChar = new Tipo("char", TipoDato.CARACTER, "char");
        Tipo tipoString = new Tipo("string", TipoDato.CADENA, "string");
        Tipo tipoBoolean = new Tipo("boolean", TipoDato.BOOLEANO, "boolean");
        Tipo tipoVoid = new Tipo("void", TipoDato.VOID, "void");
        
         this.tablaActual.put(tipoInteger.getNombre().toLowerCase(),tipoInteger);
         this.tablaActual.put(tipoReal.getNombre().toLowerCase(),tipoReal);
         this.tablaActual.put(tipoChar.getNombre().toLowerCase(),tipoChar);
         this.tablaActual.put(tipoString.getNombre().toLowerCase(),tipoString);
         this.tablaActual.put(tipoBoolean.getNombre().toLowerCase(),tipoBoolean);
         this.tablaActual.put(tipoVoid.getNombre().toLowerCase(),tipoVoid);
        
    }

}
