package SINTAXIS;
import static SINTAXIS.Token.*;
%%
%class Lexer
%type Token
%line
%column
Letra = [a-zA-Z_]
Digito = [0-9]
Salto = [\r| \n| \r\n]
Espacio = [ | \t]
Empty = {Salto} | {Espacio}
String = (Letra | Digito | Espacio)*
/*Comentarios*/
comlinea = "--" {String}
comMlinea = "/*" ({string} | {Salto})* ~"*/"
comment = {comlinea} | {comMlinea}
/*Variables de la clase Generada*/
%{
  public String foundLine;
  public int line;
  public int columnSt;
  public int columnNd;  
%}
%%
/*Ignored*/
{Empty}+ {/*Ignore*/}
{comment}+ {/*Ignore*/}

/*------------------------------------------------------------RESERVADAS-------------------------------------------------*/
"ADD" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"ALL" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"ALTER" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"AND" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"ANY" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"AS" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"ASC" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"AUTHORIZATION" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BACKUP" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BEGIN" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BETWEEN" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BREAK" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BROWSE" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BULK" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"BY" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CASCADE" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CASE" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CHECK" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CHECKPOINT" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CLOSE" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"CLUSTERED" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
"COALASCE" {foundLine = yytext(); line = yyline; columnSt = yycolum; columnNd = yycolumn + yylength -1; return RESERVADA;}
