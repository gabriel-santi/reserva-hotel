package exception;

public class QuartoInexistenteException extends Exception{
    public QuartoInexistenteException() {
        super("Nenhum quarto registrado com esse número");
    }
}
