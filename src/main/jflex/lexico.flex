package com.rudyreyes.pascalcompiler.controlador.analisis;
//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import com.rudyreyes.pascalcompiler.modelo.errores.*;


%%

//codigo de usuario
%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

//caracteristicas de jflex
%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase

//simbolos del sistema
PAR1="("
PAR2=")"
MAS="+"
MENOS="-"
MULT = "*"
DIV = "/"
DIVE = "div"
MOD = "mod"
FINCADENA=";"
//DOSPUNTOS = ":"
//LLAVE1="{"
//LLAVE2="}"
//CORCHETE1 = "["
//CORCHETE2 = "]"
//COMA = ","
PUNTO = "."

//simbolos de op relacionales
IGUAL = "="
DIFERENTE = "<>"
MENOR = "<"
MENORIGUAL = "<="
MAYOR = ">"
MAYORIGUAL = ">="

//operaciones logicas
OR = "or"
AND = "and"
THEN = "then"
ELSE = "else"
NOT = "not"



BLANCOS=[\ \r\t\f\n]+

//tipo de datos

ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
CADENA = [\'](\\\'|[^\'])*[\']
BOOLEANO = true|false
CARACTER = '([^\\'\\n\\r]|\\.)'
ID = [a-zA-Z][a-zA-Z0-9_]*

//comentarios
COMENTARIO = ([{](.*)[}])
COMENTARIOMULTI = "(*"([^*]|\*[^/])*"\*)"

//palabras reservadas
IMPRIMIR="writeln"
BEGIN = "begin"
END = "end"

%%

<YYINITIAL> {IMPRIMIR}  {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> {BEGIN}           {return new Symbol(sym.BEGIN, yyline, yycolumn,yytext());}
<YYINITIAL> {END}           {return new Symbol(sym.END, yyline, yycolumn,yytext());}


<YYINITIAL> {OR}    {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> {AND}    {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> {THEN}    {return new Symbol(sym.THEN, yyline, yycolumn,yytext());}
<YYINITIAL> {NOT}    {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}
<YYINITIAL> {ELSE}    {return new Symbol(sym.ELSE, yyline, yycolumn,yytext());}
<YYINITIAL> {DIVE}           {return new Symbol(sym.DIVE, yyline, yycolumn,yytext());}
<YYINITIAL> {MOD}           {return new Symbol(sym.MOD, yyline, yycolumn,yytext());}



<YYINITIAL> {DECIMAL}   {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO}    {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOLEANO}  {return new Symbol(sym.BOOLEAN, yyline, yycolumn,yytext());}


<YYINITIAL> {ID}        {return new Symbol(sym.ID, yyline, yycolumn,yytext());}


<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
    }

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }



<YYINITIAL> {COMENTARIO} {}
<YYINITIAL> {COMENTARIOMULTI} {}

<YYINITIAL> {FINCADENA}     {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {PUNTO}        {return new Symbol(sym.PUNTO, yyline, yycolumn,yytext());}


<YYINITIAL> {PAR1}          {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2}          {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}


<YYINITIAL> {DIFERENTE}     {return new Symbol(sym.DIFERENTE, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR}         {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MENORIGUAL}    {return new Symbol(sym.MENORIGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR}         {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYORIGUAL}    {return new Symbol(sym.MAYORIGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL}    {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}



<YYINITIAL> {MAS}           {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS}         {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {MULT}          {return new Symbol(sym.MULT, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV}           {return new Symbol(sym.DIV, yyline, yycolumn,yytext());}




<YYINITIAL> {BLANCOS} {}



<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO","El caracter \" "+
                yytext()+" \" NO pertenece al lenguaje", yyline, yycolumn));
}