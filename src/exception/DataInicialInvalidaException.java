package exception;

public class DataInicialInvalidaException extends Exception{
    public DataInicialInvalidaException() {
        super("Data inicial não pode ser maior que a data final");
    }
}
