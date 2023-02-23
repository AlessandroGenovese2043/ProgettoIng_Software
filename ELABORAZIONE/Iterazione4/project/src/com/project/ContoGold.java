package com.project;
public class ContoGold extends ContoCorrente{
    private static double tassoInt = 4;
    private int costoannuo=40;
    private static double maxPrelevabile = 700;
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

    @Override
    public double massimoPrelevabile() {
        return maxPrelevabile;
    }

    @Override
    public double getTassoInt() {
        return tassoInt;
    }

    public static void setTassoInt(double tassoInt) {
        ContoGold.tassoInt = tassoInt;
    }

    public static void setMaxPrelevabile(double maxPrelevabile) {
        ContoGold.maxPrelevabile = maxPrelevabile;
    }
    public static double getTassoInteresse() {
        return tassoInt;
    }




}
