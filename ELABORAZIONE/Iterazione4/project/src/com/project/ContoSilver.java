package com.project;
public class ContoSilver extends ContoCorrente{
    private int costoannuo=20;
    private static double tassoInt = 5;
    private static double maxPrelevabile = 350;
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

    public double massimoPrelevabile() {
        return maxPrelevabile;
    }

    @Override
    public double getTassoInt() {
        return tassoInt;
    }


    public static void setMaxPrelevabile(double maxPrelevabile) {
        ContoSilver.maxPrelevabile = maxPrelevabile;
    }

    public static void setTassoInt(double tassoInt) {
        ContoSilver.tassoInt = tassoInt;
    }

    public static double getMaxPrelevabile() {
        return maxPrelevabile;
    }
    public static double getTassoInteresse() {
        return tassoInt;
    }
}
