/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.funciones;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.variables.DeclaracionVariable;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class LlamadoFuncion extends Instruccion{
     private String id;
    private LinkedList<Instruccion> parametros;

    public LlamadoFuncion(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(this.id);
        if (busqueda == null) {
            return new Errores("SEMANTICO", "Funcion no existente",
                    this.linea, this.columna);
        }
        if (busqueda instanceof DeclaracionFuncion) {
            var metodo = (DeclaracionFuncion)busqueda;
            
            var newTabla = new TablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("LLAMADA FUNCION " + this.id);
            arbol.agregarTablaEntorno(newTabla);
            if (metodo.parametros.size() != this.parametros.size()) {
                return new Errores("SEMANTICO", "Parametros Erroneos",
                        this.linea, this.columna);
            }
            
            for (int i = 0; i < this.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.
                        get(i).get("id");

                var valor = this.parametros.get(i);

                String nombreTipo = (String) metodo.parametros.
                        get(i).get("tipo");
                
                Tipo tipo2 = arbol.getTablaTipos().getTipo(nombreTipo);

                var declaracionParametro = new DeclaracionVariable(true, new LinkedList<>(Collections.singletonList(identificador)), null, tipo2.getNombre(),
                        this.linea, this.columna);

                var resultado = declaracionParametro.interpretar(arbol, newTabla);
                
                if (resultado instanceof Errores) {
                    return resultado;
                }
                
                var valorInterpretado = valor.interpretar(arbol, tabla);
                if (valorInterpretado instanceof Errores) {
                    return valorInterpretado;
                }
                
                var variable = newTabla.getVariable(identificador);
                if (variable == null) {
                    return new Errores("SEMANTICO", "Error declaracion parametros",
                            this.linea, this.columna);
                }
                
                if (variable.getTipo().getTipo() != valor.tipo.getTipo()) {
                    return new Errores("SEMANTICO", "Error en tipo de parametro",
                            this.linea, this.columna);
                }
                
                variable.setValor(valorInterpretado);
                
            }
            
            var resultadoFuncion = metodo.interpretar(arbol, newTabla);
            if(resultadoFuncion instanceof FuncionReturn){
                
                    FuncionReturn miR = (FuncionReturn)resultadoFuncion;
                    var resR = miR.instruccion.interpretar(arbol, tabla);
                    
                    this.tipo = miR.tipo;
                    
                    return resR;
                
            }
            if (resultadoFuncion instanceof Errores) {
                return resultadoFuncion;
            }
        }
        
        return null;

    }
    @Override
    public String generarast(Arbol arbol, String anterior) {
        var busqueda = arbol.getFuncion(id);
        var metodo = (DeclaracionFuncion)busqueda;
        String nodoExp1 = "n" + arbol.getContador();
        String nodoOp = "n" + arbol.getContador();
        String nodoExp2 = "n" + arbol.getContador();
        String inst = "n" + arbol.getContador();
        
        String resultado = anterior + " -> " + nodoExp1 + ";\n";
        
        
        resultado += nodoExp1 + "[label=\"LLAMADA FUNCION\"];\n";
        resultado += nodoOp + "[label=\"FUNCION\"];\n";
        resultado += nodoExp2 + "[label=\""+ this.id+" \"];\n";
        resultado += inst + "[label=\" INSTRUCCIONES \"];\n";
        
        resultado += nodoExp1 +"->"+ nodoOp+ ";\n";
        resultado += nodoOp +"->"+ nodoExp2+ ";\n";
        resultado += nodoExp2 +"->"+ inst+ ";\n";
   
        return resultado+= metodo.generarast(arbol, inst);
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
