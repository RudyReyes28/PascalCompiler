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
public class DeclaracionVariable extends Instruccion{
    public boolean mutabilidad;
    public LinkedList<String> identificadores;
    public Instruccion valor;

    public DeclaracionVariable(boolean mutabilidad, LinkedList<String> identificadores, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificadores = identificadores;
        
        
        if(valor ==null){
            switch (tipo.getTipo()){
                case INTEGER:
                    valor =  new Nativo(0, new Tipo(TipoDato.INTEGER), linea, columna-1 );
                    break;
                case REAL:
                    valor =  new Nativo(0.0, new Tipo(TipoDato.REAL), linea, columna-1 );
                    break;
                case CADENA:
                    valor =  new Nativo("", new Tipo(TipoDato.CADENA), linea, columna-1 );
                    break;
                case CARACTER:
                    valor =  new Nativo('0', new Tipo(TipoDato.CARACTER), linea, columna-1 );
                    break;
                case BOOLEANO:
                    valor =  new Nativo(false, new Tipo(TipoDato.BOOLEANO), linea, columna-1 );
                    break;
            }
        }
        
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
        if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipo de dato erroneo, no se puede asignar el valor de tipo "+this.valor.tipo.getTipo()+ " a la variable de tipo "+ this.tipo.getTipo(), this.linea, this.columna);
        }
       
        for (String identificador : identificadores) {
            Simbolo s = new Simbolo(mutabilidad, this.tipo, identificador, valorInterpretado, this.linea, this.columna);

            boolean creacion = tabla.setVariable(s);
            if (!creacion) {
                return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
            }
        }

        

        return null;
    }
    
    
}
