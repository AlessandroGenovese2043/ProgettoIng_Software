package com.project;

import java.time.LocalDate;

public abstract class OperazioneBancaria {
    private LocalDate data;
    private double importo;
    private static int idOperazione=1;

    public OperazioneBancaria(double importo) {
        this.data=LocalDate.now();
        this.importo = importo;
        idOperazione++;
    }

    public LocalDate getData() {
        return data;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public static int getIdOperazione() {
        return idOperazione;
    }

    @Override
    public String toString() {
        return "OperazioneBancaria{" +
                "data=" + data +
                ", importo=" + importo +
                ", idOperazione=" + idOperazione +
                '}';
    }
}
