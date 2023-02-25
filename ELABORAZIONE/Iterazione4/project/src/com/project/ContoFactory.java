package com.project;

//Usiamo questa classe a seguito dell'utilizzo del pattern factory method
public class ContoFactory {
    public static ContoCorrente nuovoConto(int tipologia,double saldo){
        if(saldo < 0){
            System.err.println("Errore: inserire saldo maggiore o uguale a zero");
            return null;
        }
        switch(tipologia)
        {
            case 1:
                System.out.println("CREAZIONE CONTO SILVER...");
                return new ContoSilver(saldo);

            case 2:
                System.out.println("CREAZIONE CONTO GOLD...");
                return new ContoGold(saldo);

            case 3:
                System.out.println("CREAZIONE CONTO PLATINUM...");
                return new ContoPlatinum(saldo);

            default:
                System.out.println("Indicare una delle 3 tipologie indicate");
                return null;


        }

    }
}
