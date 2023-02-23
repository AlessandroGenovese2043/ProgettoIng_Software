package com.project;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class OperazioneBancaria {
    private LocalDate data;
    private String dateTime;
    private double importo;
    private static int idOperazione=1;
    private int idOp;

    public OperazioneBancaria(double importo) {
        this.dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
        this.data=LocalDate.now();
        this.importo = importo;
        this.idOp = idOperazione;
        idOperazione++;
    }

    public int getId() {
        return idOp;
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

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "OperazioneBancaria{" +
                ", dateTime='" + dateTime + '\'' +
                ", importo=" + importo +
                ", idOp=" + idOp +
                '}';
    }
}
