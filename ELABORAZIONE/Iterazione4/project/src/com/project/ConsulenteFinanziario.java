package com.project;

import java.util.ArrayList;
import java.util.List;

public class ConsulenteFinanziario {
    private String email;
    private String nome;
    private String cognome;
    private int anniEsperienza;
    private TipoSettore settore;
    private static int cont= 0;
    private final int idConsulente;
    private List<Cliente> listaClienti;

    public ConsulenteFinanziario(String email, String nome, String cognome, int anniEsperienza, TipoSettore settore) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.anniEsperienza = anniEsperienza;
        this.settore = settore;
        this.listaClienti = new ArrayList<>();
        this.idConsulente = cont++;
    }

    public ConsulenteFinanziario(String email, String nome, String cognome, int anniEsperienza, TipoSettore settore, List<Cliente> listaClienti) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.anniEsperienza = anniEsperienza;
        this.settore = settore;
        this.listaClienti = listaClienti;
        this.idConsulente = cont++;
    }

    public int getIdConsulente() {
        return idConsulente;
    }

    public List<Cliente> getListaClienti() {
        return listaClienti;
    }

    public void setListaClienti(List<Cliente> listaClienti) {
        this.listaClienti = listaClienti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getAnniEsperienza() {
        return anniEsperienza;
    }

    public void setAnniEsperienza(int anniEsperienza) {
        this.anniEsperienza = anniEsperienza;
    }

    public TipoSettore getSettore() {
        return settore;
    }

    public void setSettore(TipoSettore settore) {
        this.settore = settore;
    }


    @Override
    public String toString() {
        return "ConsulenteFinanziario{" +
                "idConsulente='" + idConsulente + '\'' +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", anniEsperienza=" + anniEsperienza +
                ", settore=" + settore +
                '}';
    }

    //Metodo per aggiungere un cliente alla lista dei clienti di un consulente
    public void aggiungiCliente(Cliente cliente) throws Exception {
        if(cliente != null) {
            listaClienti.add(cliente);
            System.out.println("Il cliente è stato aggiunto correttamente alla lista del Consulente");
            System.out.println(this.getIdConsulente());
            System.out.println(listaClienti);
        }
        else{
            throw new Exception("Non risulta inserito un cliente");
        }

    }



}
