package com.project;
public class ContoSilver extends ContoCorrente{
    private int costoannuo=20;
    public ContoSilver(double saldo) {
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
        return "ContoSilver{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }
}
