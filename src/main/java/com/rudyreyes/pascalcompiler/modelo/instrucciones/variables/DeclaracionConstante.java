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

/**
 *
 * @author rudyo
 */
public class DeclaracionConstante extends Instruccion{
    public boolean mutabilidad;
    public String identificador;
    public Instruccion valor;

    public DeclaracionConstante(boolean mutabilidad, String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        
        
        this.valor = valor;
    }

    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valorInterpretado = this.valor.interpretar(arbol, tabla);

        //validamos si es error
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        //validamos los tipo
        /*if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipo de dato erroneo, no se puede asignar el valor de tipo "+this.valor.tipo.getTipo()+ " a la variable de tipo "+ this.tipo.getTipo(), this.linea, this.columna);
        }*/
        
        Tipo tipoDatoVar = arbol.getTablaTipos().getTipo(this.valor.tipo.getNombre());
        
        if(tipoDatoVar== null){
             return new Errores("SEMANTICO", "El tipo de dato no existe", this.linea, this.columna);

        }
        this.tipo = tipoDatoVar;

        Simbolo s = new Simbolo(mutabilidad,this.tipo, this.identificador, valorInterpretado, this.linea, this.columna);

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "La variable \""+this.identificador+"\" ya existe", this.linea, this.columna);
        }

        return null;
    }
    
    @Override
    public String generarast(Arbol arbol, String anterior) {
        String declaracion = "n" + arbol.getContador();
        String idN = "n" + arbol.getContador();
        String igual= "n" + arbol.getContador();
        String expN = "n" + arbol.getContador();
        String fin = "n" + arbol.getContador();
        
        String resultado = anterior+" ->"+declaracion+";\n";
        
        resultado += declaracion + "[label=\"Declaracion Constante\"];\n";
        resultado += idN + "[label=\""+this.identificador+"\"];\n";
        resultado += igual + "[label=\":=\"];\n";
        resultado += expN + "[label=\"EXPRESION\"];\n";
        resultado += fin + "[label=\";\"];\n";
        
        resultado += declaracion + " ->" + idN + ";\n";
        resultado += declaracion + " ->" + igual + ";\n";
        resultado += declaracion + " ->" + expN + ";\n";
        resultado += declaracion + " ->" + fin + ";\n";
        
        
        return resultado += this.valor.generarast(arbol, expN);
    }

    @Override
    public String generarActivacion(Arbol arbol, String anterior) {
        return null;
    }
}
