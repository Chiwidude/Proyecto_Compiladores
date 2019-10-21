package SINTAXIS;
import static SINTAXIS.Token.*;
%%
%class Lexer
%type Token
%line
%column
Letra = [a-zA-Z]
Digito = [0-9]
Salto = \r| \n| \r\n
Espacio = [ \t]
Empty = {Salto} | {Espacio}
String = ([^\r\n'])*
/*Comentarios*/
comlinea = ("--"){String}
comMlinea = "/*" ~ "*/"
comment = {comlinea} | {comMlinea}
operadores=("+")|("-")|("*")|("/")|("%")|("<")|("<=")|(">")|(">=")|("=")|("==")|("!=")|("&&")|("||")|("!")|(";")|(",")|(".")|("[")|("]")|("(")|(")")|("{")|("}")|("[]")|("()")|("{}")|("@")|("#")|("##")
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
"ADD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ADD;}
"EXTERNAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXTERNAL;}
"FETCH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FETCH;}
"PUBLIC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PUBLIC;}
"ALTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ALTER;}
"FILE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FILE;}
"RAISERROR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RAISERROR;}
"AND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AND;}
"FILLFACTOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FILLFACTOR;}
"READ" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return READ;}
"ANY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ANY;}
"FOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FOR;}
"READTEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return READTEXT;}
"AS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AS;}
"FOREIGN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FOREIGN;}
"RECONFIGURE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RECONFIGURE;}
"FREETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FREETEXT;}
"REFERENCES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return REFERENCES;}
"AUTHORIZATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AUTHORIZATION;}
"FREETEXTTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FREETEXTTABLE;}
"REPLICATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return REPLICATION;}
"BACKUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BACKUP;}
"FROM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FROM;}
"RESTORE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RESTORE;}
"BEGIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BEGIN;}
"FULL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FULL;}
"RESTRICT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RESTRICT;}
"BETWEEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BETWEEN;}
"FUNCTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FUNCTION;}
"RETURN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RETURN;}
"BREAK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BREAK;}
"GOTO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GOTO;}
"REVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return REVERT;}
"BROWSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BROWSE;}
"GRANT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GRANT;}
"REVOKE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return REVOKE;}
"BULK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BULK;}
"GROUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GROUP;}
"RIGHT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RIGHT;}
"BY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BY;}
"HAVING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return HAVING;}
"ROLLBACK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ROLLBACK;}
"CASCADE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CASCADE;}
"HOLDLOCK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return HOLDLOCK;}
"ROWCOUNT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ROWCOUNT;}
"CASE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CASE;}
"IDENTITY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IDENTITY;}
"ROWGUIDCOL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ROWGUIDCOL;}
"CHECK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHECK;}
"IDENTITY_INSERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IDENTITY_INSERT;}
"RULE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RULE;}
"CHECKPOINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHECKPOINT;}
"IDENTITYCOL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IDENTITYCOL;}
"SAVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SAVE;}
"CLOSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CLOSE;}
"IF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IF;}
"SCHEMA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SCHEMA;}
"CLUSTERED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CLUSTERED;}
"IN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IN;}
"SECURITYAUDIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SECURITYAUDIT;}
"COALESCE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COALESCE;}
"INDEX" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INDEX;}
"SELECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SELECT;}
"COLLATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COLLATE;}
"INNER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INNER;}
"SEMANTICKEYPHRASETABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SEMANTICKEYPHRASETABLE;}
"COLUMN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COLUMN;}
"INSERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INSERT;}
"SEMANTICSIMILARITYDETAILSTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SEMANTICSIMILARITYDETAILSTABLE;}
"COMMIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COMMIT;}
"INTERSECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INTERSECT;}
"SEMANTICSIMILARITYTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SEMANTICSIMILARITYTABLE;}
"COMPUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COMPUTE;}
"INTO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INTO;}
"SESSION_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SESSION_USER;}
"CONSTRAINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONSTRAINT;}
"IS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IS;}
"SET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SET;}
"CONTAINS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONTAINS;}
"JOIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return JOIN;}
"SETUSER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SETUSER;}
"CONTAINSTABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONTAINSTABLE;}
"KEY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return KEY;}
"SHUTDOWN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SHUTDOWN;}
"CONTINUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONTINUE;}
"KILL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return KILL;}
"SOME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SOME;}
"CONVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONVERT;}
"LEFT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LEFT;}
"STATISTICS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return STATISTICS;}
"CREATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CREATE;}
"LIKE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LIKE;}
"SYSTEM_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SYSTEM_USER;}
"CROSS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CROSS;}
"LINENO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LINENO;}
"TABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TABLE;}
"CURRENT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURRENT;}
"LOAD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LOAD;}
"TABLESAMPLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TABLESAMPLE;}
"CURRENT_DATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURRENT_DATE;}
"MERGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MERGE;}
"TEXTSIZE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TEXTSIZE;}
"CURRENT_TIME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURRENT_TIME;}
"NATIONAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NATIONAL;}
"THEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return THEN;}
"CURRENT_TIMESTAMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURRENT_TIMESTAMP;}
"NOCHECK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NOCHECK;}
"TO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TO;}
"CURRENT_USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURRENT_USER;}
"NONCLUSTERED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NONCLUSTERED;}
"TOP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TOP;}
"CURSOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CURSOR;}
"NOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NOT;}
"TRAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRAN;}
"DATABASE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DATABASE;}
"NULL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NULL;}
"TRANSACTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRANSACTION;}
"DBCC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DBCC;}
"NULLIF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NULLIF;}
"TRIGGER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRIGGER;}
"DEALLOCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DEALLOCATE;}
"OF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OF;}
"TRUNCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRUNCATE;}
"DECLARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DECLARE;}
"OFF" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OFF;}
"TRY_CONVERT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRY_CONVERT;}
"DEFAULT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DEFAULT;}
"OFFSETS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OFFSETS;}
"TSEQUAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TSEQUAL;}
"DELETE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DELETE;}
"ON" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ON;}
"UNION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UNION;}
"DENY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DENY;}
"OPEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPEN;}
"UNIQUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UNIQUE;}
"DESC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DESC;}
"OPENDATASOURCE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPENDATASOURCE;}
"UNPIVOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UNPIVOT;}
"DISK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DISK;}
"OPENQUERY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPENQUERY;}
"UPDATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UPDATE;}
"DISTINCT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DISTINCT;}
"OPENROWSET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPENROWSET;}
"UPDATETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UPDATETEXT;}
"DISTRIBUTED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DISTRIBUTED;}
"OPENXML" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPENXML;}
"USE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return USE;}
"DOUBLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DOUBLE;}
"OPTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OPTION;}
"USER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return USER;}
"DROP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DROP;}
"OR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OR;}
"VALUES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return VALUES;}
"DUMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DUMP;}
"ORDER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ORDER;}
"ARYING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ARYING;}
"ELSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ELSE;}
"OUTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OUTER;}
"VIEW" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return VIEW;}
"END" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return END;}
"OVER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OVER;}
"WAITFOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WAITFOR;}
"ERRLVL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ERRLVL;}
"PERCENT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PERCENT;}
"WHEN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WHEN;}
"ESCAPE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ESCAPE;}
"PIVOT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PIVOT;}
"WHERE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WHERE;}
"EXCEPT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXCEPT;}
"PLAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PLAN;}
"WHILE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WHILE;}
"EXEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXEC;}
"PRECISION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRECISION;}
"WITH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WITH;}
"EXECUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXECUTE;}
"PRIMARY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRIMARY;}
"WITHIN GROUP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WITHIN_GROUP;}
"EXISTS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXISTS;}
"PRINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRINT;}
"WRITETEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WRITETEXT;}
"EXIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXIT;}
"PROC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PROC;}
"ABSOLUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ABSOLUTE;}
"OVERLAPS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OVERLAPS;}
"ACTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ACTION;}
"PAD" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PAD;}
"ADA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ADA;}
"PARTIAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PARTIAL;}
"PASCAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PASCAL;}
"ALL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ALL;}
"EXTRACT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXTRACT;}
"POSITION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return POSITION;}
"ALLOCATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ALLOCATE;}
"FALSE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FALSE;}
"PREPARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PREPARE;}
"FIRST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FIRST;}
"PRESERVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRESERVE;}
"FLOAT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FLOAT;}
"ARE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ARE;}
"PRIOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRIOR;}
"PRIVILEGES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PRIVILEGES;}
"ASC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ASC;}
"FORTRAN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FORTRAN;}
"PROCEDURE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PROCEDURE;}
"ASSERTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ASSERTION;}
"FOUND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return FOUND;}
"AT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AT;}
"REAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return REAL;}
"AVG" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AVG;}
"GET" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GET;}
"GLOBAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GLOBAL;}
"RELATIVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RELATIVE;}
"GO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return GO;}
"BIT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BIT;}
"BIT_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BIT_LENGTH;}
"BOTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return BOTH;}
"ROWS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ROWS;}
"HOUR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return HOUR;}
"CASCADED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CASCADED;}
"SCROLL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SCROLL;}
"IMMEDIATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IMMEDIATE;}
"SECOND" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SECOND;}
"CAST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CAST;}
"IN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IN;}
"SECTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SECTION;}
"CATALOG" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CATALOG;}
"INCLUDE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INCLUDE;}
"CHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHAR;}
"SESSION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SESSION;}
"CHAR_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHAR_LENGTH;}
"INDICATOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INDICATOR;}
"CHARACTER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHARACTER;}
"INITIALLY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INITIALLY;}
"CHARACTER_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CHARACTER_LENGTH;}
"SIZE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SIZE;}
"INPUT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INPUT;}
"SMALLINT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SMALLINT;}
"INSENSITIVE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INSENSITIVE;}
"SPACE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SPACE;}
"INT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INT;}
"SQL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQL;}
"COLLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COLLATION;}
"INTEGER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INTEGER;}
"SQLCA" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQLCA;}
"SQLCODE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQLCODE;}
"INTERVAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return INTERVAL;}
"SQLERROR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQLERROR;}
"CONNECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONNECT;}
"SQLSTATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQLSTATE;}
"CONNECTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONNECTION;}
"SQLWARNING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SQLWARNING;}
"ISOLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ISOLATION;}
"SUBSTRING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SUBSTRING;}
"CONSTRAINTS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONSTRAINTS;}
"SUM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SUM;}
"LANGUAGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LANGUAGE;}
"CORRESPONDING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CORRESPONDING;}
"LAST" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LAST;}
"TEMPORARY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TEMPORARY;}
"COUNT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COUNT;}
"LEADING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LEADING;}
"TIME" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TIME;}
"LEVEL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LEVEL;}
"TIMESTAMP" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TIMESTAMP;}
"TIMEZONE_HOUR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TIMEZONE_HOUR;}
"LOCAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LOCAL;}
"TIMEZONE_MINUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TIMEZONE_MINUTE;}
"LOWER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LOWER;}
"MATCH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MATCH;}
"TRAILING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRAILING;}
"MAX" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MAX;}
"MIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MIN;}
"TRANSLATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRANSLATE;}
"DATE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DATE;}
"MINUTE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MINUTE;}
"TRANSLATION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRANSLATION;}
"DAY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DAY;}
"MODULE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MODULE;}
"TRIM" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRIM;}
"MONTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MONTH;}
"TRUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return TRUE;}
"DEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DEC;}
"NAMES" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NAMES;}
"DECIMAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DECIMAL;}
"NATURAL" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NATURAL;}
"UNKNOWN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UNKNOWN;}
"NCHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NCHAR;}
"DEFERRABLE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DEFERRABLE;}
"NEXT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NEXT;}
"UPPER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return UPPER;}
"DEFERRED" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DEFERRED;}
"NO" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NO;}
"USAGE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return USAGE;}
"NONE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NONE;}
"USING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return USING;}
"DESCRIBE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DESCRIBE;}
"VALUE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return VALUE;}
"DESCRIPTOR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DESCRIPTOR;}
"DIAGNOSTICS" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DIAGNOSTICS;}
"NUMERIC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NUMERIC;}
"VARCHAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return VARCHAR;}
"DISCONNECT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DISCONNECT;}
"OCTET_LENGTH" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OCTET_LENGTH;}
"VARYING" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return VARYING;}
"DOMAIN" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DOMAIN;}
"ON" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ON;}
"ONLY" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ONLY;}
"WHENEVER" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WHENEVER;}
"WORK" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WORK;}
"END-EXEC" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return END_EXEC;}
"WRITE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return WRITE;}
"YEAR" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return YEAR;}
"OUTPUT" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OUTPUT;}
"ZONE" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ZONE;}
"EXCEPTION" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXCEPTION;}


/*                                                 IDENTIFICADORES                                                     */

({Letra}) ({Letra}|{Digito}| "_" )* {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength()-1; return IDENTIFICADOR;}

/*                                                         TIPOS DE DATO                                                    */
({Digito})+ {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength()-1; return CONSTANTE_ENTERA;}
({Digito})+ "." ({Digito}*) ([E|e] [+|-]? {Digito}+)* {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CONSTANTE_DECIMAL;}
//[0 | 1 | NULL] {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength()-1; return CONSTANTE_BOOLEANA;}
("'"){String}("'") {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength()-1; return CADENA;}

/*                                                       OPERADORES,PUNTUACION, OTROS                                         */ 
"+" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return SUMA;}
"-" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return RESTA;}
"*" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MULTIPLICACION;}
"/" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DIVISION;}
"%" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MOD;}
"<" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MENOR;}
"<=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MENOR_IGUAL;}
">" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MAYOR;}
">=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return MAYOR_IGUAL;}
"=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ASIGNACION;}
"==" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return IGUALACION;}
"!=" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return DIFERENCIA;}
"&&" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return AND;}
"||" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return OR;}
"!" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return EXCLAMACION;}
";" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PUNTO_COMA;}
"," {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return COMA;}
"." {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PUNTO;}
"[" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CORCHETEA;}
"]" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CORCHETEC;}
"(" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PARENTESISA;}
")" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PARENTESISC;}
"{" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LLAVEA;}
"}" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LLAVEC;}
"[]" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return CORCHETES;}
"()" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return PARENTESIS;}
"{}" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return LLAVES;}
"#" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NUMERAL;}
"@" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ARROBA;}
"##" {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return NUMERALES;}

/*                                                               ERROR                                                            */     
  .  {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ERROR;}
  ("/*") {String} {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ERROR;}
  ("'") {String} {foundLine = yytext(); line = yyline; columnSt = yycolumn; columnNd = yycolumn + yylength() -1; return ERROR;}
