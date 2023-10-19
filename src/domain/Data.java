package domain;

import exception.DataInvalidaException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    Date dia;

    public Data(String dia) throws DataInvalidaException {
        try {
            if(dia.length() > 10) throw new DataInvalidaException();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            this.dia = formatter.parse(dia);
        }catch (java.text.ParseException e){
            throw new DataInvalidaException();
        }
    }
}
