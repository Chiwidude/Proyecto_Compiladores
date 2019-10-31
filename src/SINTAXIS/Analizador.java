package SINTAXIS;
import java_cup.runtime.Symbol;

import javax.swing.*;
import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Analizador {
    public String path;
    private int lastline = 0;
    private int countErrors = 0;
    private String errors_St;
    private StringBuilder str = new StringBuilder();
    private ArrayList<TKEN> strings = new ArrayList<>();
    public Analizador(String path){
        this.path = path;
    }

    public String Analizar_Lexico() throws IOException {
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
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case ERROR_DECIMAL:
                    result.append( token + ": " + lexer.foundLine + " número decimal mal escrito linea: " + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                default:
                    result.append(  lexer.foundLine+ " es una palabra: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case IDENTIFICADOR:
                    if(lexer.foundLine.length() <= 31) {
                        result.append(lexer.foundLine + " es un: " + token + " linea:" + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    } else{
                        result.append(lexer.foundLine + " excede la longitud máxima del identificador,31. El identificador válido sería: "  +
                                lexer.foundLine.substring(0,31)+ " linea: " + lexer.line + " columna inicio: " +
                                lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        strings.add(new TKEN(token,lexer.foundLine.substring(0,31),lexer.columnSt,lexer.columnNd,lexer.line));
                    }

                    break;
                case CADENA:
                    result.append(  lexer.foundLine+ " es una: " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    break;
                case ASIGNACION: case SUMA: case RESTA: case MULTIPLICACION: case DIFERENCIA: case DIVISION: case MOD: case MAYOR: case MAYOR_IGUAL:
                case MENOR: case MENOR_IGUAL: case IGUALACION: case CONSTANTE_DECIMAL: case AND: case ARROBA: case COMA: case CORCHETEA: case PGO: case PUNTO_COMA: case CONSTANTE_BOOLEANA:
                case CONSTANTE_ENTERA: case CORCHETEC: case CORCHETES: case LLAVEC: case PARENTESISC: case EXCLAMACION: case LLAVEA: case LLAVES: case NUMERAL: case NUMERALES:
                case PARENTESIS: case PARENTESISA: case PUNTO: case OR: case GO:
                    result.append(  lexer.foundLine+ " es un símbolo de : " + token +" linea:" + lexer.line + " columna inicio: " +
                            lexer.columnSt + " columna fin: " + lexer.columnNd + "\n");
                    if(token.equals(Token.PGO)||token.equals(Token.PUNTO_COMA)||token.equals(Token.GO)){
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                        int vr = 0;
                        for(TKEN tk : strings){
                            if(tk.getName().equals(Token.ERROR)||tk.getName().equals(Token.ERROR_DECIMAL)){
                                vr++;
                            }
                        }
                        if(vr > 0){
                            BuildStr(null,false);
                        }else{
                            BuildStr(strings,true);
                        }
                        strings.clear();
                    }else{
                        strings.add(new TKEN(token,lexer.foundLine,lexer.columnSt,lexer.columnNd,lexer.line));
                    }

                    break;
            }

        }


    }
    public void Analizar_Sintactico () throws IOException {
        File file = new File(path);
        String st = new String(Files.readAllBytes(file.toPath()));
        Sintactic sintactic;
        if(countErrors > 0){
            JOptionPane.showMessageDialog(null,"Se encontraron errores léxicos en el archivo, los cuales se han eliminado previo al análisis sintáctico");
            sintactic = new Sintactic(new LexerJcup(new StringReader(st)));
        }else{
            sintactic = new Sintactic(new LexerJcup(new StringReader(str.toString())));
        }
        try {
          sintactic.parse();
          this.errors_St = sintactic.Errors();
          if(this.errors_St.isEmpty()){
              errors_St = "Análisis Sintáctico Sin Errores";
          }
        } catch (Exception e) {
            Symbol s = sintactic.getSymbol();
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

    private void BuildStr(ArrayList<TKEN> tkens, boolean write){
        if(write){
            int column = 0;
            int line = lastline;
            for(int i = 0; i< tkens.size();i++){
                TKEN temp = tkens.get(i);
                if(line < temp.getLine()){
                    while(line < temp.getLine()){
                     str.append(System.lineSeparator());
                     line++;
                    }
                    column = 0;
                }
                boolean found = false;
                while(!found) {
                    if (column == temp.getColumnBegin()) {
                        found = true;
                    } else {
                        str.append(" ");
                        column++;
                    }
                }
                str.append(temp.getValue());
                column = temp.getColumnEnd()+1;
                lastline = temp.getLine();
            }
        }else{
            str.append(System.lineSeparator());
            countErrors++;
        }
    }

    public String getErrors_St() {
        return errors_St;
    }
}
