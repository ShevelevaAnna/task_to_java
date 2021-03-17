package stringProcessing;

import exception.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringProcessing {
    private ArrayList<String> resulting_string;
    private String file_path;

    public StringProcessing (String file_path) {
        this.file_path = file_path;
        this.resulting_string = new ArrayList<>();
    };

    public void stringReading () {
        int symbol;
        try(FileReader reader = new FileReader(file_path)){
            while ((symbol = reader.read()) != -1){
                if ((char)symbol == '['){
                    openingBracketHandling((char)symbol);
                }
                if ((char)symbol == ']'){
                    closingBracketHandling((char)symbol);
                }
                if (((char)symbol >= 'A' && (char)symbol <= 'Z') || ((char)symbol >= 'a' && (char)symbol <= 'z')){
                    letterHandling((char)symbol);
                }
                if ((char)symbol >= '0' && (char)symbol <= '9'){
                    numberHandling((char)symbol);
                }
                if ((char)symbol != '[' && (char)symbol != ']' && !((char)symbol >= '0' && (char)symbol <= '9') &&
                    !(((char)symbol >= 'A' && (char)symbol <= 'Z') || ((char)symbol >= 'a' && (char)symbol <= 'z'))){
                    throw new ExceptionSymbol((char)symbol, file_path);
                }
            }
            if (resulting_string.size() == 0) {
                throw new NullPointerException(file_path + " is empty");
            }
            if(resulting_string.size() > 1){
                throw new ExceptionDontClosingBracketInput();
            }
        }
        catch (IOException ex){
            System.out.println("log (StringProcessing .java function stringReading): error - "+ex.getMessage());
            System.exit(0);
        }
        catch (NullPointerException ex){
            System.out.println("log (StringProcessing .java function stringReading): warning - "+ex.getMessage());
            System.exit(0);
        }
        catch (ExceptionSymbol ex){
            System.out.println("log (StringProcessing .java function stringReading): error - "+ex.toString());
            System.exit(0);
        }
        catch (ExceptionDontClosingBracketInput ex){
            System.out.println("log (StringProcessing .java function stringReading): error - "+ex.toString());
            System.exit(0);
        }

        System.out.println("Результат работы программы: "+resulting_string.get(0));
    };

    private void openingBracketHandling(char symbol){
        try {
            if (resulting_string.size() != 0 && isNumeric(resulting_string.get(resulting_string.size() - 1))){
                resulting_string.add("[");
            }
            else {
                if (resulting_string.size() != 0){
                    throw  new ExceptionOpeningBracketInput(resulting_string.get(resulting_string.size() - 1));
                }
                else {
                    throw  new ExceptionOpeningBracketInput();
                }
            }
        }
        catch (ExceptionOpeningBracketInput ex){
            System.out.println("log (StringProcessing.java openingBracketHandling): error - "+ex.toString());
            System.exit(0);
        }
    }

    private void closingBracketHandling(char symbol){
        String current_string;
        String result_string = "";
        try {
            if(resulting_string.size() > 2){
                // запоминаем строку и удаляем ее из стека
                current_string = resulting_string.get(resulting_string.size() - 1);
                resulting_string.remove(resulting_string.size() - 1);
                // удаляем из стека открывающуюся скобку
                resulting_string.remove(resulting_string.size() - 1);
                // удаляем число повторений и записываем новую строку
                for (int i = 0; i < Integer.parseInt(resulting_string.get(resulting_string.size() - 1)); i++){
                    result_string += current_string;
                }
                resulting_string.remove(resulting_string.size() - 1);
                letterHandling(result_string);
            }
            else {
                if (resulting_string.size() > 1 && resulting_string.get(resulting_string.size() - 1).equals("[") &&
                        isNumeric(resulting_string.get(resulting_string.size() - 2))){
                    // для случая число[пусто]
                    resulting_string.remove(resulting_string.size() - 1);
                    resulting_string.remove(resulting_string.size() - 1);
                    throw  new WarningClosingBracketInput();
                }
                else {
                    throw  new ExceptionClosingBracketInput();
                }
            }
        }
        catch(ExceptionClosingBracketInput ex){
            System.out.println("log (StringProcessing.java function closingBracketHandling): error - "+ex.toString());
            System.exit(0);
        }
        catch(WarningClosingBracketInput ex){
            System.out.println("log (StringProcessing.java function closingBracketHandling): warning - "+ex.toString());
        }
    }

    private void letterHandling(char symbol){
        try {
            if (resulting_string.size() != 0 && isNumeric(resulting_string.get(resulting_string.size() - 1))){
                throw  new ExceptionLetterInput(symbol, resulting_string.get(resulting_string.size() - 1));
            }
            else {
                if (resulting_string.size() == 0 || resulting_string.get(resulting_string.size() - 1).equals("[")){
                    resulting_string.add(Character.toString(symbol));
                }
                else {
                    String letter_string = resulting_string.get(resulting_string.size() - 1) + symbol;
                    resulting_string.set(resulting_string.size() - 1, letter_string);
                }
            }
        }
        catch (ExceptionLetterInput ex){
            System.out.println("log (StringProcessing.java function letterHandling): error - "+ex.toString());
            System.exit(0);
        }
    }

    private void letterHandling(String string){
        if (resulting_string.size() == 0 || resulting_string.get(resulting_string.size() - 1).equals("[")){
            resulting_string.add(string);
        }
        else{
            String letter_string = resulting_string.get(resulting_string.size() - 1) + string;
            resulting_string.set(resulting_string.size() - 1, letter_string);
        }
    }

    private void numberHandling(char symbol){
        try {
            if (resulting_string.size() == 0 || !isNumeric(resulting_string.get(resulting_string.size() - 1))){
                if (symbol == '0') {
                    throw  new ExceptionNullNumber();
                }
                resulting_string.add(Character.toString(symbol));
            }
            else {
                String number_string = resulting_string.get(resulting_string.size() - 1) + symbol;
                resulting_string.set(resulting_string.size() - 1, number_string);
            }
        }
        catch (ExceptionNullNumber ex){
            System.out.println("log (StringProcessing.java function numberHandling): error - "+ex.toString());
            System.exit(0);
        }
    }

    private boolean isNumeric(String string){
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
