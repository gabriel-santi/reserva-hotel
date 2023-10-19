import domain.Hospede;

import java.util.List;

public abstract class InterfaceUI {

    void printar(String mensagem) {
        System.out.println(mensagem);
    }

    void printarReserva(int numQuarto, String dataEntrada, String dataSaida, List<Hospede> hospedes, double valor) {
        System.out.println("-> Reserva realizada com sucesso!\n");
        System.out.println("=====================================");
        System.out.println("Quarto: n°" + numQuarto);
        System.out.println("Data entrada: " + dataEntrada);
        System.out.println("Data saída: " + dataSaida);
        System.out.println("Valor: R$" + valor);
        System.out.println(hospedes.size() + " hospede(s)" +
                hospedes.toString().replace("[", ": ").replace("]", ""));
        System.out.println("=====================================\n");

    }
}
