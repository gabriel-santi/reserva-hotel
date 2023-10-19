package domain;


import exception.PagamentoInsuficienteException;

import java.util.List;

public class Reserva {
    final private Vigencia vigencia;
    final private List<Hospede> hospedes;
    final private double valor;
    private boolean pago;
    private boolean cancelada;

    public Reserva(Vigencia periodo, List<Hospede> hospedes){
        this.vigencia = periodo;
        this.hospedes = hospedes;
        this.valor = 150 * hospedes.toArray().length;
        this.cancelada = false;
        this.pago = false;
    }

    public Vigencia getVigencia() {
        return vigencia;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public double getValor() {
        return valor;
    }

    public boolean estaPago() {
        return pago;
    }

    public boolean estaCancelado() {
        return cancelada;
    }


    public void realizarPagamento(double valorPago) throws PagamentoInsuficienteException {
        if(valorPago < valor){
            throw new PagamentoInsuficienteException(valor);
        }
        pago = true;
    }

    public void cancelar(){
        cancelada = true;
    }

    public boolean estaAtiva(Vigencia periodo){
        return !cancelada && vigencia.estaVigente(periodo);
    }

    public boolean estaAtiva(Data data){
        return vigencia.estaVigente(data);
    }
}
