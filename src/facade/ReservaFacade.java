package facade;

import domain.*;
import exception.*;

import java.util.ArrayList;
import java.util.List;

public class ReservaFacade {
    private final List<Quarto> quartos;

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public ReservaFacade() {
        List<Quarto> listaQuartos = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            listaQuartos.add(new Quarto(i));
        }
        quartos = listaQuartos;
    }

    public Reserva fazerReservaQuarto(int numQuarto, String dataEntrada, String dataSaida, List<Hospede> hospedes) throws QuartoInexistenteException, DataInvalidaException, QuartoOcupadoException, DataInicialInvalidaException {
        Quarto quartoSelecionado = buscarQuarto(numQuarto);
        boolean quartoDisponivel = verificarDisponibilidade(numQuarto, dataEntrada, dataSaida);

        if (quartoDisponivel) {
            Vigencia periodo = new Vigencia(dataEntrada, dataSaida);
            Reserva novaReserva = new Reserva(periodo, hospedes);
            quartoSelecionado.adicionarReserva(novaReserva);
            return novaReserva;
        } else {
            throw new QuartoOcupadoException();
        }
    }

    public void cancelarReserva(int numQuarto, String dataStr) throws QuartoInexistenteException, DataInvalidaException, ReservaInexistenteException {
        Quarto quartoSelecionado = buscarQuarto(numQuarto);
        for (Reserva reserva : quartoSelecionado.getReservas()) {
            if (reserva.estaAtiva(new Data(dataStr))) {
                reserva.cancelar();
                return;
            }
        }

        throw new ReservaInexistenteException();
    }

    public void realizarPagamento(int numQuarto, String dataStr, double valor) throws QuartoInexistenteException, DataInvalidaException, PagamentoInsuficienteException, ReservaInexistenteException {
        Quarto quartoSelecionado = buscarQuarto(numQuarto);
        for (Reserva reserva : quartoSelecionado.getReservas()) {
            if (reserva.estaAtiva(new Data(dataStr))) {
                reserva.realizarPagamento(valor);
                return;
            }
        }

        throw new ReservaInexistenteException();
    }

    public boolean verificarDisponibilidade(int numQuarto, String dataEntrada, String dataSaida) throws QuartoInexistenteException, DataInvalidaException, DataInicialInvalidaException {
        Quarto quarto = buscarQuarto(numQuarto);
        Vigencia periodo = new Vigencia(dataEntrada, dataSaida);

        for (Reserva reserva : quarto.getReservas()) {
            if (reserva.estaAtiva(periodo)) {
                return false;
            }
        }

        return true;
    }

    public Quarto buscarQuarto(int numQuarto) throws QuartoInexistenteException {
        Quarto quartoSelecionado;
        for (Quarto quarto : quartos) {
            if (quarto.getNumeroQuarto() == numQuarto) {
                quartoSelecionado = quarto;
                return quartoSelecionado;
            }
        }
        throw new QuartoInexistenteException();
    }

    public Reserva buscarReserva(int numQuarto, String dataReserva) throws QuartoInexistenteException, DataInvalidaException, ReservaInexistenteException {
        Quarto quartoSelecionado = buscarQuarto(numQuarto);
        for(Reserva reserva: quartoSelecionado.getReservas()){
            if(reserva.estaAtiva(new Data(dataReserva))){
                return reserva;
            }
        }

        throw new ReservaInexistenteException();
    }
}
