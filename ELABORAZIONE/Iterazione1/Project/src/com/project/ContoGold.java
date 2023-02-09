package com.project;
public class ContoGold extends ContoCorrente{
    private int costoannuo=40;

    public ContoGold(double saldo) {
        super(saldo);

    }

    public int getCostoannuo() {
        return costoannuo;
    }

    public void setCostoannuo(int costoannuo) {
        if(costoannuo > 0)
            this.costoannuo = costoannuo;
        else
            this.costoannuo = 0;
    }

    @Override
    public String toString() {
        return "ContoGold{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
