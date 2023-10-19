package exception;

public class ReservaInexistenteException extends Exception{
    public ReservaInexistenteException() {
        super("Nenhuma reserva encontrada nesta data");
    }
}
