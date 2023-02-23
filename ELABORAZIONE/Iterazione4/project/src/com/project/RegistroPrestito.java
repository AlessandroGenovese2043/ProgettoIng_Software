package com.project;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrestito {
    private static  RegistroPrestito registroprestito;
    private final Map<Integer, Prestito> elencoPrestiti;
    public RegistroPrestito() {
        this.elencoPrestiti=new HashMap<>();
    }

    public static RegistroPrestito getInstance() {
        if (registroprestito == null)
            registroprestito = new RegistroPrestito();
        else
            System.out.println("Istanza già creata");
        return registroprestito;
    }

    public void add(Prestito p)
    {
        this.elencoPrestiti.put(p.getIdPrestito(),p);
        System.out.println("Il prestito è stato aggiunto all'elenco: " + elencoPrestiti);
    }

    public Map<Integer, Prestito> getElencoPrestiti() {
        return elencoPrestiti;
    }
    public Prestito getPrestito(String telefonoCliente){
        for(Prestito p:elencoPrestiti.values()){
            if(p.getCliente().getTelefono().compareTo(telefonoCliente) == 0 && !p.isSanato()){
                return p; // Se esiste restituisce un prestito attivo del cliente non ancora sanato
            }
        }
        return null;
    }
}
