package com.project;
import java.time.LocalDate;

public class CartadiCredito {
    private static int firstnumber = 1000; //primo blocco da 4 lo incrementiamo fino a 9999
    private static int lastnumber = 9999; //secondo blocco da 4 lo decrementiamo fino a 1000
    private String numeroCarta; //
    private int pinCarta;
    private final LocalDate scadenza;

    public CartadiCredito(LocalDate scadenza) {
        this.scadenza = scadenza;
        generaNumeroCarta();
        generaPin();
    }

    private void generaNumeroCarta(){
        String firstString = ("" + firstnumber);
        String lastString = ("" + lastnumber);
        firstnumber++;
        lastnumber--;
        numeroCarta = firstString.concat(lastString);
    }
    private void generaPin(){
        double random = (Math.random()*9000) + 1000; //pin di 4 numeri da 1000 a 9999
        pinCarta = (int) random;
    }
    public String getNumeroCarta() {
        return numeroCarta;
    }

    public int getPinCarta() {
        return pinCarta;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    @Override
    public String toString() {
        return "CartadiCredito{" +
                "numeroCarta='" + numeroCarta + '\'' +
                ", scadenza=" + scadenza + ", pin=" + pinCarta+
                '}';
    }
}
