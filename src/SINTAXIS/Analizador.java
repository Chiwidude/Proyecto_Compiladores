package SINTAXIS;
import java.io.File;
import java.io.*;
import java.util.*;

public class Analizador {
    public String path;
    public StringBuilder errors;
    private Queue<TKEN> TOKENS;
    private boolean flag;
    public TKEN lookAhead;
    public Analizador(String path){
        this.path = path;
        this.TOKENS = new LinkedList<>();
        this.errors = new StringBuilder();
        flag = false;
    }

    //region LÉXICO
    public String Analizar() throws IOException {
        boolean go = false;
        Stack<TKEN> temp = new Stack<>();
        Stack<TKEN> temp1 = new Stack<>();
        Reader reader   = new BufferedReader(new FileReader(this.path));
        Lexer lexer = new Lexer(reader);
        StringBuilder result = new StringBuilder();
        while(true){
            Token token = lexer.yylex();
            if(!temp.isEmpty()){
                if(temp.peek().getName().equals(Token.PUNTO_COMA)||temp.peek().getName().equals(Token.GO)){
                    if(token != null){
                        if(token.equals(Token.GO)){
                            go = true;
                            temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                        }
                    }

                        boolean found = false;
                        while(!temp.isEmpty()&&!found){
                            TKEN tmp = temp.pop();
                            if(tmp.getName().equals(Token.ERROR)||tmp.getName().equals(Token.ERROR_DECIMAL)){
                                found = true;
                            }else if(tmp.getName().equals(Token.IDENTIFICADOR)){
                                if(tmp.getValue().trim().equals("ERROR_CADENA")){
                                    found = true;
                                }else{
                                    temp1.push(tmp);
                                }
                            }else{
                                temp1.push(tmp);
                            }
                        }
                        if(!found){
                            while(!temp1.isEmpty()){
                                TOKENS.add(temp1.pop());
                            }
                        }else{
                            temp.clear();
                            temp1.clear();
                        }

                }
            }
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
                        temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    }else if(lexer.foundLine.contains(("/*")))
                        {
                            result.append( token + ": " + lexer.foundLine + " <- Comentario con */ faltante linea: " + lexer.line + " columna inicio: " +
                                    lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                            temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                        }
                    else{
                        result.append( token + ": " + lexer.foundLine + " <- Regla no definida linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    }

                    break;
                case ERROR_DECIMAL:
                    result.append( token + ": " + lexer.foundLine + " número decimal mal escrito linea: " + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case IDENTIFICADOR:
                    if(lexer.foundLine.length() <= 31) {
                        result.append(lexer.foundLine + " es un: " + token + " linea:" + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    } else{
                        result.append(lexer.foundLine + " excede la longitud máxima del identificador,31. El identificador válido sería: "  +
                                lexer.foundLine.substring(0,31)+ " linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        temp.push(new TKEN(token,"ERROR_CADENA", lexer.columnSt,lexer.columnNd,lexer.line));
                    }

                    break;
                case CADENA:
                    result.append(  lexer.foundLine+ " es una: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case ASIGNACION: case SUMA: case RESTA: case MULTIPLICACION: case DIFERENCIA: case DIVISION: case MOD: case MAYOR: case MAYOR_IGUAL:
                case MENOR: case MENOR_IGUAL: case IGUALACION: case CONSTANTE_DECIMAL: case AND: case ARROBA: case COMA: case CORCHETEA: case PUNTO_COMA: case CONSTANTE_BOOLEANA:
                case CONSTANTE_ENTERA: case CORCHETEC: case CORCHETES: case LLAVEC: case PARENTESISC: case EXCLAMACION: case LLAVEA: case LLAVES: case NUMERAL: case NUMERALES:
                case PARENTESIS: case PARENTESISA: case PUNTO: case OR:
                    result.append(  lexer.foundLine+ " es un símbolo de : " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                    default:
                        if(token.equals(Token.GO)){
                            if(go){
                                go = false;
                                result.append(  lexer.foundLine+ " palabra reservada: " + token +" linea:" + lexer.line + " columna inicio: " +
                                        lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                            }else{
                                result.append(  lexer.foundLine+ " palabra reservada: " + token +" linea:" + lexer.line + " columna inicio: " +
                                        lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                                temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                            }
                        }else{
                            result.append(  lexer.foundLine+ " palabra reservada: " + token +" linea:" + lexer.line + " columna inicio: " +
                                    lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                            temp.push(new TKEN(token,lexer.foundLine, lexer.columnSt,lexer.columnNd,lexer.line));
                        }

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
    public String returnError(){
        String[] lines = errors.toString().split(System.lineSeparator());
        StringBuilder strc = new StringBuilder();
    if(!errors.toString().isEmpty()){
        for(String line : lines){
            if(line.length()>150){
                String temp = line.substring(0,150);
                int lastSpace = temp.lastIndexOf(" ");
                String correct = temp.substring(0,lastSpace);
                String other = temp.substring(lastSpace);
                String rest = line.substring(150);
                strc.append(correct);
                strc.append(System.lineSeparator());
                strc.append(other + rest);
                strc.append(System.lineSeparator());
            }else{
                strc.append(line);
                strc.append(System.lineSeparator());
            }
        }
    }   else{
        strc.append(errors.toString());
    }


        return strc.toString();
    }
    private void INITIAL(){
        if (lookAhead.getName().equals(Token.SELECT) ||lookAhead.getName().equals(Token.INSERT)||lookAhead.getName().equals(Token.DELETE)||lookAhead.getName().equals(Token.UPDATE)||
                lookAhead.getName().equals(Token.CREATE)||lookAhead.getName().equals(Token.ALTER)||lookAhead.getName().equals(Token.DROP)||lookAhead.getName().equals(Token.TRUNCATE)){
                    INICIAL_A();
                    FINAL();
        }else if((lookAhead.getName().equals(Token.PUNTO_COMA)||lookAhead.getName().equals(Token.GO))&& flag) {
            MATCH(Token.PUNTO_COMA);
            flag = false;
        }else{
                List<Object> errors = new LinkedList<>();
                errors.add(lookAhead);
                errors.add(Token.SELECT);
                errors.add(Token.INSERT);
                errors.add(Token.DELETE);
                errors.add(Token.UPDATE);
                errors.add(Token.CREATE);
                errors.add(Token.ALTER);
                errors.add(Token.DROP);
                errors.add(Token.TRUNCATE);
               raiseError(errors);

        }
    }
    private final void MATCH(Token Expected){
        if (lookAhead.getName().equals(Expected)){
            lookAhead = TOKENS.poll();
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Expected);
            raiseError(errors);
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
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.SELECT);
            errors.add(Token.INSERT);
            errors.add(Token.DELETE);
            errors.add(Token.UPDATE);
            errors.add(Token.CREATE);
            errors.add(Token.ALTER);
            errors.add(Token.DROP);
            errors.add(Token.TRUNCATE);
            raiseError(errors);
        }
    }
    private void FINAL(){
        if(lookAhead.getName().equals(Token.PUNTO_COMA)){
            MATCH(Token.PUNTO_COMA);
            FINAL_A();
            flag = false;
        }else if(lookAhead.getName().equals(Token.GO)) {
            MATCH(Token.GO);
            flag = false;
        }
          else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PUNTO_COMA);
            errors.add(Token.GO);
            raiseError(errors);
        }
    }
    private void FINAL_A(){
        if(lookAhead != null){
            if (lookAhead.getName().equals(Token.GO)){
                MATCH(Token.GO);
            }
        }
    }
    private void ID(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR)){
            MATCH(Token.IDENTIFICADOR);
        }else if(lookAhead.getName().equals(Token.CORCHETEA)){
            MATCH(Token.CORCHETEA);
            MATCH(Token.IDENTIFICADOR);
            MATCH(Token.CORCHETEC);
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            raiseError(errors);
        }
    }
    //region ERROR
    public void raiseError(List<Object> error){
        if(!flag){
            TKEN tken = (TKEN) error.remove(0);
            if(tken.getColumnBegin() == tken.getColumnEnd()){
                errors.append("Se ha encontrado un error en linea: " + tken.getLine() + " en la columna: " + tken.getColumnBegin());
                errors.append(" Se encontró: " + tken.getName() + " se esperaba: ");
            }else{
                errors.append("Se ha encontrado un error en linea: " + tken.getLine() + " entre las columnas " + tken.getColumnBegin() +" y " + tken.getColumnEnd());
                errors.append(" Se encontró: " + tken.getName() + " se esperaba: ");
            }

            while(!error.isEmpty()){
                Token err = (Token) error.remove(0);
                if(error.isEmpty()){
                    errors.append(err);
                }else errors.append(err + " | ");
            }
            errors.append(System.lineSeparator());
            raiseQueue();
            flag = true;
        }
    }
    private void raiseQueue(){
        boolean isPYC = false;
        while(!isPYC && !TOKENS.isEmpty()){
            lookAhead = TOKENS.poll();
            if(lookAhead.getName().equals(Token.PUNTO_COMA) || lookAhead.getName().equals(Token.GO)){
                isPYC = true;
            }
        }
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
            Expresion();
            GROUP_A();
        }
    }
    private void GROUP_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            Expresion();
            GROUP_A();
        }
    }
    private void ORDER(){
        if(lookAhead.getName().equals(Token.ORDER)){
            MATCH(Token.ORDER);
            MATCH(Token.BY);
            Expresion();
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
            Expresion();
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
        } else if(lookAhead.getName().equals(Token.MULTIPLICACION)){
            MATCH(Token.MULTIPLICACION);
            SELECT_COLUMNS_A();
        } else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            raiseError(errors);
        }
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PARENTESISA);
            raiseError(errors);
        }

    }
    private void COLUMN_LIST_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            COLUMN_LIST_B();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            raiseError(errors);
        }
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
                lookAhead.getName().equals(Token.FULL)||lookAhead.getName().equals(Token.JOIN)){
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.OPENQUERY);
            raiseError(errors);
        }

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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.DEFAULT);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.NULL);
            raiseError(errors);
        }
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            raiseError(errors);
        }
    }
    private void UPDATE_B(){
        if(lookAhead.getName().equals(Token.DEFAULT)){
            MATCH(Token.DEFAULT);
        } else if(lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA) || lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA)
                || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals(Token.ARROBA)|| lookAhead.getName().equals(Token.PARENTESISA)||
                lookAhead.getName().equals(Token.NULL)){
            Expresion();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.PARENTESISA);
            errors.add(Token.NULL);
            raiseError(errors);
        }
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
        }
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
        } else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.TABLE);
            errors.add(Token.USER);
            errors.add(Token.DATABASE);
            errors.add(Token.VIEW);
            errors.add(Token.INDEX);
            raiseError(errors);
        }
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
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
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
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
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
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
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
      }else {
          List<Object> errors = new LinkedList<>();
          errors.add(lookAhead);
          errors.add(Token.IDENTIFICADOR);
          errors.add(Token.CORCHETEA);
          raiseError(errors);
      }
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
          case INDEX: case UNIQUE: case NONCLUSTERED: case CLUSTERED:
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
              List<Object> errors = new LinkedList<>();
              errors.add(lookAhead);
              errors.add(Token.USER);
              errors.add(Token.INDEX);
              errors.add(Token.DATABASE);
              errors.add(Token.TABLE);
              errors.add(Token.VIEW);
              raiseError(errors);
              break;
      }
    }
    private void CREATE_TABLE(){
        MATCH(Token.TABLE);
        Object_3();
        MATCH(Token.PARENTESISA);
        C_N_C();
        C_N_C_A();
        MATCH(Token.PARENTESISC);
    }
    private void NOT_F_R(){
        if(lookAhead.getName().equals(Token.NOT)){
            if(TOKENS.peek().getName().equals(Token.FOR)){
                MATCH(Token.NOT);
                MATCH(Token.FOR);
                MATCH(Token.REPLICATION);
            }
        }
    }
    private void C_N_C(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            COLUMN_DEFINITION();
        }else if(lookAhead.getName().equals(Token.CONSTRAINT)||lookAhead.getName().equals(Token.PRIMARY)||
        lookAhead.getName().equals(Token.UNIQUE)||lookAhead.getName().equals(Token.FOREIGN)||lookAhead.getName().equals(Token.CHECK)){
            TABLE_CONSTRAINT();
        }else if(lookAhead.getName().equals(Token.INDEX)){
            TABLE_INDEX();
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTRAINT);
            errors.add(Token.PRIMARY);
            errors.add(Token.UNIQUE);
            errors.add(Token.FOREIGN);
            errors.add(Token.CHECK);
            errors.add(Token.INDEX);
            raiseError(errors);
        }
    }
    private void C_N_C_A(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            C_N_C();
            C_N_C_A();
        }
    }
    private void COLUMN_DEFINITION(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            TIPO_DATO();
            COLUMN_DEF_A();
            COLUMN_DEF_B();
            COLUMN_DEF_E();
            NOT_F_R();
            COLUMN_DEF_G();
            COLUMN_DEF_H();
            COLUMN_CONSTRAINT();
        }
    }
    private void COLUMN_DEF_A(){
        if(lookAhead.getName().equals(Token.COLLATE)){
            MATCH(Token.COLLATE);
            ID();
        }
    }
    private void COLUMN_DEF_B(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)){
            MATCH(Token.CONSTRAINT);
            ID();
            COLUMN_DEF_C();
        }
    }
    private void COLUMN_DEF_C(){
      if(lookAhead.getName().equals(Token.DEFAULT)){
            MATCH(Token.DEFAULT);
            COLUMN_DEF_D();
      }
    }
    private void COLUMN_DEF_D(){
        if(lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA) || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA)){
            CONSTANTS();
        }
    }
    private void COLUMN_DEF_E(){
        if(lookAhead.getName().equals(Token.IDENTITY)){
            MATCH(Token.IDENTITY);
            COLUMN_DEF_F();
        }
    }
    private void COLUMN_DEF_F(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            MATCH(Token.CONSTANTE_ENTERA);
            MATCH(Token.COMA);
            MATCH(Token.CONSTANTE_ENTERA);
            MATCH(Token.PARENTESISC);
        }
    }
    private void COLUMN_DEF_G(){
        if(lookAhead.getName().equals(Token.NULL)){
            MATCH(Token.NULL);
        }else if(lookAhead.getName().equals(Token.NOT)){
                MATCH(Token.NOT);
                MATCH(Token.NULL);
        }
    }
    private void COLUMN_DEF_H(){
        if (lookAhead.getName().equals(Token.ROWGUIDCOL)) {
            MATCH(Token.ROWGUIDCOL);
        }
    }
    private void COLUMN_CONSTRAINT(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)||lookAhead.getName().equals(Token.PRIMARY)||
        lookAhead.getName().equals(Token.UNIQUE)||lookAhead.getName().equals(Token.FOREIGN)||lookAhead.getName().equals(Token.REFERENCES)||lookAhead.getName().equals(Token.CHECK)){
            COLUMN_CONSTR_A();
            COLUMN_CONSTR_B();
            COLUMN_CONSTRAINT();
        }
    }
    private void COLUMN_CONSTR_A(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)){
            MATCH(Token.CONSTRAINT);
            ID();
        }
    }
    private void COLUMN_CONSTR_B(){
        if (lookAhead.getName().equals(Token.PRIMARY)){
            MATCH(Token.PRIMARY);
            MATCH(Token.KEY);
            COLUMN_CONSTR_C();
        }else if(lookAhead.getName().equals(Token.UNIQUE)){
            MATCH(Token.UNIQUE);
            COLUMN_CONSTR_C();
        }else if(lookAhead.getName().equals(Token.CHECK)){
            MATCH(Token.CHECK);
            NOT_F_R();
            MATCH(Token.PARENTESISA);
            SearchCondition();
            MATCH(Token.PARENTESISC);
        }else if(lookAhead.getName().equals(Token.FOREIGN)){
            COLUMN_CONSTR_D();
            MATCH(Token.REFERENCES);
            Object_2();
            COLUMN_CONSTR_E();
            COLUMN_CONSTR_F();
            COLUMN_CONSTR_G();
            NOT_F_R();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PRIMARY);
            errors.add(Token.UNIQUE);
            errors.add(Token.CHECK);
            errors.add(Token.FOREIGN);
            raiseError(errors);
        }
    }
    private void COLUMN_CONSTR_C(){
        if(lookAhead.getName().equals(Token.CLUSTERED)){
            MATCH(Token.CLUSTERED);
        }else if(lookAhead.getName().equals(Token.NONCLUSTERED)){
            MATCH(Token.NONCLUSTERED);
        }
    }
    private void COLUMN_CONSTR_D(){
        if(lookAhead.getName().equals(Token.FOREIGN)){
            MATCH(Token.FOREIGN);
            MATCH(Token.KEY);
        }
    }
    private void COLUMN_CONSTR_E(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            MATCH(Token.PARENTESISA);
            ID();
            MATCH(Token.PARENTESISC);
        }
    }
    private void COLUMN_CONSTR_F(){
        if(lookAhead.getName().equals(Token.ON)){
            MATCH(Token.ON);
            MATCH(Token.DELETE);
            COLUMN_CONSTR_I();
        }
    }
    private void COLUMN_CONSTR_G(){
        if(lookAhead.getName().equals(Token.ON)){
            MATCH(Token.ON);
            MATCH(Token.UPDATE);
            COLUMN_CONSTR_I();
        }
    }
    private void COLUMN_CONSTR_H(){
        if(lookAhead.getName().equals(Token.NULL)){
            MATCH(Token.NULL);
        }else if(lookAhead.getName().equals(Token.DEFAULT)){
            MATCH(Token.DEFAULT);
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.NULL);
            errors.add(Token.DEFAULT);
            raiseError(errors);
        }
    }
    private void COLUMN_CONSTR_I(){
        if(lookAhead.getName().equals(Token.CASCADE)){
            MATCH(Token.CASCADE);
        }else if(lookAhead.getName().equals(Token.SET)){
            MATCH(Token.SET);
            COLUMN_CONSTR_H();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.CASCADE);
            errors.add(Token.SET);
            raiseError(errors);
        }
    }
    private void TABLE_CONSTRAINT(){
        COLUMN_CONSTR_A();
        TABLE_CONSTR_A();
    }
    private void TABLE_CONSTR_A(){
        if(lookAhead.getName().equals(Token.PRIMARY)){
            MATCH(Token.PRIMARY);
            MATCH(Token.KEY);
            COLUMN_CONSTR_C();
            TABLE_CONSTR_B();
        }else if(lookAhead.getName().equals(Token.UNIQUE)){
            MATCH(Token.UNIQUE);
            COLUMN_CONSTR_C();
            TABLE_CONSTR_B();
        }else if(lookAhead.getName().equals(Token.FOREIGN)){
            MATCH(Token.FOREIGN);
            MATCH(Token.KEY);
            TABLE_CONSTR_D();
            MATCH(Token.REFERENCES);
            Object_2();
            COLUMN_CONSTR_E();
            COLUMN_CONSTR_F();
            COLUMN_CONSTR_G();
            NOT_F_R();
        }else if(lookAhead.getName().equals(Token.CHECK)){
            MATCH(Token.CHECK);
            NOT_F_R();
            MATCH(Token.PARENTESISA);
            SearchCondition();
            MATCH(Token.PARENTESISC);
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PRIMARY);
            errors.add(Token.UNIQUE);
            errors.add(Token.FOREIGN);
            errors.add(Token.CHECK);
            raiseError(errors);

        }
    }
    private void TABLE_CONSTR_B(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
             MATCH(Token.PARENTESISA);
             ID();
             ORDER_B();
             TABLE_CONSTR_C();
             MATCH(Token.PARENTESISC);
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PARENTESISA);
            raiseError(errors);
        }
    }
    private void TABLE_CONSTR_C(){
        if(lookAhead.getName().equals(Token.COMA)){
               MATCH(Token.COMA);
               ID();
               ORDER_B();
               TABLE_CONSTR_C();
        }
    }
    private void TABLE_CONSTR_D(){
        MATCH(Token.PARENTESISA);
        ID();
        TABLE_CONSTR_E();
        MATCH(Token.PARENTESISC);
    }
    private void TABLE_CONSTR_E(){
        if(lookAhead.getName().equals(Token.COMA)){
            MATCH(Token.COMA);
            ID();
            TABLE_CONSTR_E();
        }
    }
    private void TABLE_INDEX(){
        if(lookAhead.getName().equals(Token.INDEX)){
            MATCH(Token.INDEX);
            ID();
            COLUMN_CONSTR_C();
            TABLE_CONSTR_B();
        }
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
        COLUMN_CONSTR_C();
        MATCH(Token.INDEX);
        ID();
        MATCH(Token.ON);
        Object_3();
        COLUMN_INDEX();
        INCLUDE_INDEX();
        WHERE();
        OptionON();
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.PARENTESISA);
            raiseError(errors);
        }
    }
    private void COLUMN_INDEX_A(){
        if(lookAhead.getName().equals(Token.IDENTIFICADOR)||lookAhead.getName().equals(Token.CORCHETEA)){
            ID();
            ORDER_B();
            COLUMN_INDEX_B();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            raiseError(errors);
        }
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
    private void OptionON(){
        if(lookAhead.getName().equals(Token.ON)){
            MATCH(Token.ON);
            ID();
            MATCH(Token.PARENTESISA);
            ID();
            MATCH(Token.PARENTESISC);
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
        if(lookAhead.getName().equals(Token.TABLE)||lookAhead.getName().equals(Token.USER)
                ||lookAhead.getName().equals(Token.DATABASE)||lookAhead.getName().equals(Token.VIEW)) {
            ALTER_A();
        } else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.TABLE);
            errors.add(Token.USER);
            errors.add(Token.DATABASE);
            errors.add(Token.VIEW);
            raiseError(errors);
        }

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
            C_N_C();
            C_N_C_A();
        }else if(lookAhead.getName().equals(Token.DROP)){
            ALTER_TABLE_DROP();
        }
        else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.ALTER);
            errors.add(Token.ADD);
            errors.add(Token.DROP);
            raiseError(errors);
        }
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
            TIPO_DATO();
            COLUMN_DEF_A();
            COLUMN_DEF_G();
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
        } else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.VARCHAR);
            errors.add(Token.FLOAT);
            errors.add(Token.DATE);
            errors.add(Token.BIT);
            errors.add(Token.DOUBLE);
            errors.add(Token.CHAR);
            errors.add(Token.CURSOR);
            errors.add(Token.NCHAR);
            errors.add(Token.REAL);
            errors.add(Token.TIME);
            errors.add(Token.INT);
            errors.add(Token.INTEGER);
            errors.add(Token.DECIMAL);
            errors.add(Token.SMALLINT);
            errors.add(Token.NUMERIC);
            errors.add(Token.ADD);
            errors.add(Token.DROP);
            raiseError(errors);
        }
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.ROWGUIDCOL);
            errors.add(Token.NOT);
            raiseError(errors);
        }
    }
    private void ALTER_TABLE_DROP(){
        if(lookAhead.getName().equals(Token.DROP)) {
            MATCH(Token.DROP);
            ALTER_TABLE_DROP_A();
            ALTER_TABLE_DROP_C();
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.DROP);
            raiseError(errors);
        }
    }
    private void ALTER_TABLE_DROP_A(){
        if(lookAhead.getName().equals(Token.CONSTRAINT)|| lookAhead.getName().equals(Token.IF)||lookAhead.getName().equals(Token.IDENTIFICADOR)||
                lookAhead.getName().equals(Token.CORCHETEA)){
            ALTER_TABLE_DROP_B();
            IF_EXISTS();
            ID();
        }else if (lookAhead.getName().equals(Token.COLUMN)){
            MATCH(Token.COLUMN);
            IF_EXISTS();
            ID();
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.CONSTRAINT);
            errors.add(Token.IF);
            errors.add(Token.COLUMN);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            raiseError(errors);
        }
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
        }else if(lookAhead.getName().equals(Token.COLUMN)|| lookAhead.getName().equals(Token.CONSTRAINT)||lookAhead.getName().equals(Token.IF)
                ||lookAhead.getName().equals(Token.IDENTIFICADOR)||
                lookAhead.getName().equals(Token.CORCHETEA)){
            ALTER_TABLE_DROP_A();
        } else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.COLUMN);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTRAINT);
            errors.add(Token.IF);
            raiseError(errors);
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
        ALTER_VIEW_A();
        MATCH(Token.AS);
        SELECT();
    }
    private void ALTER_VIEW_A(){
        if(lookAhead.getName().equals(Token.PARENTESISA)){
            COLUMN_LIST();
        }
    }
    //endregion
    //region TRUNCATE
    private void TRUNCATE(){
        MATCH(Token.TRUNCATE);
        MATCH(Token.TABLE);
        Object_3();
    }
    //endregion
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
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CADENA);
            raiseError(errors);
        }
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
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.PARENTESISA);
            errors.add(Token.NULL);
            errors.add(Token.AVG);
            errors.add(Token.COUNT);
            errors.add(Token.MIN);
            errors.add(Token.MAX);
            errors.add(Token.SUM);
            raiseError(errors);
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
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)||lookAhead.getName().equals(Token.NULL)) {
            ExpresionD();
            ExpresionC();
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.PARENTESISA);
            errors.add(Token.AVG);
            errors.add(Token.COUNT);
            errors.add(Token.MIN);
            errors.add(Token.MAX);
            errors.add(Token.SUM);
            raiseError(errors);
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
                || lookAhead.getName().equals(Token.MAX) || lookAhead.getName().equals( Token.SUM)||lookAhead.getName().equals(Token.NULL)) {
            ExpresionE();
        }else if (lookAhead.getName().equals(Token.PARENTESISA)) {
            MATCH(Token.PARENTESISA);
            Expresion();
            PredicadoB();
            MATCH(Token.PARENTESISC);
        }else{
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.AVG);
            errors.add(Token.COUNT);
            errors.add(Token.MIN);
            errors.add(Token.MAX);
            errors.add(Token.SUM);
            errors.add(Token.PARENTESISA);
            raiseError(errors);
        }
    }
    private void ExpresionE(){
        if (lookAhead.getName().equals(Token.IDENTIFICADOR) || lookAhead.getName().equals(Token.CORCHETEA)) {
            Object_4();
        }else if (lookAhead.getName().equals(Token.CONSTANTE_BOOLEANA) || lookAhead.getName().equals(Token.CONSTANTE_ENTERA) || lookAhead.getName().equals(Token.CONSTANTE_DECIMAL)
                || lookAhead.getName().equals(Token.CADENA) || lookAhead.getName().equals( Token.ARROBA)||lookAhead.getName().equals(Token.NULL)) {
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
        }else if(lookAhead.getName().equals(Token.MULTIPLICACION)){
            MATCH(Token.MULTIPLICACION);
            MATCH(Token.PARENTESISC);
        }else {
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.MULTIPLICACION);
            raiseError(errors);
        }
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
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.PARENTESISA);
            errors.add(Token.AVG);
            errors.add(Token.COUNT);
            errors.add(Token.MIN);
            errors.add(Token.MAX);
            errors.add(Token.SUM);
            raiseError(errors);
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
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.ASIGNACION);
            errors.add(Token.DIFERENCIA);
            errors.add(Token.MAYOR);
            errors.add(Token.MAYOR_IGUAL);
            errors.add(Token.MENOR);
            errors.add(Token.MENOR_IGUAL);
            errors.add(Token.NOT);
            errors.add(Token.BETWEEN);
            errors.add(Token.IN);
            errors.add(Token.LIKE);
            errors.add(Token.IS);
            raiseError(errors);
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
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.BETWEEN);
            errors.add(Token.IN);
            errors.add(Token.LIKE);
            raiseError(errors);
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
            List<Object> errors = new LinkedList<>();
            errors.add(lookAhead);
            errors.add(Token.IDENTIFICADOR);
            errors.add(Token.CORCHETEA);
            errors.add(Token.CONSTANTE_BOOLEANA);
            errors.add(Token.CONSTANTE_ENTERA);
            errors.add(Token.CONSTANTE_DECIMAL);
            errors.add(Token.CADENA);
            errors.add(Token.ARROBA);
            errors.add(Token.PARENTESISA);
            errors.add(Token.AVG);
            errors.add(Token.COUNT);
            errors.add(Token.MIN);
            errors.add(Token.MAX);
            errors.add(Token.SUM);
            errors.add(Token.NOT);
            raiseError(errors);
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
