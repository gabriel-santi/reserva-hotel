package exception;

public class DataInvalidaException extends Exception{
    public DataInvalidaException() {
        super("Data informada não está no padrão correto");
    }
}
