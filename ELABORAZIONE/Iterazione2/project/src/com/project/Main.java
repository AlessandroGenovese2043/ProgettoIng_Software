package com.project;


import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------SISTEMA BANCARIO-----------");
        SistemaBancario sistema= SistemaBancario.getInstance();
        //inserimento cliente prova

        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464");
        sistema.inserisciConto(2,0);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Davide","Verdi", LocalDate.of(2005,1,14),"33366038");
        sistema.inserisciConto(1,10000);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Matteo","Neri", LocalDate.of(1989,6,2),"366943578");
        sistema.inserisciConto(3,45);
        sistema.confermaOperazione();

        System.out.println("Stampa mappa");
        sistema.stampa();
        System.out.println("OPERAZIONE DI PRELIEVO");
        ContoCorrente conto = OperazioneDiVerifica(sistema);
        if(conto == null){//il numero di carta non esiste
            return;
        }
        System.out.println("INSERIMENTO PIN E IMPORTO");
        System.out.println("Inserisci numero pin");
        Scanner dati = new Scanner(System.in);
        int pin = Integer.parseInt(dati.nextLine());
        System.out.flush();
        int a = -1;
        int importo = 0;
        while(a < 0) {
            System.out.println("Inserisci importo");
            importo = Integer.parseInt(dati.nextLine());
            System.out.flush();
            if( importo > 0){
                 a = 1;
            }
            else{
                System.err.println("Errore: inserire un numero maggiore di zero");
            }
        }
        sistema.effetuaPrelievo(pin, importo, conto);

        System.out.println("OPERAZIONE DI DEPOSITO");
        conto = OperazioneDiVerifica(sistema);
        if(conto == null){
            return;
        }
        System.out.println("INSERIMENTO PIN E IMPORTO");
        System.out.println("Inserisci numero pin");
        int pinDeposito= Integer.parseInt(dati.nextLine());
        System.out.flush();
        int importoDeposito = -1;
        while( importoDeposito < 0) {
            System.out.println("Inserisci importo");
            importoDeposito = Integer.parseInt(dati.nextLine());
            System.out.flush();
            if(importoDeposito < 0){
                System.err.println("Errore: inserire un numero maggiore di zero");
            }
        }
        sistema.effetuaDeposito(pinDeposito, importoDeposito, conto);

        Deposito.setCommissione(1);

        System.out.println("OPERAZIONE DI DEPOSITO");
        conto = OperazioneDiVerifica(sistema);
        if(conto == null){
            return;
        }
        System.out.println("INSERIMENTO PIN E IMPORTO");
        System.out.println("Inserisci numero pin");
        int pinDeposito2= Integer.parseInt(dati.nextLine());
        System.out.flush();
        int importoDeposito2 = -1;
        while( importoDeposito2 < 0) {
            System.out.println("Inserisci importo");
            importoDeposito2 = Integer.parseInt(dati.nextLine());
            System.out.flush();
            if(importoDeposito2 < 0){
                System.err.println("Errore: inserire un numero maggiore di zero");
            }
        }
        sistema.effetuaDeposito(pinDeposito2, importoDeposito2, conto);

        conto.stampaListaMovimenti();
    }
    public static ContoCorrente OperazioneDiVerifica(SistemaBancario sistema){
        System.out.println("Inserisci numero carta");
        Scanner input = new Scanner(System.in);
        String numeroCartaInserito = input.nextLine();
        ContoCorrente conto = sistema.verificaCarta(numeroCartaInserito); //worka
        if(conto!=null)
        {
            System.out.println("Il tuo saldo attuale è:" + conto.getSaldo());
        }else {
            System.err.println("Mi dispiace carta non presente nel sistema, riprovare");
            return null;
        }
        return conto;
    }
}