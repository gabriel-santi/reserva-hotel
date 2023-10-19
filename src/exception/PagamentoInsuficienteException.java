package exception;

public class PagamentoInsuficienteException extends Exception{
    public PagamentoInsuficienteException(double valor) {
        super("Pagamento realizado menor que o valor da reserva!\nValor da reserva: R$"+valor);
    }
}
