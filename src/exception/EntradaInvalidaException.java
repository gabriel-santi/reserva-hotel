package exception;

public class EntradaInvalidaException extends  Exception{
    public EntradaInvalidaException() {
        super("Selecione uma opção válida");
    }
}
