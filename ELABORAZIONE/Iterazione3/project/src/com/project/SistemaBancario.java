package com.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaBancario {
    private static SistemaBancario sistemabancario;

    private ContoCorrente corrente;
    private Cliente clientecorrente = null;
    private Map<ContoCorrente,Cliente>elenco;
    private Map<String, Cliente> elencoClienti;
    private Map<Integer, ConsulenteFinanziario> elencoConsulenti;

    public SistemaBancario() {
        this.elenco=new HashMap<>();
        this.elencoClienti=new HashMap<>();
        this.elencoConsulenti=new HashMap<>();
    }

    public static SistemaBancario getInstance() {
        if (sistemabancario == null)
            sistemabancario = new SistemaBancario();
        else
            System.out.println("Istanza già creata");
        return sistemabancario;
    }

    public Cliente getClientecorrente() {
        return clientecorrente;
    }

    public ContoCorrente getCorrente() {
        return corrente;
    }

    public Map<ContoCorrente, Cliente> getElenco() {
        return elenco;
    }

    public List<Cliente> getElencoCliente() {
        List<Cliente> listCliente = new ArrayList<>();
        listCliente.addAll(elenco.values());
        return listCliente;
    }
    public List<ContoCorrente> getElencoConto() {
        List<ContoCorrente> listConto = new ArrayList<>();
        listConto.addAll(elenco.keySet());
        return listConto;
    }

    @Override
    public String toString() {
        return "SistemaBancario{" +
                " elenco=" + elenco +
                " } ";
    }

    public void stampa()
    {
        for (ContoCorrente conto:elenco.keySet())
        {
            System.out.println("\nConto corrente: "+ conto);
            System.out.println("Cliente: "+elenco.get(conto)+"\n");
        }


    }

    public Cliente inserisciNuovoCliente(String nome, String cognome,LocalDate datanascita,String telefono)
    {
        if(!telefono.matches("^[0-9]*$")){
            System.err.println("Errore: inserire numero di telefono con caratteri numerici");
            return null;
        }
        if(datanascita.compareTo(LocalDate.now()) > 0){
            System.err.println("Errore: inserire una data antecedente alla data odierna");
            return null;
        } /*
        for(Cliente c: getElencoCliente()){
            if(c.getTelefono().compareTo(telefono) == 0){
                System.err.println("Errore: il cliente "+ c.toString()+ " risulta già possedere un conto corrente");
                return null;
            }
        }*/
        if(elencoClienti.containsKey(telefono) == true){
            System.err.println("Errore: il cliente "+ elencoClienti.get(telefono).toString()+ " risulta già possedere un conto corrente");
            return null;
        }
        this.clientecorrente= new Cliente(nome,cognome,datanascita,telefono);
        return clientecorrente;
    }
    public ContoCorrente inserisciConto(int tipologia,double saldo)
    {
        if(saldo < 0){
            System.err.println("Errore: inserire saldo maggiore o uguale a zero");
            return null;
        }
        if( clientecorrente == null){
            System.err.println("Errore: prima di creare un conto inserire il nuovo cliente");
            return null;
        }
        switch(tipologia)
        {
            case 1:
                this .corrente= new ContoSilver(saldo);
                break;

            case 2:
                this .corrente= new ContoGold(saldo);
                break;

            case 3:
                this .corrente= new ContoPlatinum(saldo);
                break;

            default:
                System.out.println("Indicare una delle 3 tipologie indicate");
                return null;


        }
        return corrente;
    }

    public void confermaOperazione()
    {
        if(clientecorrente!= null && corrente!=null)
        {
            clientecorrente.setCartadiCredito(corrente.getCartaAssociata());
            this.elencoClienti.put(clientecorrente.getTelefono(), clientecorrente);
            this.elenco.put(corrente,clientecorrente);
            System.out.println("Operazione completata con successo!!");
        }
        clientecorrente = null;
        corrente = null;

    }

    public ContoCorrente verificaCarta(String numeroCartaInserito){
        List<ContoCorrente> conti= getElencoConto();
        double saldo;
        for (ContoCorrente c:conti) {
            saldo=c.findNumeroCarta(numeroCartaInserito);
            if(saldo!=-1){
                return c;
            }
        }
        return null;

    }
    public void effetuaPrelievo(int pinCartaInserito, double importo, ContoCorrente conto) {
        double saldo = conto.verificaPin(pinCartaInserito, importo, "prelievo");
        if(saldo != -1){
            System.out.println("Il saldo Attuale del conto (IBAN " + conto.getIBAN() + ") e'"+ saldo);

        }

    }

    public void effetuaDeposito(int pinDeposito, int importoDeposito, ContoCorrente conto) {
        double saldo = conto.verificaPin(pinDeposito, importoDeposito, "deposito");
        if(saldo != -1){
            System.out.println("Il saldo Attuale del conto (IBAN " + conto.getIBAN() + ") e'"+ saldo);

        }
    }

    public void loadConsulenti()
    {
        ConsulenteFinanziario a= new ConsulenteFinanziario("marco@gmail.com","Marco","Rossi",5,TipoSettore.AZIONITECH);
        this.elencoConsulenti.put(a.getIdConsulente(),a);
        ConsulenteFinanziario b= new ConsulenteFinanziario("alfio@gmail.com","Alfio","Neri",2,TipoSettore.AZIONI);
        this.elencoConsulenti.put(b.getIdConsulente(),b);
        ConsulenteFinanziario c= new ConsulenteFinanziario("francesco@gmail.com","Francesco","Verdi",8,TipoSettore.ETF);
        this.elencoConsulenti.put(c.getIdConsulente(),c);
        ConsulenteFinanziario d= new ConsulenteFinanziario("davide@gmail.com","Davide","Bianchi",5,TipoSettore.NTF);
        this.elencoConsulenti.put(d.getIdConsulente(),d);
        ConsulenteFinanziario e= new ConsulenteFinanziario("elia@gmail.com","Elia","Gialli",1,TipoSettore.CRIPTOVALUTE);
        this.elencoConsulenti.put(e.getIdConsulente(),e);
        ConsulenteFinanziario f= new ConsulenteFinanziario("alessandro@gmail.com","Alessandro","Grasso",5,TipoSettore.MATERIEPRIME);
        this.elencoConsulenti.put(f.getIdConsulente(),f);
        ConsulenteFinanziario g= new ConsulenteFinanziario("alfredo@gmail.com","Alfredo","Potter",4,TipoSettore.MATERIEPRIME);
        this.elencoConsulenti.put(g.getIdConsulente(),g);
        ConsulenteFinanziario h= new ConsulenteFinanziario("matteo@gmail.com","Matteo","Jackson",7,TipoSettore.AZIONITECH);
        this.elencoConsulenti.put(h.getIdConsulente(),h);
    }

    public void stampaConsulenti(){
        for (ConsulenteFinanziario consulente:elencoConsulenti.values())
        {
            System.out.println("\n"+ consulente);

        }
    }




}
