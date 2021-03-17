package exception;

public class ExceptionOpeningBracketInput extends Exception {
    private String string;

    public ExceptionOpeningBracketInput (String string) {
        this.string = string;
    }

    public ExceptionOpeningBracketInput () {
        this.string = "пусто (стек символов пуст)";
    }

    public String toString(){
        String message = "перед '[' стоит " + string + ". Ожидалась цифра.";
        return message;
    }
}
