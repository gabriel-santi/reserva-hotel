package domain;

import exception.DataInicialInvalidaException;
import exception.DataInvalidaException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vigencia {
    Data dataInicio;
    Data dataFim;

    public Vigencia(String dataInicio, String dataFinal) throws DataInvalidaException, DataInicialInvalidaException {
        this.dataInicio = new Data(dataInicio);
        this.dataFim = new Data(dataFinal);
        if (this.dataInicio.dia.after(this.dataFim.dia)) throw new DataInicialInvalidaException();
    }

    boolean estaVigente(Vigencia periodo) {
        boolean comecaDurante = dataInicio.dia.before(periodo.dataInicio.dia) && dataFim.dia.after(periodo.dataInicio.dia);
        boolean terminaDurante = dataInicio.dia.after(periodo.dataInicio.dia) && dataFim.dia.before(periodo.dataFim.dia);
        boolean IncluiComeco = dataInicio.dia.equals(periodo.dataInicio.dia) || dataInicio.dia.equals(periodo.dataFim.dia);
        boolean incluiFinal = dataFim.dia.equals(periodo.dataFim.dia) || dataFim.dia.equals(periodo.dataInicio.dia);

        return comecaDurante || terminaDurante || IncluiComeco || incluiFinal;
    }

    boolean estaVigente(Data data) {
        return (dataInicio.dia.before(data.dia) && dataFim.dia.after(data.dia)) || dataInicio.dia.equals(data.dia) || dataFim.dia.equals(data.dia);
    }

    long getDias(){
        LocalDate localDate1 = dataInicio.dia.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = dataFim.dia.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();


        return ChronoUnit.DAYS.between(localDate1, localDate2);
    }
}
