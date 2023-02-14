package com.project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------SISTEMA BANCARIO-----------");
        SistemaBancario sistema= SistemaBancario.getInstance();
        //inserimento cliente prova

        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        sistema.inserisciConto(2,0);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Davide","Verdi", LocalDate.of(2005,1,14),"33366038");
        sistema.inserisciConto(1,0);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Matteo","Neri", LocalDate.of(1989,6,2),"366943578");
        sistema.inserisciConto(3,45);
        sistema.confermaOperazione();

        double saldo;

       saldo= sistema.verificaCarta("10029997"); //worka
       if(saldo!=-1)
       {
           System.out.println("Il tuo saldo attuale è: "+saldo);
       }else
           System.out.println("Mi dispiace carta non presente nel sistema");





        System.out.println("Stampa mappa");
        sistema.stampa();



    }

}