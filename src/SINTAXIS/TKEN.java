package SINTAXIS;

import java.util.ArrayList;

public class TKEN {
    private Token name;
    private String Value;
    private int columnBegin;
    private int columnEnd;
    private int line;

    public TKEN(Token name,String value, int cbegin, int cend, int line){
        this.name = name;
        this.Value = value;
        this.columnBegin = cbegin;
        this.columnEnd = cend;
        this.line = line;
    }
    public String getValue() {
        return Value;
    }


    public int getColumnBegin() {
        return columnBegin;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public int getLine() {
        return line;
    }

    public Token getName() {
        return name;
    }

    public void setName(Token name) {
        this.name = name;
    }
}
