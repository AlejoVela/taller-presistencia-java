package Excepciones;

public class BancoException extends Exception {
    public BancoException(){
        super();
    }
    public BancoException(String message){
        super(message);
    }
}
