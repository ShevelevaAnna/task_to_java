package exception;

public class ExceptionNullNumber extends Exception {

    public String toString(){
        return "число не может быть равно нулю или начинаться с нуля";
    }
}
