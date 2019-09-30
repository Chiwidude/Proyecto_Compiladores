package SINTAXIS;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;

public class Analizador {
    public String path;
    private Queue<TKEN> TOKENS;
    public TKEN lookAhead;
    public Analizador(String path){
        this.path = path;
        this.TOKENS = new LinkedList<>();
    }

    //region LÉXICO
    public String Analizar() throws IOException {
        Reader reader   = new BufferedReader(new FileReader(this.path));
        Lexer lexer = new Lexer(reader);
        StringBuilder result = new StringBuilder();

        while(true){
            Token token = lexer.yylex();
            if(token == null){
                result.append("FIN LECTURA");
                GenerarOut(result.toString());
                return result.toString();
            }
            switch (token){
                case ERROR:
                    if(lexer.foundLine.contains("'")){
                        result.append( token + ": " + lexer.foundLine + " <- Cadena mal definida linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }else if(lexer.foundLine.contains(("/*")))
                        {
                            result.append( token + ": " + lexer.foundLine + " <- Comentario con */ faltante linea: " + lexer.line + " columna inicio: " +
                                    lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        }
                    else{
                        result.append( token + ": " + lexer.foundLine + " <- Regla no definida linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }

                    break;
                case ERROR_DECIMAL:
                    result.append( token + ": " + lexer.foundLine + " número decimal mal escrito linea: " + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    break;
                case IDENTIFICADOR:
                    if(lexer.foundLine.length() <= 31) {
                        result.append(lexer.foundLine + " es un: " + token + " linea:" + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    } else{
                        result.append(lexer.foundLine + " excede la longitud máxima del identificador,31. El identificador válido sería: "  +
                                lexer.foundLine.substring(0,31)+ " linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    }

                    break;
                case CADENA:
                    result.append(  lexer.foundLine+ " es una: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case ASIGNACION: case SUMA: case RESTA: case MULTIPLICACION: case DIFERENCIA: case DIVISION: case MOD: case MAYOR: case MAYOR_IGUAL:
                case MENOR: case MENOR_IGUAL: case IGUALACION: case CONSTANTE_DECIMAL: case AND: case ARROBA: case COMA: case CORCHETEA: case PUNTO_COMA: case CONSTANTE_BOOLEANA:
                case CONSTANTE_ENTERA: case CORCHETEC: case CORCHETES: case LLAVEC: case PARENTESISC: case EXCLAMACION: case LLAVEA: case LLAVES: case NUMERAL: case NUMERALES:
                case PARENTESIS: case PARENTESISA: case PUNTO: case OR:
                    result.append(  lexer.foundLine+ " es un símbolo de : " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                    default:
                    result.append(  lexer.foundLine+ " palabra reservada: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    TOKENS.add(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
            }

        }


    }
    private void GenerarOut(String result){
        StringBuilder newpath = new StringBuilder(path.replace(".sql",".out"));
        File out = new File(newpath.toString());
        try {
            FileWriter fileWriter = new FileWriter(out);
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion
    public void Sintaxis(){

        AnalisisSintactico();
    }
    private void AnalisisSintactico(){
            lookAhead = TOKENS.poll();
          while(!TOKENS.isEmpty()){
              INITIAL();
          }

    }
    private void INITIAL(){
        if (lookAhead.getName().equals(Token.SELECT) ||lookAhead.getName().equals(Token.INSERT)||lookAhead.getName().equals(Token.DELETE)||lookAhead.getName().equals(Token.UPDATE)||
                lookAhead.getName().equals(Token.CREATE)||lookAhead.getName().equals(Token.ALTER)||lookAhead.getName().equals(Token.DROP)||lookAhead.getName().equals(Token.TRUNCATE)){
                    INICIAL_A();
                    FINAL();
        }else{
                raiseError(lookAhead);
        }
    }
    private final void MATCH(Token Expected){
        if (lookAhead.getName().equals(Expected)){
            lookAhead = TOKENS.remove();
        }else{
            raiseError(Expected);
        }

    }
    private void INICIAL_A (){
        if(lookAhead.getName().equals(Token.SELECT)){
            SELECT();
        }else if(lookAhead.getName().equals(Token.DELETE)){
            DELETE();
        }else if(lookAhead.getName().equals(Token.UPDATE)){
            UPDATE();
        } else if(lookAhead.getName().equals(Token.INSERT)){
            INSERT();
        } else if(lookAhead.getName().equals(Token.DROP)){
            DROP();
        } else if(lookAhead.getName().equals(Token.TRUNCATE)){
            TRUNCATE();
        }else if(lookAhead.getName().equals(Token.CREATE)){
            CREATE();
        } else if(lookAhead.getName().equals(Token.ALTER)){
            ALTER();
        }else raiseError(lookAhead);
    }
    private void FINAL(){
        if(lookAhead.getName().equals(Token.PUNTO_COMA)){
            MATCH(Token.PUNTO_COMA);
            FINAL_A();
        }else if(lookAhead.getName().equals(Token.GO)) {
            MATCH(Token.GO);
        }
          else  raiseError(lookAhead);
    }
    private void FINAL_A(){
            if (lookAhead.getName().equals(Token.GO)){
                    MATCH(Token.GO);
            }
    }
    private void ID(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR)){
            MATCH(Token.IDENTIFICADOR);
        }else if(lookAhead.getName().equals(Token.CORCHETEA)){
            MATCH(Token.CORCHETEA);
            MATCH(Token.IDENTIFICADOR);
            MATCH(Token.CORCHETEC);
        }else raiseError(lookAhead);
    }
    //region ERROR
    public void raiseError(Object error){

    }
    //endregion
    //region SELECT
    private void SELECT(){
        MATCH(Token.SELECT);
        SELECT_A();
        TOP();
        SELECT_COLUMNS();
        FROM_SELECT();
        WHERE();
        GROUP();
        HAVING();
        ORDER();
    }
    private void SELECT_A(){
        if(lookAhead.getName().equals(Token.ALL)){
            MATCH(Token.ALL);
        }else if(lookAhead.getName().equals(Token.DISTINCT)){
            MATCH(Token.DISTINCT);
        }
    }
    private void HAVING(){
        if(lookAhead.getName().equals(Token.HAVING)){
            MATCH(Token.HAVING);
            SearchCondition();
        }
    }
    private void GROUP(){
        if(lookAhead.getName().equals(Token.GROUP)){
            MATCH(Token.GROUP);
            MATCH(Token.BY);
            ExpresionE();
            GROUP_A();
        }
    }
    private void GROUP_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            ExpresionE();
            GROUP_A();
        }
    }
    private void ORDER(){
        if(lookAhead.getName().equals(Token.ORDER)){
            MATCH(Token.ORDER);
            MATCH(Token.BY);
            ExpresionE();
            ORDER_A();
            ORDER_B();
            ORDER_C();
        }
    }
    private void ORDER_A(){
        if(lookAhead.getName().equals(Token.COLLATE)){
            MATCH(Token.COLLATE);
            ID();
        }
    }
    private void ORDER_B(){
        if(lookAhead.getName().equals(Token.ASC)){
            MATCH(Token.ASC);
        }else if(lookAhead.getName().equals(Token.DESC)){
            MATCH(Token.DESC);
        }
    }
    private void ORDER_C(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)|| lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)){
            ExpresionE();
            ORDER_A();
            ORDER_B();
        }
    }
    private void FROM_SELECT(){
      if(lookAhead.getName().equals(Token.FROM)){
          MATCH(Token.FROM);
          Object_3();
          ALIAS();
          JOIN();
          FROM_SELECT_A();
      }
    }
    private void SELECT_COLUMNS(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)||
                lookAhead.getName().equals(Token.NULL)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)){
            Expresion();
            ALIAS();
            SELECT_COLUMNS_A();
        }else raiseError(lookAhead);
    }
    private void SELECT_COLUMNS_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            Expresion();
            ALIAS();
            SELECT_COLUMNS_A();
        }
    }
    private void COLUMN_LIST(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            COLUMN_LIST_A();
            MATCH(Token.PARENTESISC);
        }else raiseError(lookAhead);

    }
    private void COLUMN_LIST_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            COLUMN_LIST_B();
        }else raiseError(lookAhead);
    }
    private void COLUMN_LIST_B(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            ID();
            COLUMN_LIST_B();
        }
    }
    private void FROM_SELECT_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            Object_3();
            ALIAS();
            JOIN();
        }
    }
    private void JOIN(){
        if(lookAhead.getName().equals(Token.INNER)||lookAhead.getName().equals(Token.RIGHT)||lookAhead.getName().equals(Token.LEFT)||
                lookAhead.getName().equals(Token.FULL)){
            TYPE();
            MATCH(Token.JOIN);
            Object_3();
            ALIAS();
            MATCH(Token.ON);
            SearchCondition();
            JOIN();
        }
    }
    private void TYPE(){
        if(lookAhead.getName().equals(Token.INNER)){
            MATCH(Token.INNER);
        } else if(lookAhead.getName().equals(Token.RIGHT)){
            MATCH(Token.RIGHT);
            OUTER();
        }else if(lookAhead.getName().equals(Token.LEFT)){
            MATCH(Token.LEFT);
            OUTER();
        }else if(lookAhead.getName().equals(Token.FULL)){
            MATCH(Token.FULL);
            OUTER();
        }
    }
    private void OUTER(){
        if(lookAhead.getName().equals(Token.OUTER)){
            MATCH(Token.OUTER);
        }
    }
    //endregion
    //region DELETE
    private void DELETE(){
        MATCH(Token.DELETE);
        TOP();
        from();
        DELETE_A();
    }
    private void DELETE_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            Object_3();
            DELETE_B();
            WHERE();
        }else if(lookAhead.getName().equals(Token.OPENQUERY)){
            MATCH(Token.OPENQUERY);
            SERVER();
        }else
            raiseError(lookAhead);
    }
    private void DELETE_B(){
        if(lookAhead.getName().equals(Token.FROM)){
            MATCH(Token.FROM);
            Object_3();
            DELETE_C();
        }
    }
    private void DELETE_C(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            Object_3();
            DELETE_C();
        }
    }
    private void SERVER(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            ID();
            MATCH(Token.COMA);
            MATCH(Token.CADENA);
            MATCH(Token.PARENTESISC);
        }
    }
    private void TOP(){
        if(lookAhead.getName().equals(Token.TOP)){
            MATCH(Token.TOP);
            MATCH(Token.PARENTESISA);
            MATCH(Token.CONSTANTE_ENTERA);
            MATCH(Token.PARENTESISC);
            TOP_A();
        }
    }
    private void TOP_A(){
        if(lookAhead.getName().equals(Token.PERCENT)){
            MATCH(Token.PERCENT);
        }
    }
    private void from(){
        if(lookAhead.getName().equals(Token.FROM)){
            MATCH(Token.FROM);
        }
    }
    //endregion
    //region INSERT
    private void INSERT(){
        MATCH(Token.INSERT);
        TOP();
        INSERT_INTO();
        Object_3();
        INSERT_COLUMN_LIST();
        INSERT_VALUES();
    }
    private void INSERT_INTO(){
        if(lookAhead.getName().equals(Token.INTO)){
            MATCH(Token.INTO);
        }
    }
    private void INSERT_COLUMN_LIST(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            COLUMN_LIST();
        }
    }
    private void INSERT_VALUES(){
        if(lookAhead.getName().equals(Token.VALUES)){
            MATCH(Token.VALUES);
            MATCH(Token.PARENTESISA);
            INSERT_EXPRESION();
            MATCH(Token.PARENTESISC);
            INSERT_VALUES_A();

        }else if(lookAhead.getName().equals(Token.DEFAULT)){
            MATCH(Token.DEFAULT);
            MATCH(Token.VALUES);
        }
    }
    private void INSERT_VALUES_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            MATCH(Token.PARENTESISA);
            INSERT_EXPRESION();
            MATCH(Token.PARENTESISC);
            INSERT_VALUES_A();
        }
    }
    private void INSERT_EXPRESION(){
        if(lookAhead.getName().equals(Token.DEFAULT)|| lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA)||
                lookAhead.getName().equals(Token.NULL)){
            INSERT_EXPRESION_A();
            INSERT_EXPRESION_B();
        }else raiseError(lookAhead);
    }
    private void INSERT_EXPRESION_A(){
        switch (lookAhead.getName()){
            case DEFAULT:
                MATCH(Token.DEFAULT);
                break;
            case CONSTANTE_BOOLEANA:
                MATCH(Token.CONSTANTE_BOOLEANA);
                break;
            case CONSTANTE_ENTERA:
                MATCH(Token.CONSTANTE_ENTERA);
                break;
            case CONSTANTE_DECIMAL:
                MATCH(Token.CONSTANTE_DECIMAL);
                break;
            case CADENA:
                MATCH(Token.CADENA);
                break;
            case NULL:
                MATCH(Token.NULL);
                break;
        }
    }
    private void INSERT_EXPRESION_B(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            INSERT_EXPRESION_A();
            INSERT_EXPRESION_B();
        }
    }
    //endregion
    //region UPDATE
    private void UPDATE(){
        MATCH(Token.UPDATE);
        TOP();
        Object_3();
        MATCH(Token.SET);
        UPDATE_A();
        FROM_UPDATE();
        WHERE();
    }
    private void UPDATE_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            MATCH(Token.ASIGNACION);
            UPDATE_B();
            UPDATE_C();
        }else
            raiseError(lookAhead);
    }
    private void UPDATE_B(){
        if(lookAhead.getName().equals(Token.DEFAULT)){
            MATCH(Token.DEFAULT);
        } else if(lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)||
                lookAhead.getName().equals(Token.NULL)){
            Expresion();
        }else raiseError(lookAhead);
    }
    private void UPDATE_C(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            UPDATE_A();
            UPDATE_C();
        }
    }
    private void FROM_UPDATE(){
        if(lookAhead.getName().equals(Token.FROM)){
            MATCH(Token.FROM);
            Object_3();
            DELETE_C();
        }else raiseError(lookAhead);
    }
    //endregion
    //region DROP
    private void DROP(){
        MATCH(Token.DROP);
        DROP_A();
    }
    private void DROP_A(){
        if (lookAhead.getName().equals(Token.TABLE)){
            DROP_TABLE();
        }else if(lookAhead.getName().equals(Token.USER)){
            DROP_USER();
        } else if(lookAhead.getName().equals(Token.DATABASE)){
            DROP_DATABASE();
        } else if(lookAhead.getName().equals(Token.VIEW)){
            DROP_VIEW();
        } else if(lookAhead.getName().equals(Token.INDEX)){
            DROP_INDEX();
        } else raiseError(lookAhead);
    }
    private void IF_EXISTS(){
        if (lookAhead.getName().equals(Token.IF)){
            MATCH(Token.IF);
            MATCH(Token.EXISTS);
        }
    }
    private void DROP_TABLE(){
        MATCH(Token.TABLE);
        IF_EXISTS();
        Object_3();
        DROP_TABLE_A();
    }
    private void DROP_TABLE_A(){
        if(lookAhead.getName().equals(Token.PUNTO_COMA)){
            MATCH(Token.PUNTO_COMA);
            Object_3();
            DROP_TABLE_A();
        }
    }
    private void DROP_USER(){
        MATCH(Token.USER);
        IF_EXISTS();
        ID();
    }
    private void DROP_DATABASE(){
       MATCH(Token.DATABASE);
       IF_EXISTS();
       ID();
       DROP_DATABASE_A();
    }
    private void DROP_DATABASE_A(){
        if(lookAhead.getName().equals(Token.PUNTO_COMA)){
            MATCH(Token.PUNTO_COMA);
            ID();
            DROP_DATABASE_A();
         }
    }
    private void DROP_VIEW(){
        MATCH(Token.VIEW);
        IF_EXISTS();
        Object_2();
        DROP_VIEW_A();
    }
    private void DROP_VIEW_A(){
        if(lookAhead.getName().equals(Token.PUNTO_COMA)){
            MATCH(Token.PUNTO_COMA);
            Object_2();
            DROP_VIEW_A();
        }
    }
    private void DROP_INDEX(){
         MATCH(Token.INDEX);
         IF_EXISTS();
         DROP_INDEX_A();
         DROP_INDEX_B();
    }
    private void DROP_INDEX_A(){
      if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)) {
          ID();
          MATCH(Token.ON);
          Object_3();
      }else raiseError(lookAhead);
    }
    private void DROP_INDEX_B(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            DROP_INDEX_A();
            DROP_INDEX_B();
        }
    }
    //endregion
    //region CREATE
    private void CREATE(){
        MATCH(Token.CREATE);
        CREATE_A();
    }
    private void CREATE_A(){
      switch (lookAhead.getName()){
          case USER:
              CREATE_USER();
              break;
          case INDEX:
              CREATE_INDEX();
              break;
          case DATABASE:
              CREATE_DATABASE();
              break;
          case TABLE:
              CREATE_TABLE();
              break;
          case VIEW:
              CREATE_VIEW();
              break;
          default:
              raiseError(lookAhead);
              break;
      }
    }
    private void CREATE_TABLE(){

    }
    private void CREATE_DATABASE(){
        MATCH(Token.DATABASE);
        ID();
        CREATE_DATABASE_A();

    }
    private void CREATE_DATABASE_A(){
        if(lookAhead.getName().equals(Token.COLLATE)){
                MATCH(Token.COLLATE);
                ID();
        }
    }
    private void CREATE_INDEX(){
        CREATE_INDEX_A();
        //COLUMN_CONSTR_C();
        MATCH(Token.INDEX);
        ID();
        MATCH(Token.ON);
        Object_3();
        COLUMN_INDEX();
        INCLUDE_INDEX();
        WHERE();
    }
    private void CREATE_INDEX_A(){
        if(lookAhead.getName().equals(Token.UNIQUE)){
            MATCH(Token.UNIQUE);
        }
    }
    private void COLUMN_INDEX(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            COLUMN_INDEX_A();
            MATCH(Token.PARENTESISC);
        }else raiseError(lookAhead);
    }
    private void COLUMN_INDEX_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            ORDER_B();
            COLUMN_INDEX_B();
        }else raiseError(lookAhead);
    }
    private void COLUMN_INDEX_B(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            ID();
            ORDER_B();
            COLUMN_INDEX_B();
        }
    }
    private void INCLUDE_INDEX(){
        if(lookAhead.getName().equals(Token.INCLUDE)){
            MATCH(Token.INCLUDE);
            COLUMN_LIST();
        }
    }
    private void CREATE_USER(){
        MATCH(Token.USER);
        MATCH(Token.IDENTIFICADOR);
    }
    private void CREATE_VIEW(){
        MATCH(Token.VIEW);
        Object_2();
        MATCH(Token.AS);
        SELECT();
    }
    //endregion
    //region ALTER
    private void ALTER(){
        MATCH(Token.ALTER);
        if(lookAhead.getName().equals(Token.TABLE)||lookAhead.getName().equals(Token.USER)||lookAhead.getName().equals(Token.DATABASE)||lookAhead.getName().equals(Token.VIEW)) {
            ALTER_A();
        } else raiseError(lookAhead);

    }
    private void ALTER_A(){
        if(lookAhead.getName().equals(Token.TABLE)){
            ALTER_TABLE();
        }else if(lookAhead.getName().equals(Token.USER)){
            ALTER_USER();
        }else if(lookAhead.getName().equals(Token.DATABASE)){
            ALTER_DATABASE();
        }else if(lookAhead.getName().equals(Token.VIEW)){
            ALTER_VIEW();
        }
    }
    private void ALTER_TABLE(){
        MATCH(Token.TABLE);
        Object_3();
        ALTER_TABLE_A();
    }
    private void ALTER_TABLE_A(){
        if(lookAhead.getName().equals(Token.ALTER)){
            ALTER_COLUMN();
        }else if(lookAhead.getName().equals(Token.ADD)){
            MATCH(Token.ADD);
            //CNC();
            //CNCA();
        }else if(lookAhead.getName().equals(Token.DROP)){
            ALTER_TABLE_DROP();
        }
        else raiseError(lookAhead);
    }
    private void ALTER_COLUMN(){
       if(lookAhead.getName().equals(Token.ALTER)){
           MATCH(Token.ALTER);
           MATCH(Token.COLUMN);
           ID();
           ALTER_COLUMN_C();
       }

    }
    private void ALTER_COLUMN_A(){
        if(lookAhead.getName().equals(Token.VARCHAR) || lookAhead.getName().equals(Token.FLOAT) || lookAhead.getName().equals(Token.DATE) || lookAhead.getName().equals(Token.BIT)
                || lookAhead.getName().equals(Token.DOUBLE) || lookAhead.getName().equals(Token.CHAR) || lookAhead.getName().equals(Token.CURSOR) || lookAhead.getName().equals(Token.NCHAR)
                || lookAhead.getName().equals(Token.REAL) || lookAhead.getName().equals(Token.TIME) || lookAhead.getName().equals(Token.INT) || lookAhead.getName().equals(Token.INTEGER)
                || lookAhead.getName().equals(Token.DECIMAL) || lookAhead.getName().equals(Token.SMALLINT) || lookAhead.getName().equals(Token.NUMERIC)){
            ALTER_COLUMN_B();
            //COLUMN_DEFINITION_A();
            //COLUMN_DEFINITION_G();
        }
    }
    private void ALTER_COLUMN_B(){
        if(lookAhead.getName().equals(Token.VARCHAR) || lookAhead.getName().equals(Token.FLOAT) || lookAhead.getName().equals(Token.DATE) || lookAhead.getName().equals(Token.BIT)
                || lookAhead.getName().equals(Token.DOUBLE) || lookAhead.getName().equals(Token.CHAR) || lookAhead.getName().equals(Token.CURSOR) || lookAhead.getName().equals(Token.NCHAR)
                || lookAhead.getName().equals(Token.REAL) || lookAhead.getName().equals(Token.TIME) || lookAhead.getName().equals(Token.INT) || lookAhead.getName().equals(Token.INTEGER)
                || lookAhead.getName().equals(Token.DECIMAL) || lookAhead.getName().equals(Token.SMALLINT) || lookAhead.getName().equals(Token.NUMERIC)){
                    TIPO_DATO();
        }
    }
    private void ALTER_COLUMN_C(){
        if(lookAhead.getName().equals(Token.VARCHAR) || lookAhead.getName().equals(Token.FLOAT) || lookAhead.getName().equals(Token.DATE) || lookAhead.getName().equals(Token.BIT)
                || lookAhead.getName().equals(Token.DOUBLE) || lookAhead.getName().equals(Token.CHAR) || lookAhead.getName().equals(Token.CURSOR) || lookAhead.getName().equals(Token.NCHAR)
                || lookAhead.getName().equals(Token.REAL) || lookAhead.getName().equals(Token.TIME) || lookAhead.getName().equals(Token.INT) || lookAhead.getName().equals(Token.INTEGER)
                || lookAhead.getName().equals(Token.DECIMAL) || lookAhead.getName().equals(Token.SMALLINT) || lookAhead.getName().equals(Token.NUMERIC)){
                ALTER_COLUMN_A();
        }else if(lookAhead.getName().equals(Token.ADD)||lookAhead.getName().equals(Token.DROP)){
                ALTER_COLUMN_D();
                ALTER_COLUMN_E();
        } else raiseError(lookAhead);
    }
    private void ALTER_COLUMN_D(){
        if(lookAhead.getName().equals(Token.ADD)){
            MATCH(Token.ADD);
        }else if(lookAhead.getName().equals(Token.DROP)){
            MATCH(Token.DROP);
        }
    }
    private void ALTER_COLUMN_E(){
        if(lookAhead.getName().equals(Token.ROWGUIDCOL)){
            MATCH(Token.ROWGUIDCOL);
        }else if(lookAhead.getName().equals(Token.NOT)){
            MATCH(Token.NOT);
            MATCH(Token.FOR);
            MATCH(Token.REPLICATION);
        }else raiseError(lookAhead);
    }
    private void ALTER_TABLE_DROP(){
        MATCH(Token.DROP);
        ALTER_TABLE_DROP_A();
        ALTER_TABLE_DROP_C();
    }
    private void ALTER_TABLE_DROP_A(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)|| lookAhead.getName().equals(Token.IF)){
            ALTER_TABLE_DROP_B();
            IF_EXISTS();
            ID();
        }else if (lookAhead.getName().equals(Token.COLUMN)){
            MATCH(Token.COLUMN);
            IF_EXISTS();
            ID();
        }else raiseError(lookAhead);
    }
    private void ALTER_TABLE_DROP_B(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)){
            MATCH(Token.CONSTRAINT);
        }
    }
    private void ALTER_TABLE_DROP_C(){
       if(lookAhead.getName().equals(Token.COMA)){
           MATCH(Token.COMA);
           ALTER_TABLE_DROP_D();
           ALTER_TABLE_DROP_C();
       }
    }
    private void ALTER_TABLE_DROP_D(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
        }else if(lookAhead.getName().equals(Token.COLUMN)|| lookAhead.getName().equals(Token.CONSTRAINT)||lookAhead.getName().equals(Token.IF)){
            ALTER_TABLE_DROP_A();
        }
    }
    private void ALTER_USER(){
        MATCH(Token.USER);
        ID();
    }
    private void ALTER_DATABASE(){
        MATCH(Token.DATABASE);
        ALTER_DATABASE_A();
        ALTER_DATABASE_B();
    }
    private void ALTER_DATABASE_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
        }else if(lookAhead.getName().equals(Token.CURRENT)){
            MATCH(Token.CURRENT);
        }
    }
    private void ALTER_DATABASE_B(){
        if(lookAhead.getName().equals(Token.COLLATE)){
            MATCH(Token.COLLATE);
            ID();
        }else if(lookAhead.getName().equals(Token.SET)){
            MATCH(Token.SET);
            MATCH(Token.ROLLBACK);
            MATCH(Token.IMMEDIATE);
        }
    }
    private void ALTER_VIEW(){
        MATCH(Token.VIEW);
        Object_2();
        COLUMN_LIST();
        MATCH(Token.AS);
        SELECT();
    }
    //endregion
    //region TRUNCATE
    private void TRUNCATE(){
        MATCH(Token.TRUNCATE);
        MATCH(Token.TABLE);
        Object_3();
    }
    //endregion}
    //region WHERE
    private void WHERE(){
     if(lookAhead.getName().equals(Token.WHERE)) {
         MATCH(Token.WHERE);
         SearchCondition();
     }
    }
    //endregion
    //region OBJETOS
    private void Object_3() {
        ID();
        Object_3_A();
    }
    private void Object_3_A(){
        if (lookAhead.getName().equals(Token.PUNTO)){
                MATCH(Token.PUNTO);
                Object_2();
        }
    }
    private void Object_2(){
        ID();
        Object_2_A();
    }
    private void Object_2_A(){
        if (lookAhead.getName().equals(Token.PUNTO)){
            MATCH(Token.PUNTO);
            ID();
        }
    }
    private void Object_4(){
        ID();
        Object_4_A();
    }
    private void Object_4_A(){
        if (lookAhead.getName().equals(Token.PUNTO)){
            MATCH(Token.PUNTO);
            Object_3();
        }
    }
    private void ALIAS(){
        if (lookAhead.getName().equals(Token.AS)){
            MATCH(Token.AS);
            ALIAS_A();
        }else if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)||lookAhead.getName().equals(Token.CADENA)){
            ALIAS_A();
        }
    }
    private void ALIAS_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
        }else if(lookAhead.getName().equals(Token.CADENA)){
            MATCH(Token.CADENA);
        }else raiseError(lookAhead);
    }
    //endregion
    //region CONDICIONES
    private void Expresion(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)||
                    lookAhead.getName().equals(Token.NULL)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)) {
            ExpresionB();
            ExpresionA();
        }else{
            raiseError(lookAhead);
        }
    }
    private void Expresiones(){
        if (lookAhead.getName().equals(Token.COMA)) {
            MATCH(Token.COMA);
            Expresion();
            Expresiones();
        }
    }
    private void ExpresionA(){
        if (lookAhead.getName().equals(Token.SUMA)) {
            MATCH(Token.SUMA);
            ExpresionB();
            ExpresionA();

        }else if (lookAhead.getName().equals(Token.RESTA)) {
            MATCH(Token.RESTA);
            ExpresionB();
            ExpresionA();
        }
    }
    private void ExpresionB(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals( Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)) {
            ExpresionD();
            ExpresionC();
        }else{
            raiseError(lookAhead);
        }
    }
    private void ExpresionC(){
        if (lookAhead.getName().equals(Token.MULTIPLICACION)) {
            MATCH(Token.MULTIPLICACION);
            ExpresionD();
            ExpresionC();
        }else if (lookAhead.getName().equals(Token.DIVISION)) {
            MATCH(Token.DIVISION);
            ExpresionD();
            ExpresionC();
        }
    }
    private void ExpresionD(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR)|| lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)) {
            ExpresionE();
        }else if (lookAhead.getName().equals(Token.PARENTESISA)) {
            MATCH(Token.PARENTESISA);
            Expresion();
            PredicadoB();
            MATCH(Token.PARENTESISC);
        }else{
            raiseError(lookAhead);
        }
    }
    private void ExpresionE(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA)) {
            Object_4();
        }else if (lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA) || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals( Token.ARROBA)) {
            CONSTANTS();
        }else if(lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)){
                switch (lookAhead.getName()){
                    case AVG:
                        MATCH(Token.AVG);
                        break;
                    case COUNT:
                        MATCH(Token.COUNT);
                        break;
                    case MIN:
                        MATCH(Token.MIN);
                        break;
                    case MAX:
                        MATCH(Token.MAX);
                        break;
                    case SUM:
                        MATCH(Token.SUM);
                        break;
                }
                MATCH(Token.PARENTESISA);
                Expresion_F();
        }
    }
    private void Expresion_F(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)|| lookAhead.getName().equals(Token.CORCHETEA)){
            Object_4();
            MATCH(Token.PARENTESISC);
        }else if(lookAhead.getName().equals(Token.CONSTANTE_ENTERA)){
            MATCH(Token.CONSTANTE_ENTERA);
            MATCH(Token.PARENTESISC);
        }else raiseError(lookAhead);
    }
    private void NOT(){
        if (lookAhead.getName().equals(Token.NOT)) {
            MATCH(Token.NOT);
        }
    }
    private void Predicado(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals( Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)) {
            Expresion();
            PredicadoA();
        }else{
            raiseError(lookAhead);
        }
    }
    private void PredicadoA(){
        if (lookAhead.getName().equals(Token.ASIGNACION) || lookAhead.getName().equals(Token.DIFERENCIA)
                ||lookAhead.getName().equals(Token.MAYOR) || lookAhead.getName().equals(Token.MAYOR_IGUAL)
                ||lookAhead.getName().equals(Token.MENOR) || lookAhead.getName().equals(Token.MENOR_IGUAL)) {
            Boolean_CONSTS();
            Expresion();
        }else if (lookAhead.getName().equals(Token.NOT)) {
            NOT();
            PredicadoB();

        }else if (lookAhead.getName().equals(Token.BETWEEN) || lookAhead.getName().equals(Token.IN) || lookAhead.getName().equals(Token.LIKE)) {
            PredicadoB();

        }else if (lookAhead.getName().equals(Token.IS)) {
            MATCH(Token.IS);
            NOT();
            MATCH(Token.NULL);


        }else{
            raiseError(lookAhead);
        }
    }
    private void PredicadoB(){
        if (lookAhead.getName().equals(Token.BETWEEN)) {
            MATCH(Token.BETWEEN);
            Expresion();
            MATCH(Token.AND);
            Expresion();

        }else if (lookAhead.getName().equals(Token.IN)) {
            MATCH(Token.IN);
            MATCH(Token.PARENTESISA);
            Expresion();
            Expresiones();
            MATCH(Token.PARENTESISC);

        }else if (lookAhead.getName().equals(Token.LIKE)) {
            MATCH(Token.LIKE);
            Expresion();

        }else{
            raiseError(lookAhead);
        }
    }

//endregion
    // region SEARCH
    private void SearchCondition(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)
                ||lookAhead.getName().equals(Token.AVG) || lookAhead.getName().equals(Token.COUNT) || lookAhead.getName().equals(Token.MIN)
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)) {
            Predicado();
            SearchConditionA();
        }else if(lookAhead.getName().equals(Token.NOT)){
            MATCH(Token.NOT);
            Predicado();
            SearchConditionA();

        }else{
            raiseError(lookAhead);
        }
    }
    private void SearchConditionA(){
        if (lookAhead.getName().equals(Token.AND)) {
            MATCH(Token.AND);
            SearchCondition();
            SearchConditionA();
        }else if(lookAhead.getName().equals(Token.OR)){
            MATCH(Token.OR);
            SearchCondition();
            SearchConditionA();
        }
    }
    private void CONSTANTS(){
        if(lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)){
            MATCH(Token.BIT);
        }else if(lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)){
            MATCH(Token.CONSTANTE_DECIMAL);
        }else if(lookAhead.getName().equals(Token.CONSTANTE_ENTERA)){
            MATCH(Token.CONSTANTE_ENTERA);
        }else if(lookAhead.getName().equals(Token.CADENA)){
            MATCH(Token.CADENA);
        }else if(lookAhead.getName().equals(Token.ARROBA)){
            VARIABLE();
        } else if(lookAhead.getName().equals(Token.NULL)){
            MATCH(Token.NULL);
        }
    }
    private void VARIABLE(){
        MATCH(Token.ARROBA);
        MATCH(Token.IDENTIFICADOR);
    }
    private void Boolean_CONSTS(){
        if(lookAhead.getName().equals(Token.ASIGNACION)){
            MATCH(Token.ASIGNACION);
        }else if(lookAhead.getName().equals(Token.DIFERENCIA)){
            MATCH(Token.DIFERENCIA);
        } else if(lookAhead.getName().equals(Token.MAYOR)){
            MATCH(Token.MAYOR);
        } else if(lookAhead.getName().equals(Token.MAYOR_IGUAL)){
            MATCH(Token.MAYOR_IGUAL);
        }else if(lookAhead.getName().equals(Token.MENOR)){
            MATCH(Token.MENOR);
        }else if(lookAhead.getName().equals(Token.MENOR_IGUAL)){
            MATCH(Token.MENOR_IGUAL);
        }
    }
    //endregion
    //region DATA_TYPE
    private void TIPO_DATO(){
        TIPO_DATO_D();
        TIPO_DATO_B();
    }
    private void TIPO_DATO_A(){
        switch(lookAhead.getName()){
            case VARCHAR:
                    MATCH(Token.VARCHAR);
                break;
            case FLOAT:
                    MATCH(Token.FLOAT);
                break;
            case DATE:
                    MATCH(Token.DATE);
                break;
            case BIT:
                    MATCH(Token.BIT);
                break;
            case DOUBLE:
                    MATCH(Token.DOUBLE);
                break;
            case CHAR:
                    MATCH(Token.CHAR);
                break;
            case CURSOR:
                    MATCH(Token.CURSOR);
                break;
            case NCHAR:
                    MATCH(Token.NCHAR);
                break;
            case REAL:
                    MATCH(Token.REAL);
                break;
            case TIME:
                    MATCH(Token.TIME);
                break;
            case INT:
                    MATCH(Token.INT);
                break;
            case INTEGER:
                    MATCH(Token.INTEGER);
                break;
            case DECIMAL:
                    MATCH(Token.DECIMAL);
                break;
            case SMALLINT:
                    MATCH(Token.SMALLINT);
                break;
            case NUMERIC:
                    MATCH(Token.NUMERIC);
                break;
        }
    }
    private void TIPO_DATO_B(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            MATCH(Token.CONSTANTE_ENTERA);
            TIPO_DATO_C();
            MATCH(Token.PARENTESISC);
        }
    }
    private void TIPO_DATO_C(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            MATCH(Token.CONSTANTE_ENTERA);
        }
    }
    private void TIPO_DATO_D(){
        if(lookAhead.getName().equals(Token.VARCHAR) || lookAhead.getName().equals(Token.FLOAT) || lookAhead.getName().equals(Token.DATE) || lookAhead.getName().equals(Token.BIT)
        || lookAhead.getName().equals(Token.DOUBLE) || lookAhead.getName().equals(Token.CHAR) || lookAhead.getName().equals(Token.CURSOR) || lookAhead.getName().equals(Token.NCHAR)
        || lookAhead.getName().equals(Token.REAL) || lookAhead.getName().equals(Token.TIME) || lookAhead.getName().equals(Token.INT) || lookAhead.getName().equals(Token.INTEGER)
        || lookAhead.getName().equals(Token.DECIMAL) || lookAhead.getName().equals(Token.SMALLINT) || lookAhead.getName().equals(Token.NUMERIC)){
            TIPO_DATO_A();
        } else if(lookAhead.getName().equals(Token.CORCHETEA)){
            MATCH(Token.CORCHETEA);
            TIPO_DATO_A();
            MATCH(Token.CORCHETEC);
        }
    }
    //endregion




}
