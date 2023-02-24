package com.project;


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner dati = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("----------SISTEMA BANCARIO-----------");
        SistemaBancario sistema= SistemaBancario.getInstance();
        //caricamento nella mappa di clienti e conto correnti
        loadMappa(sistema);
        sistema.loadConsulenti();
        sistema.stampaConsulenti();


        System.out.println("Stampa mappa");
        sistema.stampa();
        int s,amm,cass;
        do
        {
            s=scelta();
            switch(s)
            {
                case 1:   //Amministratore

                    do {
                        amm = sceltaAmministratore();
                        switch(amm)
                        {
                            case 1:
                                    inserimento(sistema);
                                break;
                            case 2:
                                    sistema.stampa();
                                break;
                            case 3:
                                    modificaTasso(sistema);
                                break;
                            case 4:
                                    modificaPrelevabile(sistema);
                                break;
                            case 5:
                                System.out.println("OPERAZIONE DI STAMPA PRESTITI");
                                try {
                                    sistema.stampaPrestiti();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }

                                break;

                            case 7: System.exit(0);
                                break;
                        }
                    }while(amm!=6);
                    break;



                case 2:   //Cassiere

                    do {
                        cass=sceltaCassiere();
                        switch(cass)
                        {
                            case 1:
                                    prelievo(sistema);
                                break;
                            case 2:
                                    deposito(sistema);
                                break;
                            case 3:
                                    prestito(sistema);
                                break;
                            case 4:
                                     sceltaConsulente(sistema);
                                break;
                            case 5:
                                    visualizzaEstrattoConto(sistema);
                                break;

                            case 7: System.exit(0);
                                break;
                        }
                    }while(cass!=6);
                    break;

            }
        }while(s!=3);
        System.out.println("Arrivederci");

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
    public static void loadMappa(SistemaBancario sistema){
        sistema.inserisciNuovoCliente("Alessandro","Rossi", LocalDate.of(2023,2,9),"35464998");
        sistema.inserisciConto(2,0);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Davide","Verdi", LocalDate.of(2005,1,14),"33366038");
        sistema.inserisciConto(1,10000);
        sistema.confermaOperazione();

        sistema.inserisciNuovoCliente("Matteo","Neri", LocalDate.of(1989,6,2),"366943578");
        sistema.inserisciConto(3,45);
        sistema.confermaOperazione();
    }
    public static int scelta()
    {
        Scanner dati = new Scanner(System.in);
        System.out.println("Scegliere che livello operazioni effettuare");
        System.out.println("1)Amministratore");
        System.out.println("2)Cassiere");
        System.out.println("3)Esci");
        System.out.println("SCELTA: ");
        int s=Integer.parseInt(dati.nextLine());
        return s;

    }

    public static int sceltaAmministratore()
    {
        Scanner dati = new Scanner(System.in);
        System.out.println("----AMMINISTRATORE----");
        System.out.println("Scegliere quale operazione effettuare");
        System.out.println("1)Inserisci nuovo conto corrente");
        System.out.println("2)Visualizza tutti i conti correnti");
        System.out.println("3)Modifica tasso d'interesse");
        System.out.println("4)Modifica massimo prelevabile");
        System.out.println("5)Visualizza tutti i prestiti");
        System.out.println("6)Indietro");
        System.out.println("7)Esci");
        System.out.println("SCELTA: ");
        int s=Integer.parseInt(dati.nextLine());
        return s;

    }
    public static int sceltaCassiere()
    {
        Scanner dati = new Scanner(System.in);
        System.out.println("----CASSIERE----");
        System.out.println("Scegliere quale operazione effettuare");
        System.out.println("1)Gestisci prelievo");
        System.out.println("2)Gestisci deposito");
        System.out.println("3)Gestisci prestito");
        System.out.println("4)Scelta consulente finanziario");
        System.out.println("5)Visualizza estratto conto");
        System.out.println("6)Indietro");
        System.out.println("7)Esci");
        System.out.println("SCELTA: ");
        int s=Integer.parseInt(dati.nextLine());
        return s;

    }
    public static void modificaTasso(SistemaBancario sistema){

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
    }

    public static void modificaPrelevabile(SistemaBancario sistema){

        int tipoConto = -1;
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
    }
    public static void sceltaConsulente(SistemaBancario sistema)
    {
        List<Integer> lista_id = new ArrayList<>();
        List<ConsulenteFinanziario> listaConsulenti;
        try {
            System.out.println(" Scegli settore(IN MAIUSCOLO:   AZIONI ,  ETF, CRIPTOVALUTE, AZIONITECH, NTF, MATERIEPRIME, VALUTE");
            System.out.flush();
            TipoSettore a;
            try{
                 a= TipoSettore.valueOf(dati.nextLine());
            }catch(IllegalArgumentException e)
            {
                System.err.println("Argomento non valido--ECCEZIONE: "+e.getMessage());
                return;
            }


                listaConsulenti = sistema.richiediConsulente(a);
                for (ConsulenteFinanziario con : listaConsulenti) {
                    System.out.println(con.toString());
                    lista_id.add(con.getIdConsulente());
                }
                int id = -1;
                while (id == -1 || !lista_id.contains(id)) {
                    System.out.println("Inserisci l'id del consulente scelto");
                    System.out.flush();
                    id = Integer.parseInt(dati.nextLine());
                }
                String telefono;
                System.out.println("Inserisci il numero di telefono del cliente");
                System.out.flush();
                telefono = dati.nextLine();

                sistema.confermaConsulente(id, telefono);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void prelievo(SistemaBancario sistema)
    {
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
    }

    public static void deposito(SistemaBancario sistema)
    {
        System.out.println("OPERAZIONE DI DEPOSITO");
        ContoCorrente conto = OperazioneDiVerifica(sistema);
        if(conto == null){//il numero di carta non esiste
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
    }

    public static void prestito(SistemaBancario sistema)
    {

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
    }

    public static void visualizzaEstrattoConto(SistemaBancario sistema)
    {
        System.out.println("OPERAZIONE DI STAMPA LISTA MOVIMENTI");
        System.out.println("Inserisci numero carta");
        String numeroCartaInserito = dati.nextLine();
        try {
            sistema.stampaListaMovimenti(numeroCartaInserito);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void inserimento(SistemaBancario sistema)
    {
        String nome,cognome,telefono;
        int anno, mese,giorno,tipologia;
        double saldo;
        System.out.println("Inserisci nome");
        System.out.flush();
        nome = dati.nextLine();
        System.out.println("Inserisci cognome");
        System.out.flush();
        cognome = dati.nextLine();
        System.out.println("Inserisci telefono");
        System.out.flush();
        telefono = dati.nextLine();
        System.out.println("Inserisci anno data di nascita");
        System.out.flush();
        anno = Integer.parseInt(dati.nextLine());
        System.out.println("Inserisci mese data di nascita");
        System.out.flush();
        mese = Integer.parseInt(dati.nextLine());
        System.out.println("Inserisci giorno data di nascita");
        System.out.flush();
        giorno = Integer.parseInt(dati.nextLine());

        System.out.println("Scegli tipologia: 1 per conto Silver, 2 per conto Gold, 3 per conto Platinum");
        System.out.flush();
        tipologia = Integer.parseInt(dati.nextLine());
        System.out.println("Inserisci, se lo desideri immetere un saldo iniziale ");
        System.out.flush();
        saldo = Integer.parseInt(dati.nextLine());


        sistema.inserisciNuovoCliente(nome,cognome, LocalDate.of(anno,mese,giorno),telefono);
        sistema.inserisciConto(tipologia,saldo);
        sistema.confermaOperazione();
    }



}