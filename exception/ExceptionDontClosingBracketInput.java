package exception;

public class ExceptionDontClosingBracketInput extends Exception {

    public String toString(){
        return "строка не имеет ожидаемой закрывающейся скобки";
    }
}
