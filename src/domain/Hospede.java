package domain;

public class Hospede {
    String nome;

    public Hospede(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
