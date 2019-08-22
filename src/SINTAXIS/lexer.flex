package SINTAXIS;
import static SINTAXIS.Token.*;
%%
%class Lexer
%type Token
%line
%column
Letra = [a-zA-Z]
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
"ADD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXTERNAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FETCH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PUBLIC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ALTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FILE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RAISERROR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"AND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FILLFACTOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"READ" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ANY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"READTEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"AS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FOREIGN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RECONFIGURE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FREETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"REFERENCES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"AUTHORIZATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FREETEXTTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"REPLICATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BACKUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FROM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RESTORE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BEGIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FULL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RESTRICT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BETWEEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FUNCTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RETURN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BREAK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GOTO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"REVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BROWSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GRANT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"REVOKE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BULK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GROUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RIGHT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"HAVING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ROLLBACK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CASCADE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"HOLDLOCK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ROWCOUNT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CASE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IDENTITY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ROWGUIDCOL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHECK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IDENTITY_INSERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RULE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHECKPOINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IDENTITYCOL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SAVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CLOSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SCHEMA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CLUSTERED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SECURITYAUDIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COALESCE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INDEX" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SELECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COLLATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INNER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SEMANTICKEYPHRASETABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COLUMN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INSERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SEMANTICSIMILARITYDETAILSTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COMMIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INTERSECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SEMANTICSIMILARITYTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COMPUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INTO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SESSION_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONSTRAINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONTAINS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"JOIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SETUSER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONTAINSTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"KEY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SHUTDOWN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONTINUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"KILL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SOME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LEFT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"STATISTICS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CREATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LIKE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SYSTEM_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CROSS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LINENO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURRENT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LOAD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TABLESAMPLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURRENT_DATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MERGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TEXTSIZE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURRENT_TIME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NATIONAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"THEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURRENT_TIMESTAMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NOCHECK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURRENT_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NONCLUSTERED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TOP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CURSOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DATABASE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NULL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRANSACTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DBCC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NULLIF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRIGGER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DEALLOCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRUNCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DECLARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OFF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRY_CONVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DEFAULT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OFFSETS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TSEQUAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DELETE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ON" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UNION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DENY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UNIQUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DESC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPENDATASOURCE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UNPIVOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DISK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPENQUERY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UPDATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DISTINCT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPENROWSET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UPDATETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DISTRIBUTED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPENXML" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"USE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DOUBLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OPTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DROP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"VALUES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DUMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ORDER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ARYING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ELSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OUTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"VIEW" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"END" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OVER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WAITFOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ERRLVL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PERCENT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WHEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ESCAPE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PIVOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WHERE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXCEPT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PLAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WHILE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRECISION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WITH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXECUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRIMARY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WITHIN GROUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXISTS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WRITETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PROC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ABSOLUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OVERLAPS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ACTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PAD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ADA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PARTIAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PASCAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ALL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXTRACT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"POSITION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ALLOCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FALSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PREPARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FIRST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRESERVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FLOAT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRIOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PRIVILEGES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ASC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FORTRAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"PROCEDURE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ASSERTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"FOUND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"AT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"REAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"AVG" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GLOBAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"RELATIVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"GO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BIT_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"BOTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ROWS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"HOUR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CASCADED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SCROLL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IMMEDIATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SECOND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CAST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"IN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SECTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CATALOG" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INCLUDE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SESSION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHAR_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INDICATOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHARACTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INITIALLY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CHARACTER_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SIZE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INPUT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SMALLINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INSENSITIVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SPACE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COLLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INTEGER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQLCA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQLCODE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"INTERVAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQLERROR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONNECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQLSTATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONNECTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SQLWARNING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ISOLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SUBSTRING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CONSTRAINTS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"SUM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LANGUAGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"CORRESPONDING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LAST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TEMPORARY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"COUNT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LEADING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TIME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LEVEL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TIMESTAMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TIMEZONE_HOUR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LOCAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TIMEZONE_MINUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"LOWER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MATCH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRAILING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MAX" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRANSLATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MINUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRANSLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DAY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MODULE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRIM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"MONTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"TRUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NAMES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DECIMAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NATURAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UNKNOWN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NCHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DEFERRABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"UPPER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DEFERRED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"USAGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NONE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"USING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DESCRIBE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"VALUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DESCRIPTOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DIAGNOSTICS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"NUMERIC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"VARCHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DISCONNECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OCTET_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"VARYING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"DOMAIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ON" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ONLY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WHENEVER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WORK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"END-EXEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"WRITE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"YEAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"OUTPUT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"ZONE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};
"EXCEPTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESERVADA;};

/*                                                       COMENTARIOS                                                       */
{comment}+ {/* ignore*/}
/*                                                         ESPACIOS                                                        */
{Empty}+ {/* ignore*/}
/*                                                      IDENTIFICADORES                                                     */

({Letra}) ({Letra}|{Digito}| _ )* {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength-1; return IDENTIFICADOR}

/*                                                         TIPOS DE DATO                                                    */
[0 | 1 | NULL] {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength-1; return CONSTANTE_BOOLENA}
({Digito})+ {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength-1; return CONSTANTE_ENTERA}
({Digito})+ "." {(Digito)} ([E|e] [+|-]? {Digito}+)* {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return CONSTANTE_DECIMAL}
"'" {String} "'" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength-1; return CADENA} 

/*                                                       OPERADORES,PUNTUACION, OTROS                                         */ 
"+" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return SUMA}
"-" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return RESTA}
"*" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MULTIPLICACION}
"/" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return DIVISION}
"%" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MOD}
"<" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MENOR}
"<=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MENOR_IGUAL}
">" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MAYOR}
">=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return MAYOR_IGUAL}
"=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return ASIGNACION}
"==" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return IGUALACION}
"!=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return DIFERENCIA}
"&&" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return AND}
"||" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return OR}
"!" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return EXCLAMACION}
";" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return PUNTO_COMA}
"," {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return COMA}
"." {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return PUNTO}
"[" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return CORCHETEA}
"]" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return CORCHETEC}
"(" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return PARENTESISA}
")" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return PARENTESISC}
"{" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return LLAVEA}
"}" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return LLAVEC}
"[]" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return CORCHETES}
"()" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return PARENTESIS}
"{}" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return LLAVES}
"#" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return NUMERAL}
"##" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return NUMERALES}

/*                                                               ERROR                                                            */     
  .  {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength -1; return ERROR}

