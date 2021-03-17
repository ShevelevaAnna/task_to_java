package exception;

public class ExceptionSymbol extends Exception{
    private char symbol;
    private String file_path;
    public ExceptionSymbol (char symbol, String file_path) {
       this.symbol = symbol;
       this.file_path = file_path;
    }
    public String toString(){
        String message = "файл (" + file_path + ") содержит некорректный символ '" + symbol + "'";
        return message;
    }
}
