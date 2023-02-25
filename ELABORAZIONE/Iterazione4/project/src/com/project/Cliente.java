package com.project;
import java.time.LocalDate;


public class Cliente {
    private String nome,cognome,telefono;
    private LocalDate dataNascita;
    private CartadiCredito cartadiCredito;

    public Cliente(String nome, String cognome,  LocalDate dataNascita,String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dataNascita=" + dataNascita +
                '}';
    }


    //Metodo per settare la carta di credito al cliente
    public void setCartadiCredito(CartadiCredito carta) {
        this.cartadiCredito = carta;
    }

    public CartadiCredito getCartadiCredito() {
        return cartadiCredito;
    }
}
