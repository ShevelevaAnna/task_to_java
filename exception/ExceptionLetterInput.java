package exception;

public class ExceptionLetterInput extends Exception {
    private char symbol;
    private String number;

    public ExceptionLetterInput (char symbol, String number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String toString(){
        String message = "ввод буквы '" + symbol + "' после цифр(ы) " + number;
        return message;
    }
}
