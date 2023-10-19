package domain;

import java.util.ArrayList;
import java.util.List;

public class Quarto {
    final private int numeroQuarto;
    private List<Reserva> reservas;

    public Quarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.reservas = new ArrayList<>();
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }

    public void adicionarReserva(Reserva novaReserva){
        reservas.add(novaReserva);
    }

    @Override
    public String toString() {
        return String.valueOf(numeroQuarto);
    }
}
