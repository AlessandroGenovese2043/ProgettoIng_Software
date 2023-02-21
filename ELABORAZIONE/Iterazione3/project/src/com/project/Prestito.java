package com.project;

import java.time.LocalDate;

public class Prestito {

    private static int countId=1;
    private int idPrestito,durataAnni,tassoInteresse;
    private boolean sanato;
    private double ammontare;
    private LocalDate dataInizio,dataFine;

    public Prestito(int durataAnni, int tassoInteresse,  double ammontare,LocalDate dataFine) {
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

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public int getDurataAnni() {
        return durataAnni;
    }

    public void setDurataAnni(int durataAnni) {
        this.durataAnni = durataAnni;
    }

    public int getTassoInteresse() {
        return tassoInteresse;
    }

    public void setTassoInteresse(int tassoInteresse) {
        this.tassoInteresse = tassoInteresse;
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

    public void setAmmontare(double ammontare) {
        this.ammontare = ammontare;
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
