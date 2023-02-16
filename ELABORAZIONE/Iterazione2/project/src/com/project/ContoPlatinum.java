package com.project;
public class ContoPlatinum extends ContoCorrente{
    private int costoannuo=60;
    private static double maxPrelevabile = 1500;
    public ContoPlatinum(double saldo) {
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
        return "ContoPlatinum{" + super.toString() +
                "costoannuo=" + costoannuo +
                '}';
    }


    @Override
    public double massimoPrelevabile() {
        return maxPrelevabile;
    }

    public static void setMaxPrelevabile(double maxPrelevabile) {
        ContoPlatinum.maxPrelevabile = maxPrelevabile;
    }
}
