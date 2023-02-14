package com.project;

public class Deposito extends OperazioneBancaria{
    private int commissione;

    public Deposito(double importo, int commissione) {
        super(importo);
        this.commissione = commissione;
    }
}
