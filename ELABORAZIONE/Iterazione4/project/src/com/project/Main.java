package com.project;


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------SISTEMA BANCARIO-----------");
        SistemaBancario sistema= SistemaBancario.getInstance();
        //inserimento cliente prova

        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464998");
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
        sistema.loadConsulenti();
        //sistema.stampaConsulenti();



        Scanner dati = new Scanner(System.in);
        int tipoConto = -1;
        while(tipoConto == -1 || ( tipoConto > 3  || tipoConto < 1)) {
            System.out.println("Inserisci il tipoConto da modificare \n1 per conto Silver, 2 per conto Gold, 3 per conto Platinum");
            System.out.flush();
            tipoConto = Integer.parseInt(dati.nextLine());
        }
        double nuovoTasso = -1;
        while(nuovoTasso <= 0) {
            System.out.println("Inserisci il nuovo tasso di interesse: ");
            System.out.flush();
            nuovoTasso = Double.parseDouble(dati.nextLine());
        }
        try {
            sistema.modificaTassoInteresse(nuovoTasso, tipoConto);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        tipoConto = -1;
        while(tipoConto == -1 || ( tipoConto > 3  || tipoConto < 1)) {
            System.out.println("Inserisci il tipoConto da modificare \n1 per conto Silver, 2 per conto Gold, 3 per conto Platinum");
            System.out.flush();
            tipoConto = Integer.parseInt(dati.nextLine());
        }
        double nuovoMax = -1;
        while(nuovoMax <= 0) {
            System.out.println("Inserisci il nuovo massimo prelevabile: ");
            System.out.flush();
            nuovoMax = Double.parseDouble(dati.nextLine());
        }
        try {
            sistema.modificaMaxPrelevabile(nuovoMax, tipoConto);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        List<Integer> lista_id = new ArrayList<>();
        List<ConsulenteFinanziario> listaConsulenti;
        try {
            listaConsulenti = sistema.richiediConsulente(TipoSettore.AZIONI);
            for(ConsulenteFinanziario con : listaConsulenti){
                System.out.println(con.toString());
                lista_id.add(con.getIdConsulente());
            }
            int id = -1;
            while(id == -1 || !lista_id.contains(id)) {
                System.out.println("Inserisci l'id del consulente scelto");
                System.out.flush();
                id = Integer.parseInt(dati.nextLine());
            }
            sistema.confermaConsulente(id,"35464998");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("OPERAZIONE DI STAMPA PRESTITI");
        try {
            sistema.stampaPrestiti();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        for(int i = 0; i<2; i++) {
        System.out.println("OPERAZIONE DI CONCESSIONE PRESTITO");
        System.out.flush();
        ContoCorrente contoCorrente = OperazioneDiVerifica(sistema);
        if (contoCorrente != null) {
            double ammontare = -1;
            while (ammontare < 0) {
                System.out.println("Inserisci l'ammontare del prestito");
                System.out.flush();
                ammontare = Integer.parseInt(dati.nextLine());
            }
            double stipendioCliente = -1;
            while (stipendioCliente <= 0) {
                System.out.println("Inserisci lo stipendio annuale del Cliente");
                System.out.flush();
                stipendioCliente = Integer.parseInt(dati.nextLine());
            }
            int scelta = 0;
            while (scelta != 1 && scelta != 2) {
                System.out.println("Inserisci 1 per un prestito di 10 anni 2 per un prestito di 20 anni");
                System.out.flush();
                scelta = Integer.parseInt(dati.nextLine());
            }
            if (scelta == 1) {
                try {
                    sistema.getCondizioni(contoCorrente, TipoPrestito.ANNI10, ammontare, stipendioCliente);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
            } else {
                try {
                    sistema.getCondizioni(contoCorrente, TipoPrestito.ANNI20, ammontare, stipendioCliente);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
            }

            int scelta2 = -1;
            while (scelta2 != 0 && scelta2 != 1) {
                System.out.println("Per confermare il prestito digita: 1, per annullare 0");
                System.out.flush();
                scelta2 = Integer.parseInt(dati.nextLine());

            }
            if (scelta2 == 1) {
                try {
                    sistema.confermaPrestito(contoCorrente);
                    System.out.println("Prestito confermato");
                    System.out.flush();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        } else {
            System.err.println("Errore: il numero di carta inserito non risulta associato a nessun conto");
        }

    }
/*
        System.out.println("OPERAZIONE DI PRELIEVO");
        ContoCorrente conto = OperazioneDiVerifica(sistema);
        if(conto == null){//il numero di carta non esiste
            return;
        }
        for(int i = 0; i < 2; i++) {
            System.out.println("INSERIMENTO PIN E IMPORTO");
            System.out.println("Inserisci numero pin");
            int pin = Integer.parseInt(dati.nextLine());
            System.out.flush();
            int a = -1;
            int importo = 0;
            while (a < 0) {
                System.out.println("Inserisci importo");
                importo = Integer.parseInt(dati.nextLine());
                System.out.flush();
                if (importo > 0) {
                    a = 1;
                } else {
                    System.err.println("Errore: inserire un numero maggiore di zero");
                }
            }
            sistema.effetuaPrelievo(pin, importo, conto);

        }

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

        Deposito.setCommissione(1);*/
/*
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
*//*
        try {
            conto.stampaListaMovimenti();
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }

        System.out.println("OPERAZIONE DI STAMPA LISTA MOVIMENTI");
        System.out.println("Inserisci numero carta");
        Scanner input = new Scanner(System.in);
        String numeroCartaInserito = input.nextLine();
        try {
            sistema.stampaListaMovimenti(numeroCartaInserito);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }*/
        System.out.println("OPERAZIONE DI STAMPA PRESTITI");
        try {
            sistema.stampaPrestiti();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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