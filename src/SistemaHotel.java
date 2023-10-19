import domain.Data;
import domain.Hospede;
import exception.DataInvalidaException;
import exception.EntradaInvalidaException;
import exception.QuartoInexistenteException;
import facade.ReservaFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaHotel extends InterfaceUI {
    ReservaFacade gerenciadorDeReservas;
    final private Scanner leitor = new Scanner(System.in);

    public SistemaHotel() {
        gerenciadorDeReservas = new ReservaFacade();
    }

    public void iniciar() {
        int input = 0;
        printar("-> Bem vindo(a) ao sistema de gerenciamento de reservas\n");

        do {
            if (input != 0) {
                try {
                    tratarResposta(input);
                } catch (EntradaInvalidaException e) {
                    printar(e.getMessage());
                }
            }
            printar("-> Selecione uma opção:");
            printar("1 - Fazer uma reserva");
            printar("2 - Cancelar uma reserva");
            printar("3 - Realizar o pagamento de uma reserva");
            printar("4 - Verificar disponibilidade dos quartos");
            printar("5 - Sair");
            input = leitor.nextInt();
        } while (input != 5);

        printar("\n-> Finalizando sistema...");
        printar("-> Sistema finalizado com sucesso!");
    }

    private void tratarResposta(int input) throws EntradaInvalidaException {
        if (input < 1 || input > 5) {
            throw new EntradaInvalidaException();
        }

        switch (input) {
            case 1 -> fluxoAdicionarReserva();
            case 2 -> fluxoCancelarReserva();
            case 3 -> fluxoPagamento();
            case 4 -> fluxoDisponibilidade();
        }
    }

    void fluxoAdicionarReserva() {
        int numQuarto = solicitarNumeroQuarto();
        if(numQuarto == 0) return;
        String dataEntrada = solicitarDataEntrada();
        String dataSaida = solicitarDataSaida();

        List<Hospede> hospedes = new ArrayList<>();
        String nome = "";
        printar("-> Registre as pessoas que irão se hospedar");
        do{
            if(!nome.isEmpty()) hospedes.add(new Hospede(nome));
            printar("-> Nome ou 0 para prosseguir:");
            nome = leitor.next();
        }while(!nome.equals("0"));

        try {
            gerenciadorDeReservas.fazerReservaQuarto(numQuarto, dataEntrada, dataSaida, hospedes);
            printarReserva(numQuarto, dataEntrada, dataSaida, hospedes);
        } catch (Exception e) {
            printar("\nNão foi possível efetuar a reserva: " + e.getMessage());
            fluxoAdicionarReserva();
        }
    }

    void fluxoCancelarReserva() {
        int numQuarto = solicitarNumeroQuarto();
        if (numQuarto == 0) return;
        String data = solicitarDataReserva();

        try {
            gerenciadorDeReservas.cancelarReserva(numQuarto, data);
            printar("\n-> Reserva do quarto " + numQuarto + " no periodo " + data + " cancelada com sucesso!");
        } catch (Exception e) {
            printar("\nNão foi possível cancelar a reserva: " + e.getMessage());
            fluxoCancelarReserva();
        }
    }

    void fluxoPagamento() {
        int numQuarto = solicitarNumeroQuarto();
        if (numQuarto == 0) return;
        String dataReserva = solicitarDataReserva();
        printar("-> Informe o valor pago:");
        double valor = leitor.nextDouble();
        try {
            gerenciadorDeReservas.realizarPagamento(numQuarto, dataReserva, valor);
            printar("\n-> Pagamento da reserva no dia " + dataReserva + " no quarto " + numQuarto + " realizada com sucesso!");
        } catch (Exception e) {
            printar(e.getMessage());
            fluxoPagamento();
        }
    }

    void fluxoDisponibilidade() {
        int numQuarto = solicitarNumeroQuarto();
        if (numQuarto == 0) return;
        String dataEntrada = solicitarDataEntrada();
        String dataSaida = solicitarDataSaida();

        try {
            boolean disponivel = gerenciadorDeReservas.verificarDisponibilidade(numQuarto, dataEntrada, dataSaida);
            if (disponivel) {
                printar("\n-> Quarto " + numQuarto + " disponível no período entre " + dataEntrada + " e " + dataSaida + "!!\n");
            } else {
                printar("\n-> Quarto " + numQuarto + " indisponível no período entre " + dataEntrada + " e " + dataSaida + "!!\n");
            }
        } catch (Exception e) {
            printar(e.getMessage());
            fluxoDisponibilidade();
        }
    }

    private int solicitarNumeroQuarto() {
        printar("\n-> Informe o número do quarto ou 0 para voltar:");
        printar("-> Quartos: " + gerenciadorDeReservas.getQuartos());
        int numQuarto = leitor.nextInt();
        if(numQuarto == 0) return 0;

        try {
            gerenciadorDeReservas.buscarQuarto(numQuarto);
        } catch (QuartoInexistenteException e) {
            printar(e.getMessage());
            return solicitarNumeroQuarto();
        }

        return numQuarto;
    }

    private String solicitarDataEntrada(){
        printar("-> Informe a data da entrada no formato dd-mm-yyyy:");
        String dataEntrada = leitor.next();

        try{
            new Data(dataEntrada);
        }catch (DataInvalidaException e) {
            printar(e.getMessage());
            return solicitarDataEntrada();
        }

        return dataEntrada;
    }

    private String solicitarDataSaida(){
        printar("-> Informe a data de saída no formato dd-mm-yyyy:");
        String dataSaida = leitor.next();

        try{
            new Data(dataSaida);
        }catch (DataInvalidaException e) {
            printar(e.getMessage());
            return solicitarDataSaida();
        }

        return dataSaida;
    }

    private String solicitarDataReserva(){
        printar("-> Informe a data da reserva no formato dd-mm-yyyy:");
        String dataReserva = leitor.next();

        try{
            new Data(dataReserva);
        }catch (DataInvalidaException e) {
            printar(e.getMessage());
            return solicitarDataSaida();
        }

        return dataReserva;
    }
}
