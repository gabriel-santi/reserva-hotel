package exception;

public class QuartoOcupadoException extends Exception{
    public QuartoOcupadoException() {
        super("Quarto reservado neste período");
    }
}
