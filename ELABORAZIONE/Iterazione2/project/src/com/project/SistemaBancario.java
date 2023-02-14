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

    public SistemaBancario() {
        this.elenco=new HashMap<>();
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
            System.out.println("\nConto corrente: "+ conto+"\n");
            System.out.println("Cliente: "+elenco.get(conto));
        }


    }

    public void inserisciNuovoCliente(String nome, String cognome,LocalDate datanascita,String telefono)
    {
        if(!telefono.matches("^[0-9]*$")){
            System.err.println("Errore: inserire numero di telefono con caratteri numerici");
            return;
        }
        if(datanascita.compareTo(LocalDate.now()) > 0){
            System.err.println("Errore: inserire una data antecedente alla data odierna");
            return;
        }
        for(Cliente c: getElencoCliente()){
            if(c.getTelefono().compareTo(telefono) == 0){
                System.err.println("Errore: il cliente "+ c.toString()+ " risulta già possedere un conto corrente");
                return;
            }
        }
        this.clientecorrente= new Cliente(nome,cognome,datanascita,telefono);

    }
    public void inserisciConto(int tipologia,double saldo)
    {
        if(saldo < 0){
            System.err.println("Errore: inserire saldo maggiore o uguale a zero");
            return;
        }
        if( clientecorrente == null){
            System.err.println("Errore: prima di creare un conto inserire il nuovo cliente");
            return;
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


        }
    }

    public void confermaOperazione()
    {
        if(clientecorrente!= null && corrente!=null)
        {
            this.elenco.put(corrente,clientecorrente);
            System.out.println("Operazione completata con successo!!");
        }
        clientecorrente = null;
        corrente = null;

    }

    public double verificaCarta(String numeroCartaInserito){
        List<ContoCorrente> conti= getElencoConto();
        double saldo;
        for (ContoCorrente c:conti) {
            saldo=c.findNumeroCarta(numeroCartaInserito);
            if(saldo!=-1){
                return saldo;
            }
        }
        return -1;

    }

}
