package com.project;

public class Deposito extends OperazioneBancaria{
    private static int commissione = 2;
    private int commissioneAttuale;
    public Deposito(double importo) {
        super(importo);
        this.commissioneAttuale = commissione;
    }

    public static int getCommissione() {
        return commissione;
    }

    public static void setCommissione(int commissione) {
        Deposito.commissione = commissione;
    }

    public int getCommissioneAttuale() {
        return commissioneAttuale;
    }
}
