package com.project;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrestito {
    private static  RegistroPrestito registroprestito;
    private Map<Integer, Prestito> elencoPrestiti;
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
    }


}
