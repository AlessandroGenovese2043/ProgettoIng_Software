package com.project;

import java.time.LocalDate;

public class Prestito {

    private static int countId=1;
    private int idPrestito,durataAnni;
    private final double tassoInteresse;
    private boolean sanato;
    private final double ammontare;
    private LocalDate dataInizio,dataFine;
    private Cliente cliente;
    private ContoCorrente conto;
    public Prestito(Cliente cliente,int durataAnni, double tassoInteresse,  double ammontare) {
        idPrestito=countId;
        countId++;
        this.cliente = cliente;
        this.durataAnni = durataAnni;
        this.tassoInteresse = tassoInteresse;
        this.sanato = false;
        this.ammontare = ammontare;
        this.dataInizio = LocalDate.now();
        this.dataFine = LocalDate.now().plusYears(durataAnni);
    }

    public Prestito(int durataAnni, double tassoInteresse,  double ammontare) {
        idPrestito=countId;
        countId++;
        this.durataAnni = durataAnni;
        this.tassoInteresse = tassoInteresse;
        this.sanato = false;
        this.ammontare = ammontare;
        this.dataInizio = LocalDate.now();
        this.dataFine = LocalDate.now().plusYears(durataAnni);
    }

    public  int getIdPrestito() {
        return idPrestito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setConto(ContoCorrente conto) {
        this.conto = conto;
    }

    public ContoCorrente getConto() {
        return conto;
    }

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public int getDurataAnni() {
        return durataAnni;
    }

    public void setDurataAnni(int durataAnni) {
        this.durataAnni = durataAnni;
    }

    public double getTassoInteresse() {
        return tassoInteresse;
    }



    public boolean isSanato() {
        return sanato;
    }

    public void setSanato(boolean sanato) {
        this.sanato = sanato;
    }

    public double getAmmontare() {
        return ammontare;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id prestito=" + idPrestito +
                "durataAnni=" + durataAnni +
                ", tassoInteresse=" + tassoInteresse +
                ", sanato=" + sanato +
                ", ammontare=" + ammontare +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
