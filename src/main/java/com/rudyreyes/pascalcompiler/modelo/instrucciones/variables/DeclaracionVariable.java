/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.pascalcompiler.modelo.instrucciones.variables;

import com.rudyreyes.pascalcompiler.modelo.abstracto.Instruccion;
import com.rudyreyes.pascalcompiler.modelo.errores.Errores;
import com.rudyreyes.pascalcompiler.modelo.expresiones.nativo.Nativo;
import com.rudyreyes.pascalcompiler.modelo.instrucciones.tipos.DeclaracionTipoRecord;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Arbol;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Simbolo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TablaSimbolos;
import com.rudyreyes.pascalcompiler.modelo.simbolo.Tipo;
import com.rudyreyes.pascalcompiler.modelo.simbolo.TipoDato;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author rudyo
 */
public class DeclaracionVariable extends Instruccion{
    public boolean mutabilidad;
    public LinkedList<String> identificadores;
    public Instruccion valor;
    public String tipoDato;

    public DeclaracionVariable(boolean mutabilidad, LinkedList<String> identificadores, Instruccion valor, String tipoDato, int linea, int columna) {
        super(new Tipo(TipoDato.INTEGER), linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificadores = identificadores;
        this.tipoDato = tipoDato;
        
        
        
    }

    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        //if el tipo de dato es struct, si no pues simios
        
        Tipo tipoDatoVar = arbol.getTablaTipos().getTipo(this.tipoDato);
        
        if(tipoDatoVar== null){
             return new Errores("SEMANTICO", "El tipo de dato no existe", this.linea, this.columna);

        }
        this.tipo = tipoDatoVar;
        verificarTipo();
        
        var valorInterpretado = this.valor.interpretar(arbol, tabla);

        //validamos si es error
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }

        //validamos los tipo
        if(this.tipo.getNombreEstructura().equalsIgnoreCase("record")){
            
        }else if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipo de dato erroneo, no se puede asignar el valor de tipo "+this.valor.tipo.getTipo()+ " a la variable de tipo "+ this.tipo.getTipo(), this.linea, this.columna);
        }
       
        for (String identificador : identificadores) {
            //aqui deberia de definir mas tarde el valor a guardar, ya que puede ser arreglo por ahora o book
            if(this.tipo.getNombreEstructura().equalsIgnoreCase("array")){
                Object[] valores = new Object[this.tipo.getDimension()];
                Simbolo s = new Simbolo(true, this.tipo, identificador, valores, this.linea, this.columna);

                boolean creacion = tabla.setVariable(s);
                if (!creacion) {
                    return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
                }
            }
            if (this.tipo.getNombreEstructura().equalsIgnoreCase("record")) {
                var busqueda = arbol.getStruct(this.tipoDato);
                if (busqueda == null) {
                    return new Errores("SEMANTICO", "Struct no existente",
                            this.linea, this.columna);
                }

                if (busqueda instanceof DeclaracionTipoRecord) {
                    DeclaracionTipoRecord struct = (DeclaracionTipoRecord) busqueda;
                    LinkedList<HashMap> nuevoHash = new LinkedList<>();
                    for (HashMap map : struct.listado) {
                        nuevoHash.add(new HashMap(map));
                    }
                    Simbolo s = new Simbolo(true, this.tipo, identificador, nuevoHash, this.linea, this.columna);

                    boolean creacion = tabla.setVariable(s);
                    if (!creacion) {
                        return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
                    }
                }
                
            }  else {
                Simbolo s = new Simbolo(mutabilidad, this.tipo, identificador, valorInterpretado, this.linea, this.columna);

                boolean creacion = tabla.setVariable(s);
                if (!creacion) {
                    return new Errores("SEMANTICO", "La variable \"" + identificador + "\" ya existe", this.linea, this.columna);
                }
            }
        }

        

        return null;
    }
    
    private void verificarTipo(){
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
                    
                case VOID:
                    valor =  new Nativo(0, new Tipo(TipoDato.VOID), linea, columna-1 );
                    break;
                    
            }
        }
        
        this.valor = valor;
    }
}
