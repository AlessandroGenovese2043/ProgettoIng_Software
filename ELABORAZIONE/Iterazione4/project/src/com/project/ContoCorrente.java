package com.project;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ContoCorrente {
    private static int numberIban= 100000;
    private final String firstLetter = "IT";
    private String IBAN;
    private double saldo;
    private List<OperazioneBancaria> lista_movimenti;
    private CartadiCredito cartaAssociata;
    public ContoCorrente(double saldo) {
        this.saldo = saldo;
        associaCartaCredito();
        associaIBAN();
        this.lista_movimenti  = new ArrayList<>();
    }

    private void associaIBAN() {
        String number = ("" + numberIban);
        numberIban++;
        IBAN = firstLetter.concat(number);
    }

    private void associaCartaCredito(){
        LocalDate today = LocalDate.now();
        today = today.plusYears(5);
        cartaAssociata = new CartadiCredito(today);
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getSaldo() {
        return saldo;
    }
    public CartadiCredito getCartaAssociata() {
        return cartaAssociata;
    }
    public double findNumeroCarta(String numeroCartaInserita) {
        String numeroCarta = cartaAssociata.getNumeroCarta();

        if(numeroCartaInserita.compareTo(numeroCarta)==0)
        {
            return saldo;
        }
        return -1;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContoCorrente{" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", cartaAssociata=" + cartaAssociata +
                '}';
    }

    public double verificaPin(int pinCartaInserito, double importo, String operazione){
        if(pinCartaInserito == cartaAssociata.getPinCarta()){
            //il pin è corretto
            if(operazione.compareTo("deposito") == 0){
                Deposito d = new Deposito(importo);
                lista_movimenti.add(d);
                aggiornaSaldo(importo, operazione);
                return saldo;
            }

            if(importo < saldo && operazione.compareTo("prelievo") == 0 && importo < (this.massimoPrelevabile() - verificaMovimento())){
                Prelievo p = new Prelievo(importo);
                lista_movimenti.add(p);
                aggiornaSaldo(importo, operazione);
                return saldo; //saldoAttuale
            }else{
                System.err.println("Errore: l'importo non è accettabile");

            }
        }else{
            System.err.println("Errore: Il pin inserito è errato");
        }
        return -1;
    }

    public void aggiornaSaldo(double importo, String operazione){
        if(operazione.compareTo("deposito") == 0){
            saldo = saldo + importo - Deposito.getCommissione();
        }
        else {
            saldo = saldo - importo;
        }
        System.out.println("Saldo aggiornato");
    }

    public List<OperazioneBancaria> getLista_movimenti() {
        return lista_movimenti;
    }

    public void stampaListaMovimenti() throws Exception {
        if(lista_movimenti.size() == 0){
            throw new Exception("Nessun movimento presente nella lista");
        }
        for (OperazioneBancaria op:lista_movimenti)
        {
            if(op.getClass() == Prelievo.class){
                System.out.println("\nPrelievo: "+ op + "\n");
            }
            else{
                System.out.println("\nDeposito: " + op + " COMMISSIONE:" + ((Deposito) op).getCommissioneAttuale() + " euro\n");
            }

        }
    }
    public abstract double massimoPrelevabile();
    public abstract double getTassoInt();

    public double verificaMovimento(){ //Controllo dei prelievi giornalieri
        double totale = 0;
        if(lista_movimenti.size() == 0){
            return totale;
        }
        for(OperazioneBancaria op : lista_movimenti) {
            if (op.getClass() == Prelievo.class && op.getData().isEqual(LocalDate.now())) {
                totale = totale + op.getImporto();
                //System.out.println("" + op + totale);
            }
        }

        return totale;
    }


}
